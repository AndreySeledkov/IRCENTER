package org.ircenter.server.dao.news;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.googlecode.ehcache.annotations.When;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.bean.news.NewsComment;
import org.ircenter.server.dao.Database;
import org.ircenter.server.service.user.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * User: Seledkov Kostyantyn
 * Date: 28.03.12
 * Time: 0:31
 */
@Repository
public class NewsCommentDAO {
    private NamedParameterJdbcTemplate jdbcTemplate;

    private Database database;
    private static final Log LOGGER = LogFactory.getLog(NewsCommentDAO.class.getClass());

    public NewsCommentDAO() {
    }

    @Cacheable(cacheName = "newsComments", keyGeneratorName = "newsPageKeyGenerator")
    public List<NewsComment> getNewsComments(long newsId, int skipCount, int recordCount) {
        List<NewsComment> comments;
        String query = "SELECT * FROM news_comments WHERE news_id=" + newsId + " ORDER BY ID LIMIT " + skipCount + "," + recordCount;
        try {
            comments = jdbcTemplate.query(query, new MapSqlParameterSource(), new RowMapper<NewsComment>() {
                @Override
                public NewsComment mapRow(ResultSet resultSet, int i) throws SQLException {
                    NewsComment comment = new NewsComment();
                    comment.setNewsId(resultSet.getLong("news_id"));
                    long userId = resultSet.getLong("user_id");
                    comment.setUserId(userId);
                    UserProfile profile = database.getUserInfoDAO().getUser(userId);
                    if (profile == null) {
                        comment.setName("Удаленный пользователь");
                    } else {
                        comment.setName(profile.getLoginName());
                    }
                    comment.setDate(resultSet.getTimestamp("date"));
                    comment.setText(resultSet.getString("text"));
                    return comment;
                }
            });
        } catch (DataAccessException e) {
            LOGGER.error("", e);
            comments = Collections.EMPTY_LIST;
        }
        return comments;
    }

    @TriggersRemove(cacheName = "newsComments", when = When.AFTER_METHOD_INVOCATION, removeAll = true)
    public void addNewsComment(NewsComment newsComment) {
        String insert = "INSERT INTO news_comments(news_id, user_id, date, text) VALUES(:news_id, :user_id, :date, :text)";
        try {
            jdbcTemplate.update(insert, new MapSqlParameterSource("news_id", newsComment.getNewsId()).
                    addValue("user_id", newsComment.getUserId()).
                    addValue("date", newsComment.getDate()).
                    addValue("text", newsComment.getText()));
        } catch (DataAccessException e) {
            LOGGER.error("", e);
        }
    }

    @Autowired
    public void setDatabase(Database database) {
        this.database = database;
    }

    @Autowired
    public void init(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
}
