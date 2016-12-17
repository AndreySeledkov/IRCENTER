package org.ircenter.server.dao.statistic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

@Repository
public class UserStatisticDAO {
    private static final Log LOGGER = LogFactory.getLog(UserStatisticDAO.class);
    private static JdbcTemplate jdbcTemplate;

    @Autowired
    public void init(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
    }

    public static void increaseUniqCount() {
        String query = "INSERT INTO statistic (date, uniq_count) VALUES (?, 1)" +
                " ON DUPLICATE KEY UPDATE uniq_count = uniq_count + 1";
        try {
            jdbcTemplate.update(query, new Date(new GregorianCalendar().getTimeInMillis()));
        } catch (DataAccessException e) {
            LOGGER.error("exeption while updating uniq_count", e);
        }
    }

    public static void increaseNotUniqCount() {
        String query = "INSERT INTO statistic (date, not_uniq_count)" +
                " VALUES (?, 1) ON DUPLICATE KEY UPDATE not_uniq_count = not_uniq_count + 1";
        try {
            jdbcTemplate.update(query, new Date(new GregorianCalendar().getTimeInMillis()));
        } catch (DataAccessException e) {
            LOGGER.error("exeption while updating not_uniq_count", e);
        }
    }

    /**
     * Получить статистику посещений
     *
     * @return статистика посещений
     */
    public List getStatistics() {
        String query = "SELECT * FROM statistic LIMIT 0, 100";
        try {
            return jdbcTemplate.queryForList(query);
        } catch (DataAccessException e) {
            LOGGER.error("exeption while getting statistics", e);
            return Collections.emptyList();
        }
    }
}
