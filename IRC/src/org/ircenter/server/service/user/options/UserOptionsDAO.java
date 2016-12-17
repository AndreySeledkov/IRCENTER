package org.ircenter.server.service.user.options;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.googlecode.ehcache.annotations.When;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.bean.user.options.PrivacyOptions;
import org.ircenter.server.bean.user.options.UserOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User: Seledkov Kostyantyn
 * Date: 23.04.12
 * Time: 10:27
 */
public class UserOptionsDAO {
    private static final Log LOGGER = LogFactory.getLog(UserOptionsDAO.class.getClass());
    private NamedParameterJdbcTemplate jdbcTemplate;

    public UserOptionsDAO() {
    }

    @Cacheable(cacheName = "userOptions")
    public UserOptions loadUserOptions(long userId) {
        final UserOptions options = new UserOptions(userId);
        final PrivacyOptions privacyOpt = options.getPrivacyOptions();

        try {
            jdbcTemplate.query("SELECT * FROM user_options WHERE user_id = :user_id", new MapSqlParameterSource("user_id", userId), new RowMapper<UserOptions>() {

                @Override
                public UserOptions mapRow(ResultSet resultSet, int i) throws SQLException {
                    options.setExistsInDb(true);
                    options.setMessageNotification(resultSet.getInt("message_notification"));
                    privacyOpt.setWriteMessage(PrivacyOptions.fromInt(resultSet.getInt("privacy_write_message")));
                    return options;
                }
            });
        } catch (DataAccessException e) {
            LOGGER.error("", e);
            options.setDefault();
        }
        if (!options.isExistsInDb()) {
            options.setDefault();
        }
        return options;
    }

    @TriggersRemove(cacheName = "userOptions", when = When.AFTER_METHOD_INVOCATION, removeAll = false)
    public boolean saveUserOptions(UserOptions options) {
        String update;
        boolean ok = false;
        if (options.isExistsInDb()) {
            update = "UPDATE user_options SET "
                    + " message_notification=" + options.getMessageNotification().getValue()
                    + " WHERE user_id=" + options.getOwnerId();
            ok = jdbcTemplate.update(update, new MapSqlParameterSource()) > 0;
        } else {
            update = "INSERT INTO user_options(user_id, message_notification) " +
                    "VALUES(:user_id,:message_notification) " +
                    "ON DUPLICATE KEY UPDATE message_notification=VALUES(message_notification)";
            ok = jdbcTemplate.update(update, new MapSqlParameterSource()) > 0;
        }
        return ok;
    }

    public boolean savePrivacyOptions(UserOptions options) {
        PrivacyOptions privacyOptions = options.getPrivacyOptions();
        String update;
        if (options.isExistsInDb()) {
            update = "UPDATE user_options SET "
                    + ",privacy_write_message=" + PrivacyOptions.toInt(privacyOptions.getWriteMessage());
        } else {
            update = "INSERT INTO user_options (user_id,privacy_write_message) " +
                    "VALUES (" + options.getOwnerId()
                    + "," + PrivacyOptions.toInt(privacyOptions.getWriteMessage())
                    + ") ON DUPLICATE KEY UPDATE privacy_write_message=VALUES(privacy_write_message)";
        }
        boolean ok = jdbcTemplate.update(update, new MapSqlParameterSource()) > 0;
        if (ok) {
            options.setExistsInDb(true);
        }
        return ok;
    }

    @Autowired
    public void setJdbcTemplate(DataSource source) {
        jdbcTemplate = new NamedParameterJdbcTemplate(source);
    }
}
