package org.ircenter.server.dao.registration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.service.user.Role;
import org.ircenter.server.service.user.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.Random;

@Repository("registrationDAO")
public class RegistrationDAO {

    private final static Log LOGGER = LogFactory.getLog(RegistrationDAO.class.getClass());
    private NamedParameterJdbcTemplate jdbcTemplate;

    /**
     * Сохранить регистрируемого пользователя
     *
     * @param name     имя
     * @param login    емейл
     * @param password пароль
     * @return код активации
     */
    @Transactional
    public long saveRegistration(String name, String login, String password) {
        String query = "DELETE FROM tmp_users WHERE username = :login OR name = :name";
        String insertQuery = "INSERT INTO tmp_users (name, username, password, activation, uid)" +
                " VALUE (:name, :login, :password, :activation, :uid)";
        boolean activationNotUniq;
        int i = 0;
        DuplicateKeyException keyException = new DuplicateKeyException("");
        Random random = new Random();
        long activation;
        do {
            activationNotUniq = false;
            i++;
            activation = 10000 + random.nextInt(90000);
            try {
                if (i != 1) {
                    jdbcTemplate.update(query, new MapSqlParameterSource().addValue("login", login).addValue("name", name));
                }
                String uid = UserHelper.getUserName();
                jdbcTemplate.update(insertQuery,
                        new MapSqlParameterSource().addValue("name", name)
                                .addValue("login", login)
                                .addValue("password", password)
                                .addValue("activation", activation)
                                .addValue("uid", "".equals(uid) ? null : uid));
            } catch (DuplicateKeyException duplicateKeyException) {
                if (duplicateKeyException.getMessage().indexOf("uk_activation") != -1) {
                    activationNotUniq = true;
                    keyException = duplicateKeyException;
                } else {
                    LOGGER.error("Error during saving registration", duplicateKeyException);
                    throw duplicateKeyException;
                }
            } catch (DataAccessException e) {
                LOGGER.error("Error during saving registration", e);
                throw e;
            }
        } while (activationNotUniq && i != 1000);
        if (i == 1000 && activationNotUniq) {
            throw keyException;
        }
        return activation;
    }

    /**
     * Проверить уникальность поля в базе
     *
     * @param fieldName  имя поля в базе
     * @param fieldValue значение поля введённое пользователем
     * @return true если значение поля уникальное, false в противном случае
     */
    public boolean checkFieldUnique(String fieldName, String fieldValue) {
        LOGGER.info("Test field " + fieldName + "=" + fieldValue + " on the uniqueness");
        String sql = "SELECT((SELECT COUNT(1) FROM users WHERE " + fieldName + " = :fieldValue) + " +
                "(SELECT COUNT(1) FROM tmp_users WHERE " + fieldName + " = :fieldValue " +
                "AND created BETWEEN DATE_SUB(CURRENT_TIMESTAMP(), INTERVAL 1 DAY ) AND CURRENT_TIMESTAMP()))";
        SqlParameterSource source = new MapSqlParameterSource().addValue("fieldValue", fieldValue);
        int rowCount = jdbcTemplate.queryForInt(sql, source);
        return rowCount == 0;
    }

    @Autowired
    public void init(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     * Активировать учетную запись
     *
     * @param activation код активации
     * @param uid        недорегистрированного пользователя
     * @return колличество изменённых строк
     */
    @Transactional
    public String activateRegistration(String activation, String uid) {   //TODO what does uid mean
        LOGGER.info("Activate registration with activation " + activation);
        String insertUsers = "INSERT INTO users (name, username, password)"
                + " SELECT name, username, password"
                + " FROM tmp_users WHERE tmp_users.activation = :activation";

        String queryTmpUsers = "SELECT username FROM tmp_users WHERE activation = :activation";
        MapSqlParameterSource parameterSource;
        String login = jdbcTemplate.queryForObject(queryTmpUsers, new MapSqlParameterSource("activation", activation), String.class);
        int affectedRows;
        if ("false".equals(uid)) {
            parameterSource = new MapSqlParameterSource().addValue("activation", activation);
            KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
            affectedRows = jdbcTemplate.update(insertUsers, parameterSource, generatedKeyHolder);
            if (affectedRows > 0) {
                String insertAuth = "INSERT INTO authorities (user_id, authority) VALUES (:user_id, :role)";
                String insertProfile = "INSERT INTO user_profile (user_id) VALUES (:user_id)";

                parameterSource = new MapSqlParameterSource().addValue("user_id", generatedKeyHolder.getKey().longValue());
                jdbcTemplate.update(insertProfile, parameterSource);
                parameterSource.addValue("role", Role.ROLE_USER.getValue());
                jdbcTemplate.update(insertAuth, parameterSource);
            }
        } else {
            insertUsers = "UPDATE users SET name = (SELECT name FROM tmp_users WHERE uid = :uid)," +
                    " username = (SELECT username FROM tmp_users WHERE uid = :uid)," +
                    " password = (SELECT PASSWORD FROM tmp_users WHERE uid = :uid)," +
                    " WHERE username = :uid";
            parameterSource = new MapSqlParameterSource().addValue("uid", uid);
            //обновить запись в users
            affectedRows = jdbcTemplate.update(insertUsers, parameterSource);

        }

        if (affectedRows > 0) {
            String deleteQuery = "DELETE FROM tmp_users WHERE username = :login";
            parameterSource = new MapSqlParameterSource().addValue("login", login);
            jdbcTemplate.update(deleteQuery, parameterSource);
        }
        return login;
    }

    @Transactional
    public void userRegistrationFromAdmin(String name, String username, String password) {
        String insertUsers = "INSERT INTO users (name, username, password) VALUES (:name,:username,:password)";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource().addValue("name", name).addValue("username", username)
                .addValue("password", password);

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        int affectedRows = jdbcTemplate.update(insertUsers, parameterSource, generatedKeyHolder);
        if (affectedRows > 0) {
            String insertAuth = "INSERT INTO authorities (user_id, authority) VALUES (:user_id, :role)";
            String insertProfile = "INSERT INTO user_profile (user_id) VALUES (:user_id)";
            parameterSource = new MapSqlParameterSource().addValue("user_id", generatedKeyHolder.getKey().longValue());
            jdbcTemplate.update(insertProfile, parameterSource);
            parameterSource.addValue("role", Role.ROLE_USER.getValue());
            jdbcTemplate.update(insertAuth, parameterSource);
        }
    }

//    /**
//     * Активировать учетную запись
//     *
//     * @param activation код активации
//     * @param uid        недорегистрированного пользователя
//     * @return колличество изменённых строк
//     */
//    @Transactional
//    public void activateSocialRegistration() {   //TODO what does uid mean
//        String insertAuth = "INSERT INTO authorities (user_id, authority) VALUES (:user_id, :role)";
//        String insertProfile = "INSERT INTO user_profile (user_id) VALUES (:user_id)";
//
//        MapSqlParameterSource parameterSource = new MapSqlParameterSource().addValue("user_id", UserHelper.getUserId());
//        jdbcTemplate.update(insertProfile, parameterSource);
//        parameterSource.addValue("role", Role.ROLE_USER.getValue());
//        jdbcTemplate.update(insertAuth, parameterSource);
//    }


    /**
     * Есть ли пользователь с логином login в таблицу tmp_users
     *
     * @param login логин пользователя
     * @return true если пользователь есть в таблице
     */
    public boolean isTmpUserPresent(String login) {
        String query = "SELECT COUNT(1) FROM tmp_users " +
                "WHERE (username = :login OR name = :name) " +
                "AND created BETWEEN DATE_SUB(CURRENT_TIMESTAMP(), INTERVAL 1 DAY ) AND CURRENT_TIMESTAMP()";
        try {
            return jdbcTemplate.queryForInt(query, new MapSqlParameterSource().addValue("login", login).addValue("name", login)) > 0;
        } catch (DataAccessException e) {
            LOGGER.error("error get isTmpUserPresent " + login, e);
            return false;
        }
    }

    /**
     * Есть ли пользователь с uid в таблице tmp_users
     *
     * @param uid логин пользователя
     * @return true если пользователь есть в таблице
     */
    public boolean isTmpUidPresent(String uid) {
        String query = "SELECT COUNT(1) FROM tmp_users WHERE uid =:uid ";
        try {
            return jdbcTemplate.queryForInt(query, new MapSqlParameterSource("uid", uid)) > 0;
        } catch (DataAccessException e) {
            LOGGER.error("error get isTmpUidPresent " + uid, e);
            return false;
        }
    }

    /**
     * Есть ли пользователь с кодом активации activation в таблицу tmp_users
     *
     * @param activation код активации пользователя
     * @return true если код активации есть в таблице
     */
    public boolean isActivationPresent(String activation) {
        String query = "SELECT COUNT(1) FROM tmp_users" +
                " WHERE activation = :activation" +
                " AND created BETWEEN DATE_SUB(CURRENT_TIMESTAMP(), INTERVAL 1 DAY ) AND CURRENT_TIMESTAMP()";
        try {
            return jdbcTemplate.queryForInt(query, new MapSqlParameterSource("activation", activation)) > 0;
        } catch (DataAccessException e) {
            LOGGER.error("error get isActivationPresent " + activation, e);
            return false;
        }
    }

    /**
     * Удалить регистрацию по uid
     *
     * @param uid пользователя
     * @return если удачно true, в противном случае false
     */
    public boolean deleteTmpUserByUid(String uid) {
        String query = "DELETE FROM tmp_users WHERE uid = :uid";
        try {
            return jdbcTemplate.update(query, new MapSqlParameterSource("uid", uid)) > 0;
        } catch (DataAccessException e) {
            LOGGER.error("error deleteTmpUserByUid " + uid, e);
            return false;
        }
    }

    /**
     * @param login e-mail пользователя
     * @return если удачно true, в противном случае false
     */
    public boolean deleteTmpUserByLogin(String login) {
        String query = "DELETE FROM tmp_users WHERE username = :login OR name = :name";
        try {
            return jdbcTemplate.update(query, new MapSqlParameterSource().addValue("login", login).addValue("name", login)) > 0;
        } catch (DataAccessException e) {
            LOGGER.error("error deleteTmpUserByLogin " + login, e);
            return false;
        }
    }
}
