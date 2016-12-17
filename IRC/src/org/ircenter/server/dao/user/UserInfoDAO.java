package org.ircenter.server.dao.user;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.bean.edit.UserMain;
import org.ircenter.server.service.user.Gender;
import org.ircenter.server.service.user.Role;
import org.ircenter.server.service.user.UserProfile;
import org.ircenter.server.service.user.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

@Repository
public class UserInfoDAO {

    private static final Log LOGGER = LogFactory.getLog(UserInfoDAO.class.getClass());
    private static final int USER_PER_PAGE = 10;
    private NamedParameterJdbcTemplate jdbcTemplate;

    private final RowMapper<UserProfile> rowMapper = new RowMapper<UserProfile>() {
        public UserProfile mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("user_id");
            String name = rs.getString("name");
            if (name == null) {
                name = UserHelper.UNREG_NAME;
            }

            String password = rs.getString("password");
            String login = rs.getString("username");
            boolean enabled = rs.getBoolean("enabled");
            UserProfile userProfile = new UserProfile(id, login, password, enabled, true, true, true, getAuthorities(id));
            userProfile.setLoginName(name);
            userProfile.setUserId(id);
            userProfile.setCreateDate(rs.getTimestamp("create_date"));
            int countryId = rs.getInt("country_id");

            if (countryId != 0) {
                userProfile.setCountryId(countryId);
                userProfile.setRegionId(rs.getInt("region_id"));
                userProfile.setCityId(rs.getInt("city_id"));
            } else {
                //userProfile.setFullRegionName("Незарегистрированный пользователь");
                userProfile.setCityId(0);
                userProfile.setRegionId(0);
                userProfile.setCountryId(0);
            }
            // userProfile.setCommentsCount(getCommentsCount(id));
            //userProfile.setIrcUserName(login);
            userProfile.setGender(Gender.getGender(rs.getInt("gender")));
            userProfile.setDayOfBirth(rs.getDate("day_birth"));
            userProfile.setGivenName(rs.getString("first_name"));
            userProfile.setSurname(rs.getString("surname"));
            return userProfile;
        }
    };

    public UserInfoDAO() {
    }

    @Autowired
    public void init(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     * Получить имя пользователя по Id пользователя
     *
     * @param userId Id пользователя
     * @return имя пользователя
     */
    public String getName(long userId) {
        String query = "SELECT name FROM users WHERE user_id = :user_id";
        try {
            return jdbcTemplate.queryForObject(query, new MapSqlParameterSource("user_id", userId), String.class);
        } catch (DataAccessException e) {
            LOGGER.error("error get name by id " + userId, e);
            return "";
        }
    }

    public void deleteNameFromCache(Long userId) {
    }

    /**
     * Получить логин пользователя по Id пользователя
     *
     * @param userId Id пользователя
     * @return логин пользователя
     */
    public String getUserName(long userId) {
        String query = "SELECT username FROM users WHERE user_id = :user_id";
        try {
            return jdbcTemplate.queryForObject(query, new MapSqlParameterSource("user_id", userId), String.class);
        } catch (DataAccessException e) {
            LOGGER.error("error get login by id " + userId, e);
            return "";
        }
    }

    /**
     * Получить пароль пользователя по Id пользователя
     *
     * @param userId Id пользователя
     * @return пароль пользователя
     */
    public String getUserPassword(long userId) {
        String query = "SELECT password FROM users WHERE user_id = :user_id";
        try {
            return jdbcTemplate.queryForObject(query, new MapSqlParameterSource("user_id", userId), String.class);
        } catch (DataAccessException e) {
            LOGGER.error("error get user password by id " + userId, e);
            return "";
        }
    }

    /**
     * Получить Id и имя пользователя по логину
     *
     * @param login логин пользователя
     * @return map с данными о пользователе
     */
    public UserProfile getUserByLogin(String login) {
        String query = "SELECT * FROM users AS t1 LEFT JOIN user_profile AS t2 ON (t1.user_id = t2.user_id) WHERE t1.username = :login";
        try {
            return jdbcTemplate.queryForObject(query, new MapSqlParameterSource("login", login), rowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("error get user map by login = " + login, e);
            //return new UserProfile();
            return null;
        }
    }

    /**
     * Получить количество пользователей
     *
     * @return количество пользователей
     */
    public long getUsersCount() {
        String query = "SELECT COUNT(1) FROM users";
        try {
            return jdbcTemplate.queryForLong(query, new MapSqlParameterSource());
        } catch (DataAccessException e) {
            LOGGER.error("error get users count", e);
            return 0;
        }
    }

    /**
     * Получить все роли для пользователя
     *
     * @param userId логин пользователя
     * @return все роли для пользователя
     */
    public List<GrantedAuthority> getAuthorities(long userId) {
        String query = "SELECT authority FROM authorities WHERE user_id = :user_id";
        try {
            List<String> res = jdbcTemplate.queryForList(query, new MapSqlParameterSource("user_id", userId), String.class);
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(res.size());
            for (String auth : res) {
                authorities.add(new SimpleGrantedAuthority(auth));
            }
            return authorities;
        } catch (DataAccessException e) {
            LOGGER.error("exception while getting authorities for userId = " + userId, e);
            return Collections.emptyList();
        }
    }

    /**
     * Получить лист всех пользователей
     *
     * @return лист всех пользователей
     */
    public List<UserProfile> getAllUsers() {
        String query = "SELECT * FROM users AS t1 LEFT JOIN user_profile AS t2 ON (t1.user_id = t2.user_id) ORDER BY t2.create_date";
        try {
            return jdbcTemplate.query(query, new MapSqlParameterSource(), rowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("exception while getting all users", e);
            return Collections.emptyList();
        }
    }

    /**
     * Получить пользователя по Id
     *
     * @param userId Id поста
     * @return пользователь c запрашиваемым Id
     */
    public UserProfile getUser(long userId) {

        String query = "SELECT * FROM users AS t1 LEFT JOIN user_profile AS t2 ON (t1.user_id = t2.user_id) WHERE t1.user_id = :user_id";
        try {
            return jdbcTemplate.queryForObject(query, new MapSqlParameterSource("user_id", userId), rowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("error get user by user_id " + userId, e);
            //return new UserProfile();
            return null;
        }
    }

    public void removeUser(long userId) {

        String query = "DELETE FROM users WHERE user_id=:user_id";
        try {
            jdbcTemplate.update(query, new MapSqlParameterSource("user_id", userId));
        } catch (DataAccessException e) {
            LOGGER.error("error removeUser " + userId, e);
        }
    }

    public List<UserProfile> searchUser(String name) {
        String query = "SELECT * FROM users AS t1 LEFT JOIN user_profile AS t2 ON (t1.user_id = t2.user_id) WHERE t1.name LIKE :name";
        try {
            return jdbcTemplate.query(query, new MapSqlParameterSource().addValue("name", "%" + name + "%"), rowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("Error searchUser name=" + name, e);
            return Collections.emptyList();
        }
    }

    /**
     * Получить пользователя по псевдониму
     *
     * @param name псевдоним
     * @return пользователь c запрашиваемым псевдонимом
     */
    public UserProfile getUserByName(String name) {
        String query = "SELECT * FROM users AS t1 LEFT JOIN user_profile AS t2 ON (t1.user_id = t2.user_id) WHERE t1.name = :name";
        try {
            return jdbcTemplate.queryForObject(query, new MapSqlParameterSource("name", name), rowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("error get user by name " + name, e);
            //return new UserProfile();
            return null;
        }
    }

    /**
     * Получить пользователей для страницы пагинатора
     *
     * @param page страница пагиноатора
     * @return пользователи для страницы пагинатора
     */
    public List<UserProfile> getUsers(int page) {
        String query = "SELECT * FROM users AS t1 LEFT JOIN user_profile AS t2 ON (t1.user_id = t2.user_id) ORDER BY t2.create_date LIMIT :start, :end";
        try {
            return jdbcTemplate.query(query, new MapSqlParameterSource().addValue("start", page * USER_PER_PAGE).addValue("end", USER_PER_PAGE), rowMapper);
        } catch (Exception e) {
            LOGGER.error("error get users", e);
            return Collections.emptyList();
        }
    }

    /**
     * Забанить пользователя до bannedTo
     *
     * @param bannedTo время до которого забанене пользователь
     * @param userId   Id пользователя
     */
    public void banUser(long bannedTo, long userId) {
        String query = "update users SET banned = :banned where user_id = :user_id";
        try {
            jdbcTemplate.update(query, new MapSqlParameterSource().addValue("banned", new Timestamp(bannedTo)).addValue("user_id", userId));
        } catch (DataAccessException e) {
            LOGGER.error("Exception while ban user with id = " + userId, e);
        }
    }

    /**
     * Получить количество пользователей с данным именем пользователя
     *
     * @param login имя пользователя
     * @return количество пользователей с данным именем пользователя
     */
    //todo кэшировать?
    public int getLoginCountByLogin(String login) {
        String query = "SELECT COUNT(1) FROM users WHERE username = :login";
        try {
            return jdbcTemplate.queryForInt(query, new MapSqlParameterSource("login", login));
        } catch (DataAccessException e) {
            LOGGER.error("error get users count with login = " + login, e);
            return 0;
        }
    }

    /**
     * Получить login пользователя по name пользователя
     *
     * @param name имя пользователя
     * @return login пользователя по name пользователя
     */
    public String getLoginByName(String name) {
        String query = "SELECT login FROM users WHERE name = :name";
        try {
            return jdbcTemplate.queryForObject(query, new MapSqlParameterSource("name", name), String.class);
        } catch (DataAccessException e) {
            LOGGER.error("error get username for name = " + name, e);
            return "";
        }
    }

    /**
     * Получить количество пользователей с данным именем
     *
     * @param name имя
     * @return количество пользователей с данным именем
     */
    //todo кэшировать?
    public int getNameCount(String name) {
        String query = "SELECT COUNT(1) FROM users WHERE name = :name";
        try {
            return jdbcTemplate.queryForInt(query, new MapSqlParameterSource("name", name));
        } catch (DataAccessException e) {
            LOGGER.error("error get users count with name = " + name, e);
            return 0;
        }
    }

    /**
     * Проверить старый пароль
     *
     * @param oldPassword старый пароль
     * @param userId      Id юзера
     * @return true если совпадает с БД, false - в противном случае
     */
    public boolean checkPassword(String oldPassword, long userId) {
        String query = "SELECT COUNT(1) FROM users WHERE password = :password AND user_id = :user_id";
        try {
            return jdbcTemplate.queryForInt(query, new MapSqlParameterSource().addValue("password", oldPassword).addValue("user_id", userId)) > 0;
        } catch (DataAccessException e) {
            LOGGER.error("error check password for userId = " + userId, e);
            return false;
        }
    }

    /**
     * Изменить имя пользователя
     *
     * @param login имя пользователя
     */
//    public void changeLogin(String login) {
//        String query = "UPDATE users SET username = :login WHERE user_id = :user_id";
//        long id = UserHelper.getUserId();
//        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("login", login).addValue("user_id", id);
//        try {
//            LOGGER.info("user with user_id = " + id + " have changed login from " + UserHelper.getUserName()
//                    + " to " + login);
//            int affectedRow = jdbcTemplate.update(query, parameterSource);
//            if (affectedRow > 0) {
//                UserProfile userInfo = (UserProfile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//                if (userInfo != null) {
//                    userInfo.setIrcUserName(login);
//                }
//            }
//        } catch (DataAccessException e) {
//            LOGGER.error("error change login for userId = " + id, e);
//        }
//    }
    public int getUserByVk() {
        String query = "SELECT COUNT(1) FROM users WHERE vk_id IS NOT NULL";
        try {
            return jdbcTemplate.queryForInt(query, new MapSqlParameterSource());
        } catch (DataAccessException e) {
            LOGGER.error("error getUserByVk = ", e);
            return 0;
        }
    }

    public int getUserByFb() {
        String query = "SELECT COUNT(1) FROM users WHERE fb_id IS NOT NULL";
        try {
            return jdbcTemplate.queryForInt(query, new MapSqlParameterSource());
        } catch (DataAccessException e) {
            LOGGER.error("error getUserByFb = ", e);
            return 0;
        }
    }


    /**
     * Изменить пароль пользователя
     *
     * @param newPassword новый пароль пользователя
     */
    public void changePassword(String newPassword, long userId) {
        String query = "UPDATE users SET password = :password WHERE user_id = :user_id";
        try {
            LOGGER.info("user with user_id = " + userId + " have changed password");
            jdbcTemplate.update(query, new MapSqlParameterSource().addValue("password", newPassword).addValue("user_id", userId));
        } catch (DataAccessException e) {
            LOGGER.error("error change password for userId = " + userId, e);
        }
    }

    /**
     * Изменить расположение пользователя
     *
     * @param cityId Id города
     */
    public void changeRegion(long cityId) {
//        String query = "UPDATE users SET city_id = :city_id, region_id = :region_id, country_id = :country_id WHERE user_id = :user_id";
//        long id = UserHelper.getUserId();
//        long regionId = userLocationDAO.getCityRegionId(cityId);
//        long countryId = userLocationDAO.getRegionCountryId(regionId);
//        try {
//            LOGGER.info("user with user_id = " + id + " have changed region cityId = " + cityId);
//            jdbcTemplate.update(query, new MapSqlParameterSource().addValue("city_id", cityId).addValue("region_id", regionId).addValue("country_id", countryId).addValue("user_id", id));
//        } catch (DataAccessException e) {
//            LOGGER.error("error change region for userId = " + id + " and cityId = " + cityId, e);
//        }
//        UserProfile userInfo = ((UserProfile) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        userInfo.setCityId(cityId);
//        userInfo.setRegionId(regionId);
//        userInfo.setCountryId(countryId);
    }

    /**
     * Создать пользователя
     *
     * @param name      имя пользователя
     * @param uuid      уникальный uuid
     * @param countryId Id страны
     * @param regionId  Id региона
     * @param cityId    Id города
     * @return id созданного пользователя
     */
    public long createUser(String name, String uuid, Long countryId, Long regionId, Long cityId) {
        String query = "INSERT INTO users (name, username, password, country_id, region_id, city_id)" +
                " VALUES (:name, :login, 'очень сложный пароль', :country_id, :region_id, :city_id)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource fileParameters = new MapSqlParameterSource().addValue("name", name)
                .addValue("login", uuid == null ? name : uuid)
                .addValue("country_id", countryId)
                .addValue("region_id", regionId)
                .addValue("city_id", cityId);
        jdbcTemplate.update(query, fileParameters, keyHolder);
        return (Long) keyHolder.getKey();
    }

    /**
     * Создать пользователя ВКонтакте
     *
     * @param name имя
     * @param vkId id Вконтакте
     */
    public void createVKUser(String name, String vkId) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String query = "INSERT INTO users (name, username, password, vk_id)" +
                " VALUES (:name, :name, \"\", :vk_id)";
        SqlParameterSource fileParameters = new MapSqlParameterSource().addValue("name", name).addValue("vk_id", vkId);

        jdbcTemplate.update(query, fileParameters, keyHolder);

        long userId = (Long) keyHolder.getKey();

        String insertAuth = "INSERT INTO authorities (user_id, authority) VALUES (:user_id, :role)";
        String insertProfile = "INSERT INTO user_profile (user_id) VALUES (:user_id)";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource().addValue("user_id", userId);
        jdbcTemplate.update(insertProfile, parameterSource);
        parameterSource.addValue("role", Role.ROLE_USER.getValue());
        jdbcTemplate.update(insertAuth, parameterSource);


    }

    /**
     * Создать пользователя Facebook
     *
     * @param name имя
     * @param fbId id Вконтакте
     */
    public void createFBUser(String name, String fbId) {
//        String query = "INSERT INTO users (name, username, password, fb_id)" +
//                " VALUES (:name, :name, \"\", :fb_id)";
//        SqlParameterSource fileParameters = new MapSqlParameterSource().addValue("name", name).addValue("fb_id", fbId);
//
//        jdbcTemplate.update(query, fileParameters);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        String query = "INSERT INTO users (name, username, password, fb_id)" +
                " VALUES (:name, :name, \"\", :fb_id)";
        SqlParameterSource fileParameters = new MapSqlParameterSource().addValue("name", name).addValue("fb_id", fbId);

        jdbcTemplate.update(query, fileParameters, keyHolder);

        long userId = (Long) keyHolder.getKey();

        String insertAuth = "INSERT INTO authorities (user_id, authority) VALUES (:user_id, :role)";
        String insertProfile = "INSERT INTO user_profile (user_id) VALUES (:user_id)";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource().addValue("user_id", userId);
        jdbcTemplate.update(insertProfile, parameterSource);
        parameterSource.addValue("role", Role.ROLE_USER.getValue());
        jdbcTemplate.update(insertAuth, parameterSource);

    }

    /**
     * Создать пользователя Mail.ru
     *
     * @param name     имя
     * @param mailRuId id Mail.ru
     */
    public void createMailRuUser(String name, String mailRuId) {
        String query = "INSERT INTO users (name, username, password, mail_ru_id)" +
                " VALUES (:name, :name, \"\", :mail_ru_id)";
        SqlParameterSource fileParameters = new MapSqlParameterSource().addValue("name", name).addValue("mail_ru_id", mailRuId);

        jdbcTemplate.update(query, fileParameters);
    }


    /**
     * Получить количество комментариев
     *
     * @param userId Id пользователя
     * @return количество комментариев
     */
    public long getCommentsCount(long userId) {
        String query = "SELECT COUNT(1) FROM comments WHERE user_id = :user_id";
        try {
            return jdbcTemplate.queryForInt(query, new MapSqlParameterSource("user_id", userId));
        } catch (DataAccessException e) {
            LOGGER.error("error getting comments count by userId = " + userId, e);
            return 0;
        }
    }

    /**
     * Есть ли пользователь с таким VK_ID
     *
     * @param vkId id вконтакта
     * @return true если новый, false если нет
     */
    public boolean isVKUserNew(String vkId) {
        String query = "SELECT COUNT(1) FROM users WHERE vk_id = :vk_id";
        return !(jdbcTemplate.queryForInt(query, new MapSqlParameterSource("vk_id", vkId)) > 0);
    }

    /**
     * Есть ли пользователь с таким FB_ID
     *
     * @param fbId id вконтакта
     * @return true если новый, false если нет
     */
    public boolean isFBUserNew(String fbId) {
        String query = "SELECT COUNT(1) FROM users WHERE fb_id = :fb_id";
        return !(jdbcTemplate.queryForInt(query, new MapSqlParameterSource("fb_id", fbId)) > 0);
    }

    /**
     * Есть ли пользователь с таким MAIL_RU_ID
     *
     * @param mailRuId id mail.ru
     * @return true если новый, false если нет
     */
    public boolean isMailRuUserNew(String mailRuId) {
        String query = "SELECT COUNT(1) FROM users WHERE mail_ru_id = :mail_ru_id";
        return !(jdbcTemplate.queryForInt(query, new MapSqlParameterSource("mail_ru_id", mailRuId)) > 0);
    }


    /**
     * Получить login по vk_id
     *
     * @param uid id вконтакта
     * @return login пользователя
     */
    public String getLoginByVkId(String uid) {
        String query = "SELECT username FROM users WHERE vk_id = :vk_id";
        try {
            return jdbcTemplate.queryForObject(query, new MapSqlParameterSource().addValue("vk_id", uid), String.class);
        } catch (DataAccessException e) {
            LOGGER.error("getLoginByVkId error vk_id = " + uid, e);
            return "";
        }
    }

    /**
     * Получить login по fb_id
     *
     * @param uid id facebook
     * @return login пользователя
     */
    public String getLoginByFbId(String uid) {
        String query = "SELECT username FROM users WHERE fb_id = :fb_id";
        try {
            return jdbcTemplate.queryForObject(query, new MapSqlParameterSource().addValue("fb_id", uid), String.class);
        } catch (DataAccessException e) {
            LOGGER.error("getLoginByFbId error fb_id = " + uid, e);
            return "";
        }
    }

    /**
     * Получить login по mailRu_id
     *
     * @param uid id mailRu
     * @return login пользователя
     */
    public String getLoginByMailRuId(String uid) {
        String query = "SELECT username FROM users WHERE mail_ru_id = :mail_ru_id";
        try {
            return jdbcTemplate.queryForObject(query, new MapSqlParameterSource().addValue("mail_ru_id", uid), String.class);
        } catch (DataAccessException e) {
            LOGGER.error("getLoginByMailRuId error mailRuId = " + uid, e);
            return "";
        }
    }

    public void editUserInfo(UserMain userMain) {
        if (userMain.getCountryId() == 0) {
            userMain.setRegionId(0);
        }
        if (userMain.getRegionId() == 0) {
            userMain.setCityId(0);
        }

        String query = "UPDATE user_profile SET first_name = :first_name, surname = :surname, gender = :gender,day_birth = :day_birth, " +
                "country_id = :country_id, region_id = :region_id, city_id = :city_id WHERE user_id = :user_id";
        MapSqlParameterSource source = new MapSqlParameterSource();
        Calendar calendar = Calendar.getInstance();
        calendar.set(userMain.getYearDb(), userMain.getMonthDb() - 1, userMain.getDayDb());
        source.addValue("first_name", userMain.getName()).addValue("surname", userMain.getSurname()).addValue("gender", userMain.getGenderId())
                .addValue("day_birth", calendar.getTime()).addValue("country_id", userMain.getCountryId()).addValue("region_id", userMain.getRegionId()).
                addValue("city_id", userMain.getCityId()).addValue("user_id", UserHelper.getUserId());
        try {
            jdbcTemplate.update(query, source);
        } catch (DataAccessException e) {
            LOGGER.error("could not edit user info", e);
        }
        UserProfile userInfo = (UserProfile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userInfo != null) {
            userInfo.setGivenName(userMain.getName());
            userInfo.setSurname(userMain.getSurname());
            userInfo.setGender(Gender.getGender(userMain.getGenderId()));
            userInfo.setDayOfBirth(calendar.getTime());
            userInfo.setCountryId(userMain.getCountryId());
            userInfo.setRegionId(userMain.getRegionId());
            userInfo.setCityId(userMain.getCityId());
        }
    }

    public void editUserInfo(String name, String surname, int genderId, int dbDay,
                             int dbMonth, int dbYear, int countryId, int regionId, int cityId) {
        if (countryId == 0) {
            regionId = 0;
        }
        if (regionId == 0) {
            cityId = 0;
        }

        String query = "UPDATE user_profile SET first_name = :first_name, surname = :surname, gender = :gender,day_birth = :day_birth, " +
                "country_id = :country_id, region_id = :region_id, city_id = :city_id WHERE user_id = :user_id";
        MapSqlParameterSource source = new MapSqlParameterSource();
        Calendar calendar = Calendar.getInstance();
        calendar.set(dbYear, dbMonth - 1, dbDay);
        source.addValue("first_name", name).addValue("surname", surname).addValue("gender", genderId)
                .addValue("day_birth", calendar.getTime()).addValue("country_id", countryId).addValue("region_id", regionId).
                addValue("city_id", cityId).addValue("user_id", UserHelper.getUserId());
        try {
            jdbcTemplate.update(query, source);
        } catch (DataAccessException e) {
            LOGGER.error("could not edit user info", e);
        }
        UserProfile userInfo = (UserProfile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userInfo != null) {
            userInfo.setGivenName(name);
            userInfo.setSurname(surname);
            userInfo.setGender(Gender.getGender(genderId));
            userInfo.setDayOfBirth(calendar.getTime());
            userInfo.setCountryId(countryId);
            userInfo.setRegionId(regionId);
            userInfo.setCityId(cityId);
        }
    }
}