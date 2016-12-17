package org.ircenter.server.dao.edit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.bean.edit.UserContacts;
import org.ircenter.server.service.user.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("UserContactsDAO")
public class UserContactsDAO {

    private NamedParameterJdbcTemplate jdbcTemplate;
    private static final Log LOGGER = LogFactory.getLog(UserInterestsDAO.class.getClass());

    @Autowired
    public void init(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private final RowMapper<UserContacts> rowMapper = new RowMapper<UserContacts>() {
        public UserContacts mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserContacts userContacts = new UserContacts();
            userContacts.setId(rs.getLong("id"));
            userContacts.setUserId(rs.getLong("user_id"));
            userContacts.setCellPhone(rs.getString("cell_phone"));
            userContacts.setHomePhone(rs.getString("home_phone"));
            userContacts.setOwnSite(rs.getString("own_site"));
            userContacts.setSkype(rs.getString("skype"));
            return userContacts;
        }
    };


    public boolean saveUserContacts(UserContacts userContacts) {
        String query = "UPDATE user_contacts SET cell_phone = :cell_phone,home_phone=:home_phone,skype=:skype,own_site=:own_site WHERE id = :id";

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("cell_phone", userContacts.getCellPhone())
                .addValue("home_phone", userContacts.getHomePhone())
                .addValue("skype", userContacts.getSkype())
                .addValue("own_site", userContacts.getOwnSite())
                .addValue("id", userContacts.getId());
        int update = jdbcTemplate.update(query, parameterSource);

        if (update == 0) {
            String queryInsert = "INSERT INTO user_contacts (user_id, cell_phone, home_phone, skype, own_site)" +
                    " VALUES (:user_id, :cell_phone, :home_phone, :skype, :own_site)";

            SqlParameterSource parameterSourceInsert = new MapSqlParameterSource()
                    .addValue("user_id", UserHelper.getUserId())
                    .addValue("cell_phone", userContacts.getCellPhone())
                    .addValue("home_phone", userContacts.getHomePhone())
                    .addValue("skype", userContacts.getSkype())
                    .addValue("own_site", userContacts.getOwnSite());
            return jdbcTemplate.update(queryInsert, parameterSourceInsert) > 0 ? true : false;
        }

        return update > 0 ? true : false;
    }


    public UserContacts getUserContactsByClientId(long id) {
        String query = "SELECT * FROM user_contacts as t WHERE t.user_id = :id";
        try {
            return jdbcTemplate.queryForObject(query, new MapSqlParameterSource("id", id), rowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("error getUserInterestsByClientId = " + id, e);
            return new UserContacts();
        }
    }


    public boolean saveUserContacts(String cellPhone, String homePhone, String skype, String ownSite) {
        String query = "UPDATE user_contacts SET cell_phone = :cell_phone,home_phone=:home_phone,skype=:skype,own_site=:own_site WHERE user_id = :user_id";

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("cell_phone", cellPhone)
                .addValue("home_phone", homePhone)
                .addValue("skype", skype)
                .addValue("own_site", ownSite)
                .addValue("user_id", UserHelper.getUserId());
        int update = jdbcTemplate.update(query, parameterSource);

        if (update == 0) {
            String queryInsert = "INSERT INTO user_contacts (user_id, cell_phone, home_phone, skype, own_site)" +
                    " VALUES (:user_id, :cell_phone, :home_phone, :skype, :own_site)";

            SqlParameterSource parameterSourceInsert = new MapSqlParameterSource()
                    .addValue("user_id", UserHelper.getUserId())
                    .addValue("cell_phone", cellPhone)
                    .addValue("home_phone", homePhone)
                    .addValue("skype", skype)
                    .addValue("own_site", ownSite);
            return jdbcTemplate.update(queryInsert, parameterSourceInsert) > 0 ? true : false;
        }

        return update > 0 ? true : false;
    }
}
