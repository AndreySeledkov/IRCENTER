package org.ircenter.server.dao.evidence;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.googlecode.ehcache.annotations.When;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.ircenter.server.bean.evidence.VideoEvidence;
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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Seledkov Andriy
 * Date: 10.05.12
 * Time: 17:18
 */
@Repository("videoEvidenceDAO")
public class VideoEvidenceDAO extends AbstractDAO {
    private static final Log LOGGER = LogFactory.getLog(VideoEvidenceDAO.class.getClass());
    private LuceneSearchProcessor searchProcessor;

    private final static RowMapper<VideoEvidence> rowMapper = new RowMapper<VideoEvidence>() {
        public VideoEvidence mapRow(ResultSet rs, int rowNum) throws SQLException {
            VideoEvidence videoEvidence = new VideoEvidence();
            videoEvidence.setId(rs.getLong("id"));
            videoEvidence.setTitle(rs.getString("title"));
            videoEvidence.setLink(rs.getString("link"));
            videoEvidence.setYoutubeId(rs.getString("youtube_id"));
            videoEvidence.setCountViews(rs.getInt("count_views"));
            videoEvidence.setCreateDate(rs.getDate("create_date"));
            return videoEvidence;
        }
    };

    @TriggersRemove(cacheName = "videoevidence", when = When.AFTER_METHOD_INVOCATION, removeAll = true)
    public boolean saveVideoEvidence(VideoEvidence videoEvidence) {
        String saveVideoEvidence = "INSERT INTO evidence_video (title,link,youtube_id,create_date) VALUES(?,?,?,?)";
        try {
            return jdbcTemplate.update(saveVideoEvidence,
                    videoEvidence.getTitle(), videoEvidence.getLink(), videoEvidence.getYoutubeId(), videoEvidence.getCreateDate()) > 0;
        } catch (DataAccessException e) {
            LOGGER.error("exception while insert saveVideoEvidence", e);
            return false;
        }
    }

    @Cacheable(cacheName = "videoevidence")
    public Map<Long, VideoEvidence> getVideoEvidence() {
        Map<Long, VideoEvidence> map = new LinkedHashMap<Long, VideoEvidence>();
        String getVideoEvidence = "SELECT * FROM evidence_video ORDER BY create_date DESC";
        try {
            IndexWriter writer = searchProcessor.getWriter(IndexType.VIDEO_EVIDENCE);
            List<VideoEvidence> list = jdbcTemplate.query(getVideoEvidence, rowMapper);
            for (VideoEvidence item : list) {
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
            LOGGER.error("Exception while getVideoEvidence = ", e);
        }
        return map;
    }

    @TriggersRemove(cacheName = "videoevidence", when = When.AFTER_METHOD_INVOCATION, removeAll = true)
    public void removeVideoEvidence(long id) {
        String removeTextEvidence = "DELETE FROM evidence_video WHERE id=?";
        try {
            jdbcTemplate.update(removeTextEvidence, new Object[]{id});
        } catch (DataAccessException e) {
            LOGGER.error("Exception while removeTheme = ", e);
        }
    }

//    public int getVideoEvidenceCount() {
//        String getVideoEvidenceCount = "SELECT COUNT(1) FROM evidence_video";
//        try {
//            return jdbcTemplate.queryForInt(getVideoEvidenceCount, new Object[]{});
//        } catch (DataAccessException e) {
//            LOGGER.error("Exception while getVideoEvidenceCount = ", e);
//            return 0;
//        }
//    }

//    public VideoEvidence getVideoEvidence(long id) {
//        return null;  //To change body of created methods use File | Settings | File Templates.
//    }

//    public VideoEvidence getVideoEvidence(long id) {
//        String getVideoEvidence = "SELECT * FROM evidence_video WHERE id=?";
//        try {
//            return jdbcTemplate.queryForObject(getVideoEvidence, new Object[]{id}, rowMapper);
//        } catch (DataAccessException e) {
//            LOGGER.error("Exception while getVideoEvidence = ", e);
//            return null;
//        }
//    }

    @TriggersRemove(cacheName = "videoevidence", when = When.AFTER_METHOD_INVOCATION, removeAll = true)
    public boolean updateVideoEvidence(VideoEvidence videoEvidence) {
        String saveVideoEvidence = "UPDATE evidence_video SET title=?,link=?,youtube_id=? WHERE id=?";
        try {
            return jdbcTemplate.update(saveVideoEvidence,
                    videoEvidence.getTitle(), videoEvidence.getLink(), videoEvidence.getYoutubeId(), videoEvidence.getId()) > 0;
        } catch (DataAccessException e) {
            LOGGER.error("exception updateVideoEvidence", e);
            return false;
        }
    }

    @Autowired
    public void setSearchProcessor(LuceneSearchProcessor searchProcessor) {
        this.searchProcessor = searchProcessor;
    }

    @PostConstruct
    public void init() {
        getVideoEvidence();
    }
}
