package org.ircenter.server.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.bean.Deliveries;
import org.ircenter.server.bean.feedback.FeedBack;
import org.ircenter.server.bean.feedback.FeedBackTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
 * Time: 0:04
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class DeliveriesDAO {

    private static final Log LOGGER = LogFactory.getLog(DeliveriesDAO.class.getClass());
    private NamedParameterJdbcTemplate jdbcTemplate;

    public DeliveriesDAO() {
    }

    @Autowired
    public void init(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private final RowMapper<Deliveries> rowMapper = new RowMapper<Deliveries>() {
        public Deliveries mapRow(ResultSet rs, int rowNum) throws SQLException {
            Deliveries deliveries = new Deliveries();
            deliveries.setId(rs.getLong("id"));
            deliveries.setMail(rs.getString("mail"));

            return deliveries;
        }
    };


    public void saveDeliveries(Deliveries deliveries) {
        String query = "INSERT INTO deliveries (mail) VALUES (:mail)";
        SqlParameterSource fileParameters = new MapSqlParameterSource().addValue("mail", deliveries.getMail());
        jdbcTemplate.update(query, fileParameters);
    }

    public List<Deliveries> getDeliveriesList() {
        String query = "SELECT * FROM deliveries";
        try {
            return jdbcTemplate.queryForList(query, new MapSqlParameterSource(), Deliveries.class);
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getDeliveriesList = ", e);
            return Collections.emptyList();
        }
    }
}
