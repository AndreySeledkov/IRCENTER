package org.ircenter.server.dao.evidence;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.googlecode.ehcache.annotations.When;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.ircenter.server.bean.evidence.MinutesToGod;
import org.ircenter.server.dao.AbstractDAO;
import org.ircenter.server.lucene.IndexType;
import org.ircenter.server.lucene.LuceneSearchProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Seledkov Kostyantyn
 * Date: 13.05.12
 * Time: 23:13
 */
@Repository("minutesToGodDAO")

public class MinutesToGodDAO extends AbstractDAO {

    private static final Log LOGGER = LogFactory.getLog(MinutesToGodDAO.class.getClass());
    private LuceneSearchProcessor searchProcessor;

    private final static RowMapper<MinutesToGod> rowMapper = new RowMapper<MinutesToGod>() {
        public MinutesToGod mapRow(ResultSet rs, int rowNum) throws SQLException {
            MinutesToGod textEvidence = new MinutesToGod();
            textEvidence.setId(rs.getLong("id"));
            textEvidence.setTitle(rs.getString("title"));
            textEvidence.setYoutubeId(rs.getString("youtube_id"));
            textEvidence.setText(rs.getString("text"));
            textEvidence.setLink(rs.getString("link"));
            textEvidence.setCountViews(rs.getInt("count_views"));
            textEvidence.setCreatedDate(rs.getDate("create_date"));
            return textEvidence;
        }
    };

    @TriggersRemove(cacheName = "minutestogod", when = When.AFTER_METHOD_INVOCATION, removeAll = true)
    public boolean saveMinutesToGod(MinutesToGod minutesToGod) {
        String saveMinutesToGod = "INSERT INTO minutes_to_god (title,youtube_id,text,link,count_views,create_date) VALUES(?,?,?,?,?,?)";
        try {
            return jdbcTemplate.update(saveMinutesToGod, minutesToGod.getTitle(),
                    minutesToGod.getYoutubeId(), minutesToGod.getText(), minutesToGod.getLink(), minutesToGod.getCountViews(), minutesToGod.getCreatedDate()) > 0;
        } catch (DataAccessException e) {
            LOGGER.error("exception while insert saveMinutesToGod", e);
            return false;
        }
    }

    @Cacheable(cacheName = "minutestogod")
    public Map<Long, MinutesToGod> getMinutesToGod() {
        Map<Long, MinutesToGod> map = new LinkedHashMap<Long, MinutesToGod>();
        String getMinutesToGod = "SELECT * FROM minutes_to_god ORDER BY create_date DESC";
        try {
            IndexWriter writer = searchProcessor.getWriter(IndexType.MINUTES_TO_GOD);
            List<MinutesToGod> list = jdbcTemplate.query(getMinutesToGod, rowMapper);
            for (MinutesToGod item : list) {
                map.put(item.getId(), item);
                Document document = new Document();
                document.add(new Field("id", Long.toString(item.getId()), Field.Store.YES, Field.Index.NO));
                document.add(new Field("title", item.getTitle(), Field.Store.YES, Field.Index.ANALYZED));
                document.add(new Field("text", item.getText(), Field.Store.YES, Field.Index.ANALYZED));
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
            LOGGER.error("Exception while getMinuteToGod = ", e);
        }
        return map;
    }

//    public MinutesToGod getMinutesToGod(long id) {
//        String getMinutesToGod = "SELECT * FROM minutes_to_god WHERE id=?";
//        try {
//            return jdbcTemplate.queryForObject(getMinutesToGod, new Object[]{id}, rowMapper);
//        } catch (DataAccessException e) {
//            LOGGER.error("Exception while getMinuteToGod = ", e);
//            return null;
//        }
//    }

    @TriggersRemove(cacheName = "minutestogod", when = When.AFTER_METHOD_INVOCATION, removeAll = true)
    public void removeMinutesToGod(long id) {
        String removeMinutesToGod = "DELETE FROM minutes_to_god WHERE id=?";
        try {
            jdbcTemplate.update(removeMinutesToGod, new Object[]{id});
        } catch (DataAccessException e) {
            LOGGER.error("Exception while removeMinuteToGod = ", e);
        }
    }

//    public int getMinutesToGodCount() {
//        String getMinutesToGodCount = "SELECT COUNT(1) FROM minutes_to_god";
//        try {
//            return jdbcTemplate.queryForInt(getMinutesToGodCount, new Object[]{});
//        } catch (DataAccessException e) {
//            LOGGER.error("Exception while getThemesCount = ", e);
//            return 0;
//        }
//    }

    @TriggersRemove(cacheName = "minutestogod", when = When.AFTER_METHOD_INVOCATION, removeAll = true)
    public boolean updateMinutesToGod(MinutesToGod minutesToGod) {
        String updateMinutesToGod = "UPDATE minutes_to_god SET title=?,youtube_id=?,TEXT=?,link=? WHERE id=?";
        try {
            return jdbcTemplate.update(updateMinutesToGod, minutesToGod.getTitle(),
                    minutesToGod.getYoutubeId(), minutesToGod.getText(), minutesToGod.getLink(), minutesToGod.getId()) > 0;
        } catch (DataAccessException e) {
            LOGGER.error("exception updateMinutesToGod", e);
            return false;
        }
    }

    @TriggersRemove(cacheName = "minutestogod", when = When.AFTER_METHOD_INVOCATION, removeAll = true)
    public void setViewCount(MinutesToGod minutesToGod, int count) {
        minutesToGod.setCountViews(minutesToGod.getCountViews() + count);
        String update = "UPDATE minutes_to_god SET count_views = ? WHERE id = ?";
        try {
            jdbcTemplate.update(update, new Object[]{minutesToGod.getCountViews(), minutesToGod.getId()});
        } catch (DataAccessException e) {
            LOGGER.error("Exception while setViewCount =", e);
        }
    }

    @Autowired
    public void setSearchProcessor(LuceneSearchProcessor searchProcessor) {
        this.searchProcessor = searchProcessor;
    }

    @PostConstruct
    public void init() {
        getMinutesToGod();
    }
}
