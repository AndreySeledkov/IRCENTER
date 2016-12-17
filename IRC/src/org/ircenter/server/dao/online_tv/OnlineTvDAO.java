package org.ircenter.server.dao.online_tv;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.bean.OnlineTv;
import org.ircenter.server.dao.AbstractDAO;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * User: Seledkov Kostyantyn
 * Date: 25.05.12
 * Time: 12:15
 */
@Repository("onlineTvDAO")
public class OnlineTvDAO extends AbstractDAO {
    private static final Log LOGGER = LogFactory.getLog(OnlineTvDAO.class.getClass());

    private final RowMapper<OnlineTv> rowMapper = new RowMapper<OnlineTv>() {
        public OnlineTv mapRow(ResultSet rs, int rowNum) throws SQLException {
            OnlineTv onlineTv = new OnlineTv();
            onlineTv.setId(rs.getLong("id"));
            onlineTv.setDate(rs.getTimestamp("date"));
            return onlineTv;
        }
    };

    public boolean saveOnlineTv(OnlineTv onlineTv) {
        String saveOnlineTv = "INSERT INTO online_tv (date) VALUES(?)";
        try {
            return jdbcTemplate.update(saveOnlineTv, onlineTv.getDate()) > 0;
        } catch (DataAccessException e) {
            LOGGER.error("exception while insert saveOnlineTv", e);
            return false;
        }
    }


    public OnlineTv getOnlineTv() {
        String getOnlineTv = "SELECT * FROM online_tv WHERE DATE > NOW() ORDER BY TIMEDIFF(NOW(),DATE) DESC LIMIT 1";
        try {
            return jdbcTemplate.queryForObject(getOnlineTv, new Object[]{}, rowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getOnlineTv = ", e);
            return null;
        }
    }

    public List<OnlineTv> getOnlineTv10() {
        String getOnlineTv = "SELECT * FROM online_tv WHERE DATE > NOW() ORDER BY TIMEDIFF(NOW(),DATE) DESC LIMIT 10";
        try {
            return namedParameterJdbcTemplate.query(getOnlineTv, new MapSqlParameterSource(), rowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getOnlineTv = ", e);
            return null;
        }
    }

    public OnlineTv getOnlineTv(long id) {
        String getOnlineTv = "SELECT * FROM online_tv WHERE id=?";
        try {
            return jdbcTemplate.queryForObject(getOnlineTv, new Object[]{id}, rowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getOnlineTv id= ", e);
            return null;
        }
    }

    public void removeOnlineTv(Long id) {
        String removeOnlineTv = "DELETE FROM online_tv WHERE id=?";
        try {
            jdbcTemplate.update(removeOnlineTv, new Object[]{id});
        } catch (DataAccessException e) {
            LOGGER.error("Exception while removeOnlineTv = ", e);
        }
    }

    public boolean updateOnlineTv(OnlineTv onlineTv) {
        String updateOnlineTv = "UPDATE online_tv SET date=? WHERE id=?";
        try {
            return jdbcTemplate.update(updateOnlineTv, onlineTv.getDate(),
                    onlineTv.getId()) > 0;
        } catch (DataAccessException e) {
            LOGGER.error("exception updateOnlineTv", e);
            return false;
        }
    }
}
