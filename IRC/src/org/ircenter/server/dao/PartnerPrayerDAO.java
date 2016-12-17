package org.ircenter.server.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.bean.Partner;
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
 * Time: 21:09
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class PartnerPrayerDAO {
    private static final Log LOGGER = LogFactory.getLog(PartnerPrayerDAO.class.getClass());
    private NamedParameterJdbcTemplate jdbcTemplate;

    public PartnerPrayerDAO() {
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


    public void savePartnerPrayer(Partner partner) {
        String query = "INSERT INTO partner_prayer (name, mail, country, phone, text)" +
                " VALUES (:name, :mail, :country, :phone, :text)";
        SqlParameterSource fileParameters = new MapSqlParameterSource().addValue("name", partner.getName())
                .addValue("mail", partner.getMail())
                .addValue("country", partner.getCountry())
                .addValue("phone", partner.getPhone())
                .addValue("text", partner.getText())
                .addValue("create_date", partner.getDate());

        jdbcTemplate.update(query, fileParameters);
    }

    public List<Partner> getPartnerPrayerList() {
        String query = "SELECT * FROM partner_prayer";
        try {
            return jdbcTemplate.queryForList(query, new MapSqlParameterSource(), Partner.class);
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getPartnerPrayerList = ", e);
            return Collections.emptyList();
        }
    }

    public List<Partner> getPartnerPrayerList(int start, int limit) {
        String getPartnerList = "SELECT * FROM partner_prayer ORDER BY id DESC LIMIT :start,:limit";
        try {
            return jdbcTemplate.query(getPartnerList, new MapSqlParameterSource().addValue("start", start).addValue("limit", limit), rowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getPartnerPrayerList = ", e);
            return Collections.emptyList();
        }
    }

    public int getPartnerPrayerCount() {
        String getPartnerPrayerCount = "SELECT COUNT(1) FROM partner_prayer";
        try {
            return jdbcTemplate.queryForInt(getPartnerPrayerCount, new MapSqlParameterSource());
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getPartnerPrayerCount = ", e);
            return 0;
        }
    }

    public void removePartnerPrayer(Long id) {
        String removePartner = "DELETE FROM partner_prayer WHERE id=:id";
        try {
            jdbcTemplate.update(removePartner, new MapSqlParameterSource().addValue("id", id));
        } catch (DataAccessException e) {
            LOGGER.error("Exception while removePartnerPrayer = ", e);
        }
    }
}
