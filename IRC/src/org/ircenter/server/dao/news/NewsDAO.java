package org.ircenter.server.dao.news;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.googlecode.ehcache.annotations.When;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.ircenter.server.bean.news.PieceNews;
import org.ircenter.server.dao.AbstractDAO;
import org.ircenter.server.lucene.IndexType;
import org.ircenter.server.lucene.LuceneSearchProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Seledkov Kostyantyn
 * Date: 24.03.12
 * Time: 13:15
 */
@Repository("newsDAO")
public class NewsDAO extends AbstractDAO {

    private static final Log LOGGER = LogFactory.getLog(NewsDAO.class.getClass());
    private LuceneSearchProcessor searchProcessor;

    private final static RowMapper<PieceNews> pieceNewsRowMapper = new RowMapper<PieceNews>() {
        @Override
        public PieceNews mapRow(ResultSet resultSet, int i) throws SQLException {
            PieceNews pieceNews = new PieceNews();
            pieceNews.setId(resultSet.getLong("news_id"));
            pieceNews.setPartId(resultSet.getInt("part_id"));
            pieceNews.setOrderIndex(resultSet.getInt("order_index"));
            pieceNews.setStartDate(resultSet.getDate("start_date"));
            pieceNews.setStopDate(resultSet.getDate("stop_date"));
            pieceNews.setViewCount(resultSet.getInt("view_count"));
            pieceNews.setAuthorId(resultSet.getLong("author_id"));
            pieceNews.setTitle(resultSet.getString("title"));
            pieceNews.setText(resultSet.getString("info"));
            pieceNews.setImageId(resultSet.getInt("image_id"));
            pieceNews.setDeleted(resultSet.getBoolean("deleted"));
            pieceNews.setBanned(resultSet.getBoolean("banned"));
            pieceNews.setCommentsCount(resultSet.getInt("comments_count"));
            return pieceNews;
        }
    };

    @Cacheable(cacheName = "news")
    public Map<Long, PieceNews> getNews() {
        Map<Long, PieceNews> map = new LinkedHashMap<Long, PieceNews>();
        String query = "SELECT * FROM news AS t1 ORDER BY t1.start_date DESC";
        try {
            IndexWriter writer = searchProcessor.getWriter(IndexType.NEWS);
            List<PieceNews> list = jdbcTemplate.query(query, pieceNewsRowMapper);
            for (PieceNews el : list) {
                map.put(el.getId(), el);
                Document document = new Document();
                document.add(new Field("id", Long.toString(el.getId()), Field.Store.YES, Field.Index.NO));
                document.add(new Field("title", el.getTitle(), Field.Store.YES, Field.Index.ANALYZED));
                document.add(new Field("info", el.getText(), Field.Store.YES, Field.Index.ANALYZED));
                try {
                    writer.addDocument(document);
                } catch (IOException e) {
                    LOGGER.error("", e);
                }
            }
            try {
                writer.close();
            } catch (IOException e) {
                LOGGER.error("", e);
            }

        } catch (DataAccessException e) {
            LOGGER.error("", e);
        }
        return map;
    }

    @TriggersRemove(cacheName = "news", when = When.AFTER_METHOD_INVOCATION, removeAll = true)
    public void addNews(PieceNews news) {
        String query = "INSERT INTO news VALUES (DEFAULT,:part_id,:order_index,:start_date,:stop_date,:view_count,:author_id," +
                ":title,:info,:image_id,:deleted,:banned,:comments_count)";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("part_id", news.getPartId());
        source.addValue("order_index", news.getOrderIndex());
        source.addValue("start_date", news.getStartDate());
        source.addValue("stop_date", news.getStopDate());
        source.addValue("view_count", news.getViewCount());
        source.addValue("author_id", news.getAuthorId());
        source.addValue("title", news.getTitle());
        source.addValue("info", news.getText());
        source.addValue("image_id", news.getImageId());
        source.addValue("deleted", news.isDeleted());
        source.addValue("banned", news.isBanned());
        source.addValue("comments_count", news.getCommentsCount());
        try {
            namedParameterJdbcTemplate.update(query, source);
        } catch (DataAccessException e) {
            LOGGER.error("", e);
        }
    }

    @TriggersRemove(cacheName = "news", when = When.AFTER_METHOD_INVOCATION, removeAll = true)
    public void updateNews(PieceNews item) {
        String query = "UPDATE news SET part_id = :part_id,start_date = :start_date,title = :title,info = :info,image_id = :image_id WHERE news_id = :news_id";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("part_id", item.getPartId());
        source.addValue("start_date", item.getStartDate());
        source.addValue("title", item.getTitle());
        source.addValue("info", item.getText());
        source.addValue("image_id", item.getImageId());
        source.addValue("news_id", item.getId());
        try {
            namedParameterJdbcTemplate.update(query, source);
        } catch (DataAccessException e) {
            LOGGER.error("", e);
        }
    }

    @TriggersRemove(cacheName = "news", when = When.AFTER_METHOD_INVOCATION, removeAll = true)
    public void removeNews(long newsId) {
        String query = "DELETE FROM news WHERE news_id = :id";
        try {
            namedParameterJdbcTemplate.update(query, new MapSqlParameterSource().addValue("id", newsId));
        } catch (DataAccessException e) {
            LOGGER.error("", e);
        }
    }

    public void increaseCommentsCount(PieceNews pieceNews) {
        pieceNews.setCommentsCount(pieceNews.getCommentsCount() + 1);
        String update = "UPDATE news SET comments_count = :comments_count WHERE news_id = :news_id";
        try {
            namedParameterJdbcTemplate.update(update, new MapSqlParameterSource("comments_count", pieceNews.getCommentsCount()).addValue("news_id", pieceNews.getId()));
        } catch (DataAccessException e) {
            LOGGER.error("", e);
        }
    }

    public void decrementCommentsCount(PieceNews pieceNews) {
        pieceNews.setCommentsCount(pieceNews.getCommentsCount() - 1);
        String update = "UPDATE news SET comments_count = :comments_count WHERE news_id = :news_id";
        try {
            namedParameterJdbcTemplate.update(update, new MapSqlParameterSource("comments_count", pieceNews.getCommentsCount()).addValue("news_id", pieceNews.getId()));
        } catch (DataAccessException e) {
            LOGGER.error("", e);
        }
    }

    public void setViewCount(PieceNews pieceNews, int count) {
        pieceNews.setViewCount(pieceNews.getViewCount() + count);
        String update = "UPDATE news SET view_count = :view_count WHERE news_id = :news_id";
        try {
            namedParameterJdbcTemplate.update(update, new MapSqlParameterSource("view_count", pieceNews.getViewCount()).addValue("news_id", pieceNews.getId()));
        } catch (DataAccessException e) {
            LOGGER.error("", e);
        }
    }

    @Autowired
    public void setSearchProcessor(LuceneSearchProcessor searchProcessor) {
        this.searchProcessor = searchProcessor;
    }
}