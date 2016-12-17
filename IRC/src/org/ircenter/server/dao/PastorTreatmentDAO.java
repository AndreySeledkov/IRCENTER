package org.ircenter.server.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.bean.Partner;
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
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 1
 * Date: 15.05.12
 * Time: 23:02
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class PastorTreatmentDAO {
    private static final Log LOGGER = LogFactory.getLog(PastorTreatmentDAO.class.getClass());
    private NamedParameterJdbcTemplate jdbcTemplate;

    public PastorTreatmentDAO() {
    }

    @Autowired
    public void init(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private final RowMapper<PastorTreatment> rowMapper = new RowMapper<PastorTreatment>() {
        public PastorTreatment mapRow(ResultSet rs, int rowNum) throws SQLException {
            PastorTreatment pastorTreatment = new PastorTreatment();
            pastorTreatment.setId(rs.getLong("id"));
            pastorTreatment.setDefaultImage(rs.getInt("default_img"));
            pastorTreatment.setText(rs.getString("text"));

            return pastorTreatment;
        }
    };


    public void savePastorTreatment(PastorTreatment pastorTreatment) {
        String query = "INSERT INTO pastor_treatment (default_img, text)" +
                " VALUES (:default_img, :text)";
        SqlParameterSource fileParameters = new MapSqlParameterSource().addValue("default_img", pastorTreatment.getDefaultImage())
                .addValue("text", pastorTreatment.getText());
        jdbcTemplate.update(query, fileParameters);
    }


    public PastorTreatment getPastorTreatment() {
        String query = "SELECT * FROM pastor_treatment LIMIT 1";
        try {
            return jdbcTemplate.queryForObject(query, new MapSqlParameterSource(), rowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getPastorTreatment = ", e);
            return null;
        }
    }

    public void removePastorTreatment(Long id) {
        String removePastorTreatment = "DELETE FROM pastor_treatment WHERE id=:id";
        try {
            jdbcTemplate.update(removePastorTreatment, new MapSqlParameterSource().addValue("id", id));
        } catch (DataAccessException e) {
            LOGGER.error("Exception while removeTheme = ", e);
        }
    }

    public boolean updatePastorTreatment(PastorTreatment pastorTreatment) {
        String updatePastorTreatment = "UPDATE pastor_treatment SET default_img=:default_img,TEXT=:text WHERE id=:id";
        try {
            return jdbcTemplate.update(updatePastorTreatment, new MapSqlParameterSource().addValue("default_img", pastorTreatment.getDefaultImage()).addValue("text", pastorTreatment.getText()).addValue("id", pastorTreatment.getId())) > 0;
        } catch (DataAccessException e) {
            LOGGER.error("exception updatePastorTreatment", e);
            return false;
        }
    }
}
