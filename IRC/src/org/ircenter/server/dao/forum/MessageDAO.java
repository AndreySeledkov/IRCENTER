package org.ircenter.server.dao.forum;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.bean.forum.Message;
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
import java.util.List;

@Repository("messageDAO")
public class MessageDAO {

    private JdbcTemplate jdbcTemplate;
    private static final Log LOGGER = LogFactory.getLog(MessageDAO.class.getClass());
    private Database database;

    @Autowired
    public void init(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
    }

    public List<Message> getSortedList(Theme theme, int start, int count) {
        List<Message> result = new ArrayList<Message>(count);
        int realId = 0;

        List<Message> listm = getMessages(theme.getId());
        for (Message message : listm) {
            if (message.isExcluded()) {
                continue;
            }
            if (realId >= start) {
                result.add(message);
                if (result.size() == count) {
                    break;
                }
            }
            realId++;
        }
        return result;
    }

    public List<Message> getMessageList(Theme theme, int start, int limit) {
        String getMessages = "SELECT * FROM forum_message WHERE theme_id=? AND checked=TRUE ORDER by createdate LIMIT ?,?";

        RowMapper<Message> messageRowMapper = new RowMapper<Message>() {
            public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
                Message message = new Message();
                message.setId(rs.getLong("message_id"));
                message.setAuthorId(rs.getLong("forumuser_id"));
                message.setBanned(rs.getBoolean("banned"));
                message.setChecked(rs.getBoolean("checked"));
                message.setComplaints(rs.getInt("complaints"));
                message.setCreateDate(rs.getTimestamp("createdate"));
                message.setDeleted(rs.getBoolean("deleted"));
                message.setFirstMessage(rs.getBoolean("firstmessage"));
                message.setText(rs.getString("text"));
                message.setThemeId(rs.getLong("theme_id"));
                return message;
            }
        };

        try {
            return jdbcTemplate.query(getMessages, new Object[]{theme.getId(), start, limit}, messageRowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getMessages = ", e);
            return Collections.emptyList();
        }
    }


    private final RowMapper<Message> messageRowMapper = new RowMapper<Message>() {
        public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
            Message message = new Message();
            message.setId(rs.getLong("message_id"));
            message.setAuthorId(rs.getLong("forumuser_id"));
            message.setBanned(rs.getBoolean("banned"));
            message.setChecked(rs.getBoolean("checked"));
            message.setComplaints(rs.getInt("complaints"));
            message.setCreateDate(rs.getTimestamp("createdate"));
            message.setDeleted(rs.getBoolean("deleted"));
            message.setFirstMessage(rs.getBoolean("firstmessage"));
            message.setText(rs.getString("text"));
            message.setThemeId(rs.getLong("theme_id"));
            return message;
        }
    };


    public Message getFirstMessage(Theme theme) {
        String getFirstMessage = "SELECT * FROM forum_message WHERE theme_id=? and firstmessage=TRUE";

        RowMapper<Message> messageRowMapper = new RowMapper<Message>() {
            public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
                Message message = new Message();
                message.setId(rs.getLong("message_id"));
                message.setAuthorId(rs.getLong("forumuser_id"));
                message.setBanned(rs.getBoolean("banned"));
                message.setChecked(rs.getBoolean("checked"));
                message.setComplaints(rs.getInt("complaints"));
                message.setCreateDate(rs.getTimestamp("createdate"));
                message.setDeleted(rs.getBoolean("deleted"));
                message.setFirstMessage(rs.getBoolean("firstmessage"));
                message.setText(rs.getString("text"));
                message.setThemeId(rs.getLong("theme_id"));
                return message;
            }
        };

        try {
            return jdbcTemplate.queryForObject(getFirstMessage, new Object[]{theme.getId()}, messageRowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getFirstMessage = ", e);
            return null;
        }
    }

    public int getMessagesCount(Theme theme) {
        String getMessagesCount = "SELECT COUNT(1) FROM forum_messages WHERE theme_id=? AND checked=TRUE";
        try {
            return jdbcTemplate.queryForInt(getMessagesCount, new Object[]{theme.getId()});
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getMessagesCount = ", e);
            return 0;
        }
    }

    public int getMessagesCount() {
        String getMessagesCount = "SELECT COUNT(1) FROM forum_message checked=TRUE";
        try {
            return jdbcTemplate.queryForInt(getMessagesCount, new Object[]{});
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getThemesCount = ", e);
            return 0;
        }
    }


    private List<Message> getMessages(long themeId) {
        String getMessages = "SELECT * FROM forum_message WHERE theme_id=?";

        RowMapper<Message> messageRowMapper = new RowMapper<Message>() {
            public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
                Message message = new Message();
                message.setId(rs.getLong("message_id"));
                message.setAuthorId(rs.getLong("forumuser_id"));
                message.setBanned(rs.getBoolean("banned"));
                message.setChecked(rs.getBoolean("checked"));
                message.setComplaints(rs.getInt("complaints"));
                message.setCreateDate(rs.getTimestamp("createdate"));
                message.setDeleted(rs.getBoolean("deleted"));
                message.setFirstMessage(rs.getBoolean("firstmessage"));
                message.setText(rs.getString("text"));
                message.setThemeId(rs.getLong("theme_id"));
                return message;
            }
        };

        try {
            return jdbcTemplate.query(getMessages, new Object[]{themeId}, messageRowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getMessages = ", e);
            return Collections.emptyList();
        }
    }

    public Message addMessageNew(long themeId, long author, String text) {

        final String saveMessage = "INSERT INTO forum_message(theme_id,forumuser_id,createdate,quotingOn,text,firstmessage,checked,deleted,banned,complaints) VALUES(?,?,?,?,?,?,?,?,?,?)";

        boolean ban = false;// !filterResult.isOk();
        final Message message = Message.createNew(themeId, author, text, ban, ban);

        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(saveMessage, new String[]{"id"});

                    ps.setLong(1, message.getThemeId());
                    ps.setLong(2, message.getAuthorId());
                    ps.setTimestamp(3, new Timestamp(message.getCreateDate().getTime()));
                    ps.setLong(4, message.getQuotingOn());
                    ps.setString(5, message.getText());
                    ps.setBoolean(6, message.isFirstMessage());
                    ps.setBoolean(7, message.isChecked());
                    ps.setBoolean(8, message.isDeleted());
                    ps.setBoolean(9, message.isBanned());
                    ps.setInt(10, message.getComplaints());
                    return ps;
                }
            }, keyHolder);
            message.setId(keyHolder.getKey().longValue());
        } catch (DataAccessException e) {
            LOGGER.error("exception while addMessageNew", e);
        }
        return message;
    }


    public Message addMessageFirst(Theme theme, long author, String text) {
        // FilterChain filterChain = new FilterChain();
        //FilterResult filterResult = filterChain.filter(author, text);

        final String saveMessage = "INSERT INTO forum_message(theme_id,forumuser_id,createdate,quotingOn,text,firstmessage,checked,deleted,banned,complaints) VALUES(?,?,?,?,?,?,?,?,?,?)";

        boolean ban = false;// !filterResult.isOk();
        final Message message = Message.createFirst(theme, author, text, ban, ban);

        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            //jdbcTemplate.update(SAVE_THEME, theme.getPartId(), theme.getOrderIndex(), theme.getLastEdit(), theme.getNumberViews(), theme.getAuthorId(), theme.getLoginName(), theme.isDeleted(), theme.isBanned(), theme.getMessagesCount());
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(saveMessage, new String[]{"id"});

                    ps.setLong(1, message.getThemeId());
                    ps.setLong(2, message.getAuthorId());
                    ps.setTimestamp(3, new Timestamp(message.getCreateDate().getTime()));
                    ps.setLong(4, message.getQuotingOn());
                    ps.setString(5, message.getText());
                    ps.setBoolean(6, message.isFirstMessage());
                    ps.setBoolean(7, message.isChecked());
                    ps.setBoolean(8, message.isDeleted());
                    ps.setBoolean(9, message.isBanned());
                    ps.setInt(10, message.getComplaints());
                    return ps;
                }
            }, keyHolder);
            message.setId(keyHolder.getKey().longValue());
        } catch (DataAccessException e) {
            LOGGER.error("Exception while addMessageFirst", e);
        }


        saveNew(theme, message);
        return message;
    }

    private void saveNew(Theme theme, Message message) {
//        database.getPartsDAO().increaseMessages(theme.getPartId());
        database.getPartsDAO().addTheme(theme, message);
//        database.getThemeDAO().increaseMessages(message.getThemeId());
        database.getThemeDAO().addMessage(message);
        //userThemeLogDAO.addNew(message);
    }

    @Autowired
    public void setDatabase(Database database) {
        this.database = database;
    }

    public Message getLastMessage(long themeId) {
        String getMessage = "SELECT * FROM forum_message WHERE createdate=(SELECT MAX(createdate) FROM forum_message) AND theme_id=?";
        try {
            return jdbcTemplate.queryForObject(getMessage, new Object[]{themeId}, messageRowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("Exception while get Theme = ", e);
            return null;
        }
    }

    public List<Message> getUncheckedForumMessages(int start, int limit) {
        String getMessages = "SELECT * FROM forum_message WHERE checked=FALSE LIMIT ?,?";

        RowMapper<Message> messageRowMapper = new RowMapper<Message>() {
            public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
                Message message = new Message();
                message.setId(rs.getLong("message_id"));
                message.setAuthorId(rs.getLong("forumuser_id"));
                message.setBanned(rs.getBoolean("banned"));
                message.setChecked(rs.getBoolean("checked"));
                message.setComplaints(rs.getInt("complaints"));
                message.setCreateDate(rs.getDate("createdate"));
                message.setDeleted(rs.getBoolean("deleted"));
                message.setFirstMessage(rs.getBoolean("firstmessage"));
                message.setText(rs.getString("text"));
                message.setThemeId(rs.getLong("theme_id"));
                return message;
            }
        };

        try {
            return jdbcTemplate.query(getMessages, new Object[]{start, limit}, messageRowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getUncheckedForumMessages = ", e);
            return Collections.emptyList();
        }
    }

    public void removeMessage(Long id) {
        String removeMessage = "DELETE FROM forum_message WHERE message_id=?";
        try {
            jdbcTemplate.update(removeMessage, new Object[]{id});
        } catch (DataAccessException e) {
            LOGGER.error("Exception while removeMessage = ", e);
        }
    }

    public int getUncheckedMessageCount() {
        String getUncheckedMessageCount = "SELECT COUNT(1) FROM forum_message WHERE checked=FALSE";
        try {
            return jdbcTemplate.queryForInt(getUncheckedMessageCount, new Object[]{});
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getThemesCount = ", e);
            return 0;
        }
    }

    public void setCheckedMessage(Long id) {
        String getUncheckedThemesCount = "UPDATE forum_message SET checked=TRUE WHERE message_id=?";
        try {
            jdbcTemplate.update(getUncheckedThemesCount, new Object[]{id});
        } catch (DataAccessException e) {
            LOGGER.error("Exception while setCheckedMessage = ", e);
        }
    }
}