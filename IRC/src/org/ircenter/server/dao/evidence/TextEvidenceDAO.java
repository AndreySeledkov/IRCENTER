package org.ircenter.server.dao.evidence;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
 * Date: 10.05.12
 * Time: 17:18
 * To change this template use File | Settings | File Templates.
 */
@Repository("textEvidenceDAO")
public class TextEvidenceDAO {

    private JdbcTemplate jdbcTemplate;

    private static final Log LOGGER = LogFactory.getLog(TextEvidence.class.getClass());
    private Database database;


    @Autowired
    public void init(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
    }

    private final RowMapper<TextEvidence> rowMapper = new RowMapper<TextEvidence>() {
        public TextEvidence mapRow(ResultSet rs, int rowNum) throws SQLException {
            TextEvidence textEvidence = new TextEvidence();
            textEvidence.setId(rs.getLong("id"));
            textEvidence.setDefaultImage(rs.getInt("default_image"));
            textEvidence.setTitle(rs.getString("title"));
            textEvidence.setText(rs.getString("text"));
            textEvidence.setCountViews(rs.getInt("count_views"));
            textEvidence.setCreateDate(rs.getDate("create_date"));
            return textEvidence;
        }
    };


    public boolean saveTextEvidence(TextEvidence textEvidence) {
        String saveTextEvidence = "INSERT INTO evidence_text (default_image,title,TEXT,create_date) VALUES(?,?,?,?)";
        try {
            return jdbcTemplate.update(saveTextEvidence, textEvidence.getDefaultImage(),
                    textEvidence.getTitle(), textEvidence.getText(), textEvidence.getCreateDate()) > 0;
        } catch (DataAccessException e) {
            LOGGER.error("exception while insert saveTv", e);
            return false;
        }
    }

    public boolean updateTextEvidence(TextEvidence textEvidence) {
        String saveTextEvidence = "UPDATE evidence_text SET default_image=?,title=?,TEXT=? WHERE id=?";
        try {
            return jdbcTemplate.update(saveTextEvidence, textEvidence.getDefaultImage(),
                    textEvidence.getTitle(), textEvidence.getText(), textEvidence.getId()) > 0;
        } catch (DataAccessException e) {
            LOGGER.error("exception updateTextEvidence", e);
            return false;
        }
    }

    public void setViewCount(TextEvidence textEvidence, int count) {
        textEvidence.setCountViews(textEvidence.getCountViews() + count);
        String update = "UPDATE evidence_text SET count_views = ? WHERE id = ?";
        try {
            jdbcTemplate.update(update, new Object[]{textEvidence.getCountViews(), textEvidence.getId()});
        } catch (DataAccessException e) {
            LOGGER.error("Exception while setViewCount =", e);
        }
    }


    public List<TextEvidence> getTextEvidence(int start, int limit) {
        String getThemesCount = "SELECT * FROM evidence_text ORDER BY id DESC LIMIT ?,?";
        try {
            return jdbcTemplate.query(getThemesCount, new Object[]{start, limit}, rowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getThemesCount = ", e);
            return Collections.emptyList();
        }
    }

    public TextEvidence getTextEvidence(long id) {
        String getThemesCount = "SELECT * FROM evidence_text WHERE id=?";
        try {
            return jdbcTemplate.queryForObject(getThemesCount, new Object[]{id}, rowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getThemesCount = ", e);
            return null;
        }
    }

    public void removeTextEvidence(Long id) {
        String removeTextEvidence = "DELETE FROM evidence_text WHERE id=?";
        try {
            jdbcTemplate.update(removeTextEvidence, new Object[]{id});
        } catch (DataAccessException e) {
            LOGGER.error("Exception while removeTheme = ", e);
        }
    }

    @Autowired
    public void setDatabase(Database database) {
        this.database = database;
    }


    public int getTextEvidenceCount() {
        String getTextEvidenceCount = "SELECT COUNT(1) FROM evidence_text";
        try {
            return jdbcTemplate.queryForInt(getTextEvidenceCount, new Object[]{});
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getThemesCount = ", e);
            return 0;
        }
    }
}
