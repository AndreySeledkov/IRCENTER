package org.ircenter.server.dao.evidence;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.bean.evidence.EvidenceHealing;
import org.ircenter.server.bean.evidence.MinutesToGod;
import org.ircenter.server.bean.evidence.VideoEvidence;
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
 * Date: 16.05.12
 * Time: 21:24
 * To change this template use File | Settings | File Templates.
 */
@Repository("evidenceHealingDAO")
public class EvidenceHealingDAO {
    private JdbcTemplate jdbcTemplate;

    private static final Log LOGGER = LogFactory.getLog(EvidenceHealingDAO.class.getClass());
    private Database database;


    @Autowired
    public void init(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
    }

    private final RowMapper<EvidenceHealing> rowMapper = new RowMapper<EvidenceHealing>() {
        public EvidenceHealing mapRow(ResultSet rs, int rowNum) throws SQLException {
            EvidenceHealing evidenceHealing = new EvidenceHealing();
            evidenceHealing.setId(rs.getLong("id"));
            evidenceHealing.setDefaultImage(rs.getInt("default_img"));
            evidenceHealing.setText(rs.getString("text"));
            return evidenceHealing;
        }
    };

    public boolean saveEvidenceHealing(EvidenceHealing evidenceHealing) {
        String saveEvidenceHealing = "INSERT INTO evidence_healing (default_img,text) VALUES(?,?)";
        try {
            return jdbcTemplate.update(saveEvidenceHealing, evidenceHealing.getDefaultImage(),
                    evidenceHealing.getText()) > 0;
        } catch (DataAccessException e) {
            LOGGER.error("exception while insert saveEvidenceHealing", e);
            return false;
        }
    }


    public EvidenceHealing getEvidenceHealing() {
        String getEvidenceHealing = "SELECT * FROM evidence_healing LIMIT 1";
        try {
            return jdbcTemplate.queryForObject(getEvidenceHealing, new Object[]{}, rowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getEvidenceHealing = ", e);
            return null;
        }
    }

    public EvidenceHealing getEvidenceHealing(long id) {
        String getEvidenceHealing = "SELECT * FROM evidence_healing WHERE id=?";
        try {
            return jdbcTemplate.queryForObject(getEvidenceHealing, new Object[]{id}, rowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getEvidenceHealing id= ", e);
            return null;
        }
    }

    public void removeEvidenceHealing(Long id) {
        String removeMinutesToGod = "DELETE FROM evidence_healing WHERE id=?";
        try {
            jdbcTemplate.update(removeMinutesToGod, new Object[]{id});
        } catch (DataAccessException e) {
            LOGGER.error("Exception while removeEvidenceHealing = ", e);
        }
    }

    @Autowired
    public void setDatabase(Database database) {
        this.database = database;
    }

//    public void updateEvidenceHealing(EvidenceHealing evidenceHealing) {
//        //To change body of created methods use File | Settings | File Templates.
//    }

    public boolean updateEvidenceHealing(EvidenceHealing evidenceHealing) {
        String updateEvidenceHealing = "UPDATE evidence_healing SET default_img=?,TEXT=? WHERE id=?";
        try {
            return jdbcTemplate.update(updateEvidenceHealing, evidenceHealing.getDefaultImage(),
                    evidenceHealing.getText(), evidenceHealing.getId()) > 0;
        } catch (DataAccessException e) {
            LOGGER.error("exception updateEvidenceHealing", e);
            return false;
        }
    }
}
