package org.ircenter.server.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.bean.Partner;
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
 * Date: 15.05.12
 * Time: 21:09
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class PartnerDAO {
    private static final Log LOGGER = LogFactory.getLog(PartnerDAO.class.getClass());
    private NamedParameterJdbcTemplate jdbcTemplate;

    public PartnerDAO() {
    }

    @Autowired
    public void init(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private final RowMapper<Partner> rowMapper = new RowMapper<Partner>() {
        public Partner mapRow(ResultSet rs, int rowNum) throws SQLException {
            Partner partner = new Partner();
            partner.setId(rs.getLong("id"));
            partner.setName(rs.getString("name"));
            partner.setMail(rs.getString("mail"));
            partner.setCountry(rs.getInt("country"));
            partner.setPhone(rs.getString("phone"));
            partner.setText(rs.getString("text"));

            return partner;
        }
    };


    public void savePartner(Partner partner) {
        String query = "INSERT INTO partner (name, mail, country, phone, text)" +
                " VALUES (:name, :mail, :country, :phone, :text)";
        SqlParameterSource fileParameters = new MapSqlParameterSource().addValue("name", partner.getName())
                .addValue("mail", partner.getMail())
                .addValue("country", partner.getCountry())
                .addValue("phone", partner.getPhone())
                .addValue("text", partner.getText())
                .addValue("create_date", partner.getDate());

        jdbcTemplate.update(query, fileParameters);
    }

    public List<Partner> getPartnerList() {
        String query = "SELECT * FROM partner";
        try {
            return jdbcTemplate.queryForList(query, new MapSqlParameterSource(), Partner.class);
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getPartnerList = ", e);
            return Collections.emptyList();
        }
    }

    public List<Partner> getPartnerList(int start, int limit) {
        String getPartnerList = "SELECT * FROM partner ORDER BY create_date DESC LIMIT :start,:limit";
        try {
            return jdbcTemplate.query(getPartnerList, new MapSqlParameterSource().addValue("start", start).addValue("limit", limit), rowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getFeedBackList = ", e);
            return Collections.emptyList();
        }
    }

    public int getPartnerCount() {
        String getPartnerCount = "SELECT COUNT(1) FROM partner";
        try {
            return jdbcTemplate.queryForInt(getPartnerCount, new MapSqlParameterSource());
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getPartnerCount = ", e);
            return 0;
        }
    }

    public void removePartner(Long id) {
        String removePartner = "DELETE FROM partner WHERE id=:id";
        try {
            jdbcTemplate.update(removePartner, new MapSqlParameterSource().addValue("id", id));
        } catch (DataAccessException e) {
            LOGGER.error("Exception while removePartner = ", e);
        }
    }
}
