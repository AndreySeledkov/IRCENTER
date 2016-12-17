package org.ircenter.server.dao.evidence;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.googlecode.ehcache.annotations.When;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.ircenter.server.bean.evidence.SecretSpiritualWorld;
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
 * User: Seledkov Andriy
 * Date: 13.05.12
 * Time: 23:13
 */
@Repository("secretSpiritWorldDAO")
public class SecretSpiritualWorldDAO extends AbstractDAO {
    private static final Log LOGGER = LogFactory.getLog(SecretSpiritualWorldDAO.class.getClass());
    private LuceneSearchProcessor searchProcessor;

    private final static RowMapper<SecretSpiritualWorld> rowMapper = new RowMapper<SecretSpiritualWorld>() {
        public SecretSpiritualWorld mapRow(ResultSet rs, int rowNum) throws SQLException {
            SecretSpiritualWorld textEvidence = new SecretSpiritualWorld();
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

    @TriggersRemove(cacheName = "secretspiritualworld", when = When.AFTER_METHOD_INVOCATION, removeAll = true)
    public boolean saveSecretSpiritualWorld(SecretSpiritualWorld minutesToGod) {
        String saveMinutesToGod = "INSERT INTO secret_spirit_world (title,youtube_id,link,text,count_views,create_date) VALUES(?,?,?,?,?,?)";
        try {
            return jdbcTemplate.update(saveMinutesToGod, minutesToGod.getTitle(),
                    minutesToGod.getYoutubeId(), minutesToGod.getLink(), minutesToGod.getText(), minutesToGod.getCountViews(), minutesToGod.getCreatedDate()) > 0;
        } catch (DataAccessException e) {
            LOGGER.error("exception while insert saveSecretSpiritualWorld", e);
            return false;
        }
    }

    @Cacheable(cacheName = "secretspiritualworld")
    public Map<Long, SecretSpiritualWorld> getSecretSpiritualWorld() {
        Map<Long, SecretSpiritualWorld> map = new LinkedHashMap<Long, SecretSpiritualWorld>();
        String getMinutesToGod = "SELECT * FROM secret_spirit_world ORDER BY create_date DESC";
        try {
            IndexWriter writer = searchProcessor.getWriter(IndexType.SECRET_SPIRITUAL_WORLD);
            List<SecretSpiritualWorld> list = jdbcTemplate.query(getMinutesToGod, rowMapper);
            for (SecretSpiritualWorld item : list) {
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
            LOGGER.error("Exception while getSecretSpiritualWorld = ", e);
        }
        return map;
    }

//    public SecretSpiritualWorld getSecretSpiritualWorld(long id) {
//        String getMinutesToGod = "SELECT * FROM secret_spirit_world WHERE id=?";
//        try {
//            return jdbcTemplate.queryForObject(getMinutesToGod, new Object[]{id}, rowMapper);
//        } catch (DataAccessException e) {
//            LOGGER.error("Exception while getSecretSpiritualWorld = ", e);
//            return null;
//        }
//    }

    @TriggersRemove(cacheName = "secretspiritualworld", when = When.AFTER_METHOD_INVOCATION, removeAll = true)
    public void removeSecretSpiritualWorld(long id) {
        String removeMinutesToGod = "DELETE FROM secret_spirit_world WHERE id=?";
        try {
            jdbcTemplate.update(removeMinutesToGod, new Object[]{id});
        } catch (DataAccessException e) {
            LOGGER.error("Exception while removeSecretSpiritualWorld = ", e);
        }
    }

//    public int getSecretSpiritualWorldCount() {
//        String getMinutesToGodCount = "SELECT COUNT(1) FROM secret_spirit_world";
//        try {
//            return jdbcTemplate.queryForInt(getMinutesToGodCount, new Object[]{});
//        } catch (DataAccessException e) {
//            LOGGER.error("Exception while getSecretSpiritualWorld = ", e);
//            return 0;
//        }
//    }

    @TriggersRemove(cacheName = "secretspiritualworld", when = When.AFTER_METHOD_INVOCATION, removeAll = true)
    public boolean updateSecretSpiritualWorld(SecretSpiritualWorld secretSpiritualWorld) {
        String updateSecretSpiritualWorld = "UPDATE secret_spirit_world SET title=?,youtube_id=?,TEXT=?,link=? WHERE id=?";
        try {
            return jdbcTemplate.update(updateSecretSpiritualWorld, secretSpiritualWorld.getTitle(),
                    secretSpiritualWorld.getYoutubeId(), secretSpiritualWorld.getText(), secretSpiritualWorld.getLink(), secretSpiritualWorld.getId()) > 0;
        } catch (DataAccessException e) {
            LOGGER.error("exception updateSecretSpiritualWorld", e);
            return false;
        }
    }

    @TriggersRemove(cacheName = "secretspiritualworld", when = When.AFTER_METHOD_INVOCATION, removeAll = true)
    public void setViewCount(SecretSpiritualWorld secretSpiritualWorld, int count) {
        secretSpiritualWorld.setCountViews(secretSpiritualWorld.getCountViews() + count);
        String update = "UPDATE secret_spirit_world SET count_views = ? WHERE id = ?";
        try {
            jdbcTemplate.update(update, new Object[]{secretSpiritualWorld.getCountViews(), secretSpiritualWorld.getId()});
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
        getSecretSpiritualWorld();
    }
}
