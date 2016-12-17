package org.ircenter.server.dao.forum;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.bean.forum.*;
import org.ircenter.server.bean.forum.Theme;

import org.ircenter.server.dao.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Repository("themeDAO")
public class ThemeDAO {

    private JdbcTemplate jdbcTemplate;
    private final static Log LOGGER = LogFactory.getLog(ThemeDAO.class.getClass());
    private Database database;

    private static final String GET_THEME_INDEX = "SELECT theme_id FROM forum_theme WHERE part_id=? and deleted=false and banned=false order by orderIndex desc, lastEdit desc, theme_id";
    private static final String SAVE_THEME = "INSERT INTO forum_theme (part_id, orderIndex, lastEdit, numberViews, author, themename, deleted, banned, messages) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String INCREASE_MESSAGES = "UPDATE forum_theme SET messages=? WHERE theme_id=?";
    private static final String INCREASE_VIEWS = "UPDATE forum_theme SET numberViews=? WHERE theme_id=?";

    private static final String SET_LAST_EDIT = "UPDATE forum_theme SET lastEdit=? WHERE theme_id=?";
    private static final String GET_THEME = "SELECT * FROM forum_theme WHERE theme_id=?";


    @Autowired
    public void init(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
    }

    private final RowMapper<Long> themeIdRowMapper = new RowMapper<Long>() {
        public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
            return rs.getLong("theme_id");

        }
    };

    private final RowMapper<Theme> themeRowMapper = new RowMapper<Theme>() {
        public Theme mapRow(ResultSet rs, int rowNum) throws SQLException {
            Theme theme = new Theme();
            theme.setId(rs.getLong("theme_id"));
            theme.setPartId(rs.getLong("part_id"));
            theme.setOrderIndex(rs.getInt("orderIndex"));
            theme.setLastEdit(rs.getTimestamp("lastEdit"));
            theme.setNumberViews(rs.getInt("numberViews"));
            theme.setAuthorId(rs.getLong("author"));
            theme.setName(rs.getString("themename"));
            theme.setDeleted(rs.getBoolean("deleted"));
            theme.setBanned(rs.getBoolean("banned"));
            theme.setMessagesCount(rs.getInt("messages"));
            theme.setChecked(rs.getBoolean("checked"));

            return theme;
        }
    };

    //    static final String TABLENAME = "forum_theme";
//    private Ehcache cache;
//
//    public ThemeDAO(Database database) {
//        super(database);
//        cache = CacheHelper.createSelfPopulatingCache(new Cache("ForumThemeCache", 25000, false, false, 0, 60 * 60 * 24), new ThemeLoader(database, TABLENAME));
//    }
//
    //todo  getSortedList
    public List<Theme> getSortedList(Part part, int start, int count) {
        int last = start + count;
        List<Long> listid;
        List<Theme> list;
        synchronized (part) {
            int size = part.getThemes().size();
            if (size < last) {
                last = size;
            }
            listid = part.getThemes().subList(start, last);
            list = new ArrayList<Theme>(listid.size());
            for (Long id : listid) {
                list.add(get(id));
            }
        }
        return list;
    }


    public List<Theme> getThemes(Part part, int start, int limit) {
        String selectThemesId = "SELECT * FROM forum_theme WHERE part_id=? AND checked=TRUE LIMIT ?,?";
        try {
            return jdbcTemplate.query(selectThemesId, new Object[]{part.getId(), start, limit}, themeRowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getThemes = ", e);
            return Collections.emptyList();
        }
    }

    public Theme getLastEditTheme(long partId) {
        String getTheme = "SELECT * FROM forum_theme WHERE lastEdit=(SELECT MAX(lastEdit) FROM forum_theme) AND part_id=? AND CHECKED=TRUE LIMIT 1";
        try {
            return jdbcTemplate.queryForObject(getTheme, new Object[]{partId}, themeRowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("Exception while get Theme = ", e);
            return null;
        }
    }

    public Theme get(long id) {
        String getTheme = "SELECT * FROM forum_theme WHERE theme_id=?";
        try {
            return jdbcTemplate.queryForObject(getTheme, new Object[]{id}, themeRowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("Exception while get Theme = ", e);
            return null;
        }
    }

    public void increaseViews(long themeId) {
        String increaseView = "UPDATE forum_theme SET numberViews=? WHERE theme_id=?";

        Theme theme = get(themeId);
        theme.setNumberViews(theme.getNumberViews() + 1);
        try {
            jdbcTemplate.update(increaseView, theme.getNumberViews(), themeId);
        } catch (DataAccessException e) {
            LOGGER.error("exception while insert privateMessage", e);
        }
    }

    public List<Long> getThemesIndex(Part part) {
        String getThemeIndex = "SELECT theme_id FROM forum_theme WHERE part_id=? and deleted=false and banned=false order by orderIndex desc, lastEdit desc, theme_id";

        try {
            return jdbcTemplate.query(getThemeIndex, new Object[]{part.getId()}, themeIdRowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("exeption while getThemesIndex = ", e);
            return Collections.emptyList();
        }
    }

    private void setLastEditNow(Theme theme) {
        theme.setLastEdit(new Date());

        String setLastEdit = "UPDATE forum_theme SET lastEdit=? WHERE theme_id=?";
        try {
            jdbcTemplate.update(setLastEdit, new Timestamp(theme.getLastEdit().getTime()), theme.getId());
        } catch (DataAccessException e) {
            LOGGER.error("exception while insert privateMessage", e);
        }


    }

    public void addMessage(Message message) {
        if (message.isExcluded()) {
            return;
        }
        Theme theme = get(message.getThemeId());
        setLastEditNow(theme);
    }

    public Theme addTheme(long author, long partId, String themeName, String text) {
        // FilterChain filterChain = new FilterChain();
        // FilterResult filterResultT = filterChain.filter(author, themeName);
        // FilterResult filterResultM = filterChain.filter(author, text);

        //boolean ban = !filterResultT.isOk() || !filterResultM.isOk();

        boolean ban = false;

        Theme theme = new Theme(partId, author, themeName, ban, ban);
        saveNew(theme);
        database.getMessageDAO().addMessageFirst(theme, author, text);
        return theme;
    }


    private void saveNew(final Theme theme) {
        final String saveTheme = "INSERT INTO forum_theme (part_id, orderIndex, lastEdit, numberViews, author, themename, deleted, banned, messages) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(saveTheme, new String[]{"id"});

                    ps.setLong(1, theme.getPartId());
                    ps.setInt(2, theme.getOrderIndex());
                    ps.setTimestamp(3, new Timestamp(theme.getLastEdit().getTime()));
                    ps.setInt(4, theme.getNumberViews());
                    ps.setLong(5, theme.getAuthorId());
                    ps.setString(6, theme.getName());
                    ps.setBoolean(7, theme.isDeleted());
                    ps.setBoolean(8, theme.isBanned());
                    ps.setInt(9, theme.getMessagesCount());
                    return ps;
                }
            }, keyHolder);
            theme.setId(keyHolder.getKey().longValue());
        } catch (DataAccessException e) {
            LOGGER.error("exception while insert saveNew", e);
        }
    }

    public void increaseMessages(long themeId) {
        String increaseMessages = "UPDATE forum_theme SET messages=? WHERE theme_id=?";

        Theme theme = get(themeId);
        theme.setMessagesCount(theme.getMessagesCount() + 1);
        try {
            jdbcTemplate.update(increaseMessages, theme.getMessagesCount(), themeId);
        } catch (DataAccessException e) {
            LOGGER.error("exception while insert privateMessage", e);
        }

    }

    @Autowired
    public void setDatabase(Database database) {
        this.database = database;
    }

    public int getThemesCount(Part part) {
        String getThemesCount = "SELECT COUNT(1) FROM forum_theme WHERE part_id=?";
        try {
            return jdbcTemplate.queryForInt(getThemesCount, new Object[]{part.getId()});
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getThemesCount = ", e);
            return 0;
        }
    }

    public int getThemesCount() {
        String getThemesCount = "SELECT COUNT(1) FROM forum_theme WHERE checked=TRUE";
        try {
            return jdbcTemplate.queryForInt(getThemesCount, new Object[]{});
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getThemesCount = ", e);
            return 0;
        }
    }


    public List<Theme> getUncheckedTheme(int start, int limit) {
        String getThemesCount = "SELECT * FROM forum_theme WHERE checked=FALSE LIMIT ?,?";
        try {
            return jdbcTemplate.query(getThemesCount, new Object[]{start, limit}, themeRowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getThemesCount = ", e);
            return Collections.emptyList();
        }
    }

    public void removeTheme(Long id) {
        String getThemesCount = "DELETE FROM forum_theme WHERE theme_id=?";
        try {
            jdbcTemplate.update(getThemesCount, new Object[]{id});
        } catch (DataAccessException e) {
            LOGGER.error("Exception while removeTheme = ", e);
        }
    }

    public int getUncheckedThemesCount() {
        String getUncheckedThemesCount = "SELECT COUNT(1) FROM forum_theme WHERE checked=FALSE";
        try {
            return jdbcTemplate.queryForInt(getUncheckedThemesCount, new Object[]{});
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getUncheckedThemesCount = ", e);
            return 0;
        }
    }

    public void setCheckedTheme(long id) {
        String getUncheckedThemesCount = "UPDATE  forum_theme SET checked=TRUE WHERE theme_id=?";
        try {
            jdbcTemplate.update(getUncheckedThemesCount, new Object[]{id});
        } catch (DataAccessException e) {
            LOGGER.error("Exception while setCheckedTheme = ", e);
        }
    }
}
