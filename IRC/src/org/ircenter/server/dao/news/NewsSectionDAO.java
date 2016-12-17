package org.ircenter.server.dao.news;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.googlecode.ehcache.annotations.When;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.bean.news.NewsChapter;
import org.ircenter.server.bean.news.NewsPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Seledkov Kostyantyn
 * Date: 24.03.12
 * Time: 13:31
 */
@Repository("newsPartsDAO")
public class NewsSectionDAO {
    private static final Log LOGGER = LogFactory.getLog(NewsSectionDAO.class.getClass());   //TODO logger

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Cacheable(cacheName = "newsChapters")
    public Map<Integer, NewsChapter> getNewsChapters() {
        List<NewsChapter> list = null;
        Map<Integer, NewsChapter> chapterMap = new HashMap<Integer, NewsChapter>();
        String query = "SELECT * FROM news_chapter ORDER BY order_index";
        try {
            list = jdbcTemplate.query(query, new MapSqlParameterSource(), newsChapterRowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("", e);
        }

        for (NewsChapter chapter : list) {
            chapterMap.put(chapter.getChapterId(), chapter);
        }
        return chapterMap;
    }

    private final RowMapper<NewsChapter> newsChapterRowMapper = new RowMapper<NewsChapter>() {
        public NewsChapter mapRow(ResultSet rs, int rowNum) throws SQLException {
            NewsChapter newsChapter = new NewsChapter();
            newsChapter.setChapterId(rs.getInt("id"));
            newsChapter.setOrderIndex(rs.getInt("order_index"));
            newsChapter.setChapter(rs.getString("chapter"));
            return newsChapter;
        }
    };

    @TriggersRemove(cacheName = "newsParts", when = When.AFTER_METHOD_INVOCATION, removeAll = true)
    public void createPart(int chapterId, String partName) {
        String insert = "INSERT INTO news_parts VALUES(DEFAULT,:chapter_id, 1, :part_name, 0, 0)";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("chapter_id", chapterId);
        source.addValue("part_name", partName);
        try {
            jdbcTemplate.update(insert, source);
        } catch (DataAccessException e) {
            LOGGER.error("", e);
        }
    }

    @Cacheable(cacheName = "newsParts")
    public Map<Integer, NewsPart> getParts() {
        Map<Integer, NewsPart> parts = new HashMap<Integer, NewsPart>();
        List<NewsPart> list = null;
        String query = "SELECT * FROM news_parts ORDER BY order_index";
        try {
            list = jdbcTemplate.query(query, new MapSqlParameterSource(), partRowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("", e);
        }

        for (NewsPart newsPart : list) {
            parts.put(newsPart.getId(), newsPart);
        }
        return parts;
    }

    private final RowMapper<NewsPart> partRowMapper = new RowMapper<NewsPart>() {
        @Override
        public NewsPart mapRow(ResultSet resultSet, int i) throws SQLException {
            NewsPart newsPart = new NewsPart();
            newsPart.setId(resultSet.getInt("part_id"));
            newsPart.setChapterId(resultSet.getInt("chapter_id"));
            newsPart.setOrderIndex(resultSet.getInt("order_index"));
            newsPart.setName(resultSet.getString("part_name"));
            newsPart.setNewsCount(resultSet.getLong("news_count"));
            newsPart.setCommentsCount(resultSet.getLong("comments_count"));
            return newsPart;
        }
    };

    public void increaseNewsCount(NewsPart newsPart) {
        newsPart.setNewsCount(newsPart.getNewsCount() + 1);
        try {
            jdbcTemplate.update("UPDATE news_parts SET news_count = :news_count WHERE part_id = :part_id", new MapSqlParameterSource("news_count", newsPart.getNewsCount()).addValue("part_id", newsPart.getId()));
        } catch (DataAccessException e) {
            LOGGER.error("", e);
        }
    }

    public void decreaseNewsCount(NewsPart newsPart) {
        newsPart.setNewsCount(newsPart.getNewsCount() - 1);
        try {
            jdbcTemplate.update("UPDATE news_parts SET news_count = :news_count WHERE part_id = :part_id", new MapSqlParameterSource("news_count", newsPart.getNewsCount()).addValue("part_id", newsPart.getId()));
        } catch (DataAccessException e) {
            LOGGER.error("", e);
        }
    }

    public void increaseCommentsCount(NewsPart newsPart) {
        newsPart.setCommentsCount(newsPart.getCommentsCount() + 1);
        try {
            jdbcTemplate.update("UPDATE news_parts SET comments_count = :comments_count WHERE part_id = :part_id", new MapSqlParameterSource("comments_count", newsPart.getCommentsCount()).addValue("part_id", newsPart.getId()));
        } catch (DataAccessException e) {
            LOGGER.error("", e);
        }
    }

    public void decreaseCommentsCount(NewsPart newsPart) {
        newsPart.setCommentsCount(newsPart.getCommentsCount() - 1);
        try {
            jdbcTemplate.update("UPDATE news_parts SET comments_count = :comments_count WHERE part_id = :part_id", new MapSqlParameterSource("comments_count", newsPart.getCommentsCount()).addValue("part_id", newsPart.getId()));
        } catch (DataAccessException e) {
            LOGGER.error("", e);
        }
    }

    @Autowired
    public void init(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
}
