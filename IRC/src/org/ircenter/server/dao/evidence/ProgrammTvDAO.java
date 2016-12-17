package org.ircenter.server.dao.evidence;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.bean.evidence.OnlineEvidence;
import org.ircenter.server.bean.evidence.ProgrammTv;
import org.ircenter.server.bean.evidence.ProgrammTvType;
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
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 1
 * Date: 15.05.12
 * Time: 17:54
 * To change this template use File | Settings | File Templates.
 */
@Repository("programmTvDAO")
public class ProgrammTvDAO {

    private JdbcTemplate jdbcTemplate;

    private static final Log LOGGER = LogFactory.getLog(ProgrammTvDAO.class.getClass());
    private Database database;


    @Autowired
    public void init(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
    }

    private final RowMapper<ProgrammTv> rowMapper = new RowMapper<ProgrammTv>() {
        public ProgrammTv mapRow(ResultSet rs, int rowNum) throws SQLException {
            ProgrammTv programmTv = new ProgrammTv();
            programmTv.setId(rs.getLong("id"));
            programmTv.setTitle(rs.getString("title"));
            programmTv.setDays(rs.getString("days"));
            programmTv.setProgrammTvType(ProgrammTvType.valueOf(rs.getInt("type")));
            programmTv.setDate(rs.getTimestamp("date"));

            return programmTv;
        }
    };

    public boolean saveProgrammTv(ProgrammTv programmTv) {
        String saveProgrammTv = "INSERT INTO programm_tv (title,days,date,type) VALUES(?,?,?,?)";
        try {
            return jdbcTemplate.update(saveProgrammTv, programmTv.getTitle(), programmTv.getDays(),
                    programmTv.getDate(), programmTv.getProgrammTvType().ordinal()) > 0;
        } catch (DataAccessException e) {
            LOGGER.error("exception while insert saveProgrammTv", e);
            return false;
        }
    }

    public void removeProgrammTv(Long id) {
        String removeProgrammTv = "DELETE FROM programm_tv WHERE id=?";
        try {
            jdbcTemplate.update(removeProgrammTv, new Object[]{id});
        } catch (DataAccessException e) {
            LOGGER.error("Exception while removeProgrammTv = ", e);
        }
    }

    public List<ProgrammTv> getProgrammTvByType(ProgrammTvType programmTvType) {
        String getOnlineEvidence = "SELECT * FROM programm_tv WHERE type=? ORDER BY id DESC";
        try {
            return jdbcTemplate.query(getOnlineEvidence, new Object[]{programmTvType.ordinal()}, rowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getProgrammTvByType = ", e);
            return Collections.emptyList();
        }
    }

    public List<ProgrammTv> getProgrammTv() {
        String getProgrammTv = "SELECT * FROM programm_tv ORDER BY id DESC";
        try {
            return jdbcTemplate.query(getProgrammTv, new Object[]{}, rowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getProgrammTv = ", e);
            return Collections.emptyList();
        }
    }

    public ProgrammTv getProgrammTv(long id) {
        String getProgrammTv = "SELECT * FROM programm_tv WHERE id=?";
        try {
            return jdbcTemplate.queryForObject(getProgrammTv, new Object[]{id}, rowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getProgrammTv = ", e);
            return null;
        }
    }

//    public void updateProgrammTv(ProgrammTv programmTv) {
//        //To change body of created methods use File | Settings | File Templates.
//    }

    public boolean updateProgrammTv(ProgrammTv programmTv) {
        String updateProgrammTv = "UPDATE programm_tv SET title=?,days=?,date=?,type=? WHERE id=?";
        try {
            return jdbcTemplate.update(updateProgrammTv, programmTv.getTitle(), programmTv.getDays(),
                    new Timestamp(programmTv.getDate().getTime()), programmTv.getProgrammTvType().ordinal(), programmTv.getId()) > 0;
        } catch (DataAccessException e) {
            LOGGER.error("exception updateMinutesToGod", e);
            return false;
        }
    }
}
