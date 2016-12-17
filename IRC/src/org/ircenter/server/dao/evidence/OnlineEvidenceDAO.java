package org.ircenter.server.dao.evidence;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.bean.evidence.OnlineEvidence;
import org.ircenter.server.bean.evidence.TextEvidence;
import org.ircenter.server.dao.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 1
 * Date: 11.05.12
 * Time: 8:00
 * To change this template use File | Settings | File Templates.
 */
//public class OnlineEvidenceDAO {
//}
@Repository("onlineEvidenceDAO")
public class OnlineEvidenceDAO {

    private JdbcTemplate jdbcTemplate;

    private static final Log LOGGER = LogFactory.getLog(TextEvidence.class.getClass());
    private Database database;


    @Autowired
    public void init(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
    }

    private final RowMapper<OnlineEvidence> rowMapper = new RowMapper<OnlineEvidence>() {
        public OnlineEvidence mapRow(ResultSet rs, int rowNum) throws SQLException {
            OnlineEvidence onlineEvidence = new OnlineEvidence();
            onlineEvidence.setId(rs.getLong("id"));
            onlineEvidence.setTitle(rs.getString("title"));
            onlineEvidence.setText(rs.getString("text"));
            onlineEvidence.setCreateDate(rs.getTimestamp("create_date"));

            return onlineEvidence;
        }
    };

    public boolean saveOnlineEvidence(OnlineEvidence onlineEvidence) {
        String saveOnlineEvidence = "INSERT INTO evidence_online (title,TEXT,create_date) VALUES(?,?,?)";
        try {
            return jdbcTemplate.update(saveOnlineEvidence, onlineEvidence.getTitle(),
                    onlineEvidence.getText(), onlineEvidence.getCreateDate()) > 0;
        } catch (DataAccessException e) {
            LOGGER.error("exception while saveOnlineEvidence", e);
            return false;
        }
    }


    public boolean updateOnlineEvidence(OnlineEvidence onlineEvidence) {
        String updateOnlineEvidence = "UPDATE evidence_online SET title=?,TEXT=? WHERE id=?";
        try {
            return jdbcTemplate.update(updateOnlineEvidence, onlineEvidence.getTitle(),
                    onlineEvidence.getText(), onlineEvidence.getId()) > 0;
        } catch (DataAccessException e) {
            LOGGER.error("exception updateOnlineEvidence", e);
            return false;
        }
    }


    public List<OnlineEvidence> getOnlineEvidence(int start, int limit) {
        String getOnlineEvidence = "SELECT * FROM evidence_online ORDER BY id DESC LIMIT ?,?";
        try {
            return jdbcTemplate.query(getOnlineEvidence, new Object[]{start, limit}, rowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getOnlineEvidence = ", e);
            return Collections.emptyList();
        }
    }

    public OnlineEvidence getOnlineEvidence(long id) {
        String getOnlineEvidence = "SELECT * FROM evidence_online WHERE id=?";
        try {
            return jdbcTemplate.queryForObject(getOnlineEvidence, new Object[]{id}, rowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getOnlineEvidence = ", e);
            return null;
        }
    }

    public void removeOnlineEvidence(Long id) {
        String removeOnlineEvidence = "DELETE FROM evidence_online WHERE id=?";
        try {
            jdbcTemplate.update(removeOnlineEvidence, new Object[]{id});
        } catch (DataAccessException e) {
            LOGGER.error("Exception while removeOnlineEvidence = ", e);
        }
    }

    @Autowired
    public void setDatabase(Database database) {
        this.database = database;
    }


    public int getOnlineEvidenceCount() {
        String getOnlineEvidenceCount = "SELECT COUNT(1) FROM evidence_online";
        try {
            return jdbcTemplate.queryForInt(getOnlineEvidenceCount, new Object[]{});
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getOnlineEvidenceCount = ", e);
            return 0;
        }
    }
}

