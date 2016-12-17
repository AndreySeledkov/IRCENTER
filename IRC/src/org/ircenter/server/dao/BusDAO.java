package org.ircenter.server.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.bean.Bus;
import org.ircenter.server.bean.PastorTreatment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 1
 * Date: 15.05.12
 * Time: 23:02
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class BusDAO {
    private static final Log LOGGER = LogFactory.getLog(BusDAO.class.getClass());
    private NamedParameterJdbcTemplate jdbcTemplate;

    public BusDAO() {
    }

    @Autowired
    public void init(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private final RowMapper<Bus> rowMapper = new RowMapper<Bus>() {
        public Bus mapRow(ResultSet rs, int rowNum) throws SQLException {
            Bus bus = new Bus();
            bus.setId(rs.getLong("id"));
            bus.setDefaultImage(rs.getInt("default_img"));
            bus.setDeparture(rs.getDate("departure"));
            bus.setDescription(rs.getString("descr"));
            bus.setCityId(rs.getLong("city_id"));

            return bus;
        }
    };


    public void saveBus(Bus bus) {
        String query = "INSERT INTO bus (city_id,default_img,departure,descr)" +
                " VALUES (:city_id,:default_img,:departure,:descr)";
        SqlParameterSource fileParameters = new MapSqlParameterSource().addValue("city_id", bus.getCityId())
                .addValue("default_img", bus.getDefaultImage()).addValue("departure", bus.getDeparture()).addValue("descr", bus.getDescription());
        jdbcTemplate.update(query, fileParameters);
    }


    public List<Bus> getBusList() {
        String query = "SELECT * FROM bus";
        try {
            return jdbcTemplate.query(query, new MapSqlParameterSource(), rowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getBusList = ", e);
            return null;
        }
    }

    public void removeBus(Long id) {
        String removeBus = "DELETE FROM bus WHERE id=:id";
        try {
            jdbcTemplate.update(removeBus, new MapSqlParameterSource().addValue("id", id));
        } catch (DataAccessException e) {
            LOGGER.error("Exception while removeTheme = ", e);
        }
    }
}
