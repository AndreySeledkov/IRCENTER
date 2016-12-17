package org.ircenter.server.dao.forum;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.bean.forum.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("settingDAO")
public class SettingDAO {

    private JdbcTemplate jdbcTemplate;
    private final static Log LOGGER = LogFactory.getLog(SettingDAO.class.getClass());

    private static final String GET_SETTING_BY_USER_ID = "SELECT * FROM forum_settings WHERE userId=?";


    @Autowired
    public void init(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
    }

    private final RowMapper<Settings> settingRowMapper = new RowMapper<Settings>() {
        public Settings mapRow(ResultSet rs, int rowNum) throws SQLException {
            Settings settings = new Settings();
//            private long userId;
//            private Integer version;
//            private boolean showAvatar = true;
//            private boolean showLocation = true;
//            private Date canWriteFrom;
//            private int countBans;
//            private int showRules;
//            private long lastNews;

            settings.setUserId(rs.getLong("userId"));
            settings.setVersion(rs.getInt("version"));
            settings.setShowAvatar(rs.getBoolean("showAvatar"));
            settings.setShowLocation(rs.getBoolean("showLocation"));
            settings.setCanWriteFrom(rs.getDate("canWritefrom"));
            settings.setCountBans(rs.getInt("countBans"));
            settings.setShowRules(rs.getInt("showRules"));
            settings.setLastNews(rs.getDate("lastnews").getTime());


            return settings;
        }
    };


    public Settings getSettings(long userId) {
        try {
            return jdbcTemplate.queryForObject(GET_SETTING_BY_USER_ID, new Object[]{userId}, settingRowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("exeption while getThemesIndex = ", e);
            return null;
        }
    }
}
