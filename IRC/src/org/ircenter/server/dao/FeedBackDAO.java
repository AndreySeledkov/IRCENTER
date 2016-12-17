package org.ircenter.server.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.bean.evidence.MinutesToGod;
import org.ircenter.server.bean.feedback.FeedBack;
import org.ircenter.server.bean.feedback.FeedBackTheme;
import org.ircenter.server.bean.friends.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 1
 * Date: 06.05.12
 * Time: 21:57
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class FeedBackDAO {

    private static final Log LOGGER = LogFactory.getLog(FeedBackDAO.class.getClass());
    private NamedParameterJdbcTemplate jdbcTemplate;

    public FeedBackDAO() {
    }

    @Autowired
    public void init(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private final RowMapper<FeedBack> rowMapper = new RowMapper<FeedBack>() {
        public FeedBack mapRow(ResultSet rs, int rowNum) throws SQLException {
            FeedBack feedBack = new FeedBack();
            feedBack.setId(rs.getLong("id"));
            feedBack.setFirstName(rs.getString("first_name"));
            feedBack.setLastName(rs.getString("last_name"));
            feedBack.setMail(rs.getString("mail"));
            feedBack.setTheme(FeedBackTheme.dbValueOf(rs.getInt("theme")));
            feedBack.setInfo(rs.getString("info"));
            feedBack.setDate(rs.getDate("create_date"));

            return feedBack;
        }
    };


    public long saveFeedBack(FeedBack feedBack) {
        String query = "INSERT INTO feedback (first_name, last_name, mail, theme, info,create_date)" +
                " VALUES (:first_name, :last_name, :mail, :theme, :info,:create_date)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource fileParameters = new MapSqlParameterSource().addValue("first_name", feedBack.getFirstName())
                .addValue("last_name", feedBack.getLastName())
                .addValue("mail", feedBack.getMail())
                .addValue("theme", feedBack.getTheme().ordinal())
                .addValue("info", feedBack.getInfo())
                .addValue("create_date", feedBack.getDate());

        try {
            jdbcTemplate.update(query, fileParameters, keyHolder);
        } catch (DataAccessException e) {
            LOGGER.error("", e);
        }
        return (Long) keyHolder.getKey();
    }

    public List<FeedBack> getFeedBackList() {
        String query = "SELECT * FROM feedback";
        try {
            return jdbcTemplate.queryForList(query, new MapSqlParameterSource(), FeedBack.class);
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getFeedBackList = ", e);
            return Collections.emptyList();
        }
    }

    public List<FeedBack> getFeedBackList(int start, int limit) {
        String getFeedBackList = "SELECT * FROM feedback ORDER BY create_date DESC LIMIT :start,:limit";
        try {
            return jdbcTemplate.query(getFeedBackList, new MapSqlParameterSource().addValue("start", start).addValue("limit", limit), rowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getFeedBackList = ", e);
            return Collections.emptyList();
        }
    }

    public int getFeedBackCount() {
        String getFeedBackCount = "SELECT COUNT(1) FROM feedback";
        try {
            return jdbcTemplate.queryForInt(getFeedBackCount, new MapSqlParameterSource());
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getFeedBackCount = ", e);
            return 0;
        }
    }


    public void removeFeedBack(Long id) {
        String removeFeedBack = "DELETE FROM feedback WHERE id=:id";
        try {
            jdbcTemplate.update(removeFeedBack, new MapSqlParameterSource().addValue("id", id));
        } catch (DataAccessException e) {
            LOGGER.error("Exception while removeFeedBack = ", e);
        }
    }
}
