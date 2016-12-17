package org.ircenter.server.dao.evidence;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.googlecode.ehcache.annotations.When;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.ircenter.server.bean.evidence.Tv;
import org.ircenter.server.bean.evidence.TvType;
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
 * Date: 10.05.12
 * Time: 17:18
 */
@Repository("tvEvidenceDAO")
public class TvDAO extends AbstractDAO {
    private static final Log LOGGER = LogFactory.getLog(TvDAO.class.getClass());
    private LuceneSearchProcessor searchProcessor;

    private final RowMapper<Tv> rowMapper = new RowMapper<Tv>() {
        public Tv mapRow(ResultSet rs, int rowNum) throws SQLException {
            Tv tv = new Tv();
            tv.setId(rs.getLong("id"));
            tv.setYoutubeId(rs.getString("youtube_id"));
            tv.setTitle(rs.getString("title"));
            tv.setLink(rs.getString("link"));
            tv.setTvType(TvType.valueOf(rs.getInt("type")));
            return tv;
        }
    };

    @TriggersRemove(cacheName = "tvevidence", when = When.AFTER_METHOD_INVOCATION, removeAll = true)
    public boolean saveTv(Tv tv) {
        String saveTextEvidence = "INSERT INTO tv (youtube_id,title,link,type) VALUES(?,?,?,?)";
        try {
            return jdbcTemplate.update(saveTextEvidence, tv.getYoutubeId(),
                    tv.getTitle(), tv.getLink(), tv.getTvType().ordinal()) > 0;
        } catch (DataAccessException e) {
            LOGGER.error("exception while insert saveTv", e);
            return false;
        }
    }

    @Cacheable(cacheName = "tvevidence")
    public Map<Long, Tv> getTV() {
        Map<Long, Tv> map = new LinkedHashMap<Long, Tv>();
        String getTV = "SELECT * FROM tv ORDER BY id DESC";
        try {
            IndexWriter writer = searchProcessor.getWriter(IndexType.TV);
            List<Tv> list = jdbcTemplate.query(getTV, rowMapper);
            for (Tv item : list) {
                map.put(item.getId(), item);
                Document document = new Document();
                document.add(new Field("id", Long.toString(item.getId()), Field.Store.YES, Field.Index.NO));
                document.add(new Field("title", item.getTitle(), Field.Store.YES, Field.Index.ANALYZED));
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
            LOGGER.error("Exception while getTV = ", e);
        }
        return map;
    }

//    public List<Tv> getTV(int start, int limit) {
//        String getTV = "SELECT * FROM tv LIMIT ?,?";
//        try {
//            return jdbcTemplate.query(getTV, new Object[]{start, limit}, rowMapper);
//        } catch (DataAccessException e) {
//            LOGGER.error("Exception while getTV = ", e);
//            return Collections.emptyList();
//        }
//    }

    @TriggersRemove(cacheName = "tvevidence", when = When.AFTER_METHOD_INVOCATION, removeAll = true)
    public void removeTv(long id) {
        String removeTv = "DELETE FROM tv WHERE id=?";
        try {
            jdbcTemplate.update(removeTv, new Object[]{id});
        } catch (DataAccessException e) {
            LOGGER.error("Exception while removeTheme = ", e);
        }
    }

//    public int getTvCount(TvType tvType) {
//        String getVideoEvidenceCount = "SELECT COUNT(1) FROM tv WHERE type=?";
//        try {
//            return jdbcTemplate.queryForInt(getVideoEvidenceCount, new Object[]{tvType.ordinal()});
//        } catch (DataAccessException e) {
//            LOGGER.error("Exception while getTvCount = ", e);
//            return 0;
//        }
//    }
//
//    public int getTvCount() {
//        String getVideoEvidenceCount = "SELECT COUNT(1) FROM tv";
//        try {
//            return jdbcTemplate.queryForInt(getVideoEvidenceCount, new Object[]{});
//        } catch (DataAccessException e) {
//            LOGGER.error("Exception while getTvCount = ", e);
//            return 0;
//        }
//    }
//
//    public Tv getTv(long id) {
//        String getTv = "SELECT * FROM tv WHERE id=?";
//        try {
//            return jdbcTemplate.queryForObject(getTv, new Object[]{id}, rowMapper);
//        } catch (DataAccessException e) {
//            LOGGER.error("Exception while getTv = ", e);
//            return null;
//        }
//    }

    @TriggersRemove(cacheName = "tvevidence", when = When.AFTER_METHOD_INVOCATION, removeAll = true)
    public boolean updateTv(Tv tv) {
        String updateTv = "UPDATE tv SET youtube_id=?,title=?,link=?,type=? WHERE id=?";
        try {
            return jdbcTemplate.update(updateTv, tv.getYoutubeId(),
                    tv.getTitle(), tv.getLink(), tv.getTvType().ordinal(), tv.getId()) > 0;
        } catch (DataAccessException e) {
            LOGGER.error("exception updateTv", e);
            return false;
        }
    }

    @Autowired
    public void setSearchProcessor(LuceneSearchProcessor searchProcessor) {
        this.searchProcessor = searchProcessor;
    }

    @PostConstruct
    public void init() {
        getTV();
    }
}
