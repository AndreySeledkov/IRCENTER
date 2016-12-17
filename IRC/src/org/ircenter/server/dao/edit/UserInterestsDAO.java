package org.ircenter.server.dao.edit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.bean.edit.UserInterests;
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

@Repository("userInterestsDAO")
public class UserInterestsDAO {

    private NamedParameterJdbcTemplate jdbcTemplate;
    private static final Log LOGGER = LogFactory.getLog(UserInterestsDAO.class.getClass());

    @Autowired
    public void init(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private final RowMapper<UserInterests> rowMapper = new RowMapper<UserInterests>() {
        public UserInterests mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserInterests userInterests = new UserInterests();
            userInterests.setId(rs.getLong("id"));
            userInterests.setUserId(rs.getLong("user_id"));
            userInterests.setInterests(rs.getString("interests"));
            userInterests.setAbout(rs.getString("about"));
            userInterests.setBooks(rs.getString("books"));
            userInterests.setActivities(rs.getString("activities"));
            return userInterests;
        }
    };


    public UserInterests getUserInterestsByClientId(long id) {
        String query = "SELECT * FROM user_interests as t WHERE t.user_id = :id";
        try {
            return jdbcTemplate.queryForObject(query, new MapSqlParameterSource("id", id), rowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("error getUserInterestsByClientId = " + id, e);
            return new UserInterests();
        }
    }

    public boolean saveUserInterests(UserInterests userInterests) {
        String query = "UPDATE user_interests SET activities = :activities,interests=:interests,about=:about,books=:books WHERE id = :id";

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("activities", userInterests.getActivities())
                .addValue("interests", userInterests.getInterests())
                .addValue("about", userInterests.getAbout())
                .addValue("books", userInterests.getBooks())
                .addValue("id", userInterests.getId());

        int update = jdbcTemplate.update(query, parameterSource);

        if (update == 0) {
            String queryInsert = "INSERT INTO user_interests (user_id, activities, interests, about, books)" +
                    " VALUES (:user_id, :activities, :interests, :about, :books)";

            SqlParameterSource parameterSourceInsert = new MapSqlParameterSource()
                    .addValue("user_id", UserHelper.getUserId())
                    .addValue("activities", userInterests.getActivities())
                    .addValue("interests", userInterests.getInterests())
                    .addValue("about", userInterests.getAbout())
                    .addValue("books", userInterests.getBooks());
            return jdbcTemplate.update(queryInsert, parameterSourceInsert) > 0 ? true : false;
        }

        return update > 0 ? true : false;
    }


    public boolean saveUserInterests(String activities, String interests, String books, String about) {
        String query = "UPDATE user_interests SET activities = :activities,interests=:interests,about=:about,books=:books WHERE user_id = :user_id";

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("activities", activities)
                .addValue("interests", interests)
                .addValue("books", books)
                .addValue("about", about)
                .addValue("user_id", UserHelper.getUserId());

        int update = jdbcTemplate.update(query, parameterSource);

        if (update == 0) {
            String queryInsert = "INSERT INTO user_interests (user_id, activities, interests, about, books)" +
                    " VALUES (:user_id, :activities, :interests, :about, :books)";

            SqlParameterSource parameterSourceInsert = new MapSqlParameterSource()
                    .addValue("user_id", UserHelper.getUserId())
                    .addValue("activities", activities)
                    .addValue("interests", interests)
                    .addValue("books", books)
                    .addValue("about", about);
            return jdbcTemplate.update(queryInsert, parameterSourceInsert) > 0 ? true : false;
        }

        return update > 0 ? true : false;
    }
}
