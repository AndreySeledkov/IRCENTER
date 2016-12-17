package org.ircenter.server.dao.privatemessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.bean.privatemessage.PrivateMessage;
import org.ircenter.server.dao.Database;
import org.ircenter.server.service.user.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Repository
public class PrivateMessageDAO {

    private JdbcTemplate jdbcTemplate;

    private static final Log LOGGER = LogFactory.getLog(PrivateMessageDAO.class.getClass());
    private static final int PRIVATE_MESSAGES_PER_PAGE = 3;
    private Database database;


    @Autowired
    public void init(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
    }

    private final RowMapper<PrivateMessage> rowMapper = new RowMapper<PrivateMessage>() {
        public PrivateMessage mapRow(ResultSet rs, int rowNum) throws SQLException {
            PrivateMessage privateMessage = new PrivateMessage();
            privateMessage.setCreated(rs.getTimestamp("created"));
            privateMessage.setBody(rs.getString("body"));
            long fromUserId = rs.getLong("from_user_id");
//            String name = userInfoDAO.getLoginName(fromUserId);
//            if (name != null) {
//                privateMessage.setFromUserName(name);
//            } else {
//                privateMessage.setFromUserName(UserHelper.UNREG_NAME);
//            }
            privateMessage.setMessageId(rs.getLong("message_id"));
            privateMessage.setReaded(rs.getBoolean("readed"));
            privateMessage.setFromUserId(fromUserId);
            if (fromUserId == UserHelper.getUserId()) {
                privateMessage.setReaded(true);
            }
            privateMessage.setToUserId(rs.getLong("to_user_id"));
            return privateMessage;
        }
    };

    /**
     * Добавить приватное сообщение в БД
     *
     * @param privateMessage приватное сообщение
     */
    public int insertPrivateMessage(PrivateMessage privateMessage) {
        String insertPrivateMessage = "INSERT INTO private_messages (body, to_user_id, from_user_id) VALUES (?, ?, ?)";

        try {
            return jdbcTemplate.update(insertPrivateMessage, privateMessage.getBody(),
                    privateMessage.getToUserId(), UserHelper.getUserId());
        } catch (DataAccessException e) {
            LOGGER.error("Exception while insert privateMessage", e);
            return 0;
        }
    }

    /**
     * Получить приватные сообщения для пользователя
     *
     * @param page страница пагинатора
     * @param in   входящие или исходящие
     * @return приватные сообщения для пользователя
     */
    public List getPrivateMessages(int page, boolean in) {
        String selectPrivateMessagesTo = "SELECT * FROM private_messages WHERE to_user_id = ? ORDER BY message_id DESC LIMIT ?, ?";
        String selectPrivateMessagesFrom = "SELECT * FROM private_messages WHERE from_user_id = ? ORDER BY message_id DESC LIMIT ?, ?";

        Long id = UserHelper.getUserId();
        try {
            return jdbcTemplate.query(in ? selectPrivateMessagesTo : selectPrivateMessagesFrom,
                    new Object[]{id, page * PRIVATE_MESSAGES_PER_PAGE, PRIVATE_MESSAGES_PER_PAGE}, rowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("exception while getting privateMessages for userId = " + id, e);
            return Collections.emptyList();
        }
    }

    /**
     * Получить все приватные сообщения для пользователя
     *
     * @param page страница пагинатора
     * @return все приватные сообщения для пользователя
     */
    public List getAllPrivateMessages(int page) {
        String selectAllPrivateMessages = "SELECT * FROM private_messages WHERE  to_user_id = ? OR from_user_id = ? ORDER BY message_id DESC LIMIT ?, ?";

        Long id = UserHelper.getUserId();
        try {
            return jdbcTemplate.query(selectAllPrivateMessages, new Object[]{id, id,
                    page * PRIVATE_MESSAGES_PER_PAGE, PRIVATE_MESSAGES_PER_PAGE}, rowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("exception while getting privateMessages for userId = " + id, e);
            return Collections.emptyList();
        }
    }

//    @Autowired
//    public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
//        this.userInfoDAO = userInfoDAO;
//    }

    /**
     * Получить приватное сообщение
     *
     * @param id приватного сообщения
     * @return приватное сообщение
     */
    public PrivateMessage getPrivateMessage(Long id) {
        String selectPrivateMessage = "SELECT * FROM private_messages WHERE message_id = ? AND (to_user_id = ? OR from_user_id = ?)";

        Long userId = UserHelper.getUserId();
        try {
            return jdbcTemplate.queryForObject(selectPrivateMessage, new Object[]{id, userId, userId}, rowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getting privateMessage with id = " + id + " for userId = " + userId, e);
            return new PrivateMessage();
        }
    }

    /**
     * Получить колличество приватных сообщений
     *
     * @param in входящие или исходящие
     * @return колличество приватных сообщений
     */
    public int getPrivateMessageCount(boolean in) {
        String selectAllCountTo = "SELECT COUNT(1) FROM private_messages WHERE to_user_id = ?";
        String selectAllCountFrom = "SELECT COUNT(1) FROM private_messages WHERE from_user_id = ?";

        Long id = UserHelper.getUserId();
        try {
            return jdbcTemplate.queryForInt(in ? selectAllCountTo : selectAllCountFrom, id);
        } catch (DataAccessException e) {
            LOGGER.error("exception while getting privateMessage count for userid = " + id, e);
            return 0;
        }
    }

    /**
     * Получить колличество приватных сообщений
     *
     * @return колличество приватных сообщений
     */
    public long getAllPrivateMessageCount() {
        String selectAllCount = "SELECT COUNT(1) FROM private_messages WHERE to_user_id = ? OR from_user_id = ?";

        Long id = UserHelper.getUserId();
        try {
            return jdbcTemplate.queryForLong(selectAllCount, id, id);
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getting privateMessage count for userid = " + id, e);
            return 0;
        }
    }

    /**
     * Получить колличество приватных сообщений
     *
     * @param in входящие или исходящие
     * @return колличество приватных сообщений
     */
    public long getALLPrivateMessageCount(boolean in) {
        String selectAllCountTo = "SELECT COUNT(1) FROM private_messages WHERE to_user_id = ?";
        String selectAllCountFrom = "SELECT COUNT(1) FROM private_messages WHERE from_user_id = ?";

        Long id = UserHelper.getUserId();
        try {
            return jdbcTemplate.queryForLong(in ? selectAllCountTo : selectAllCountFrom, id);
        } catch (DataAccessException e) {
            LOGGER.error("exception while getting privateMessage count for userid = " + id, e);
            return 0;
        }
    }

    /**
     * Получить колличество приватных сообщений
     *
     * @return колличество приватных сообщений
     */
    public long getNotReadedPrivateMessageCount() {
        String getNotReadedPrivateMessageCount = "SELECT COUNT(1) FROM private_messages WHERE to_user_id = ? AND readed = 0";

        Long id = UserHelper.getUserId();
        try {
            return jdbcTemplate.queryForLong(getNotReadedPrivateMessageCount, id);
        } catch (DataAccessException e) {
            LOGGER.error("exception while getting not readed privateMessage count for userid = " + id, e);
            return 0;
        }
    }

    /**
     * Обновить признак прочитанности у поста
     *
     * @param id поста
     */
    public void updateReaded(long id) {
        String updateReaded = "UPDATE private_messages SET readed = 1 WHERE message_id = ?";
        try {
            jdbcTemplate.update(updateReaded, id);
        } catch (DataAccessException e) {
            LOGGER.error("Exception while update readed for privateMessageid = " + id, e);
        }
    }

    /**
     * Удалить личное сообщение
     *
     * @param messageId id личного сообщения
     * @return колличество удалённых строк
     */
    public int deletePrivateMessage(Long messageId) {
        String deletePrivateMessage = "DELETE FROM private_messages" +
                " WHERE (to_user_id = ? OR from_user_id = ?) AND message_id = ?";
        try {
            Long userId = UserHelper.getUserId();
            return jdbcTemplate.update(deletePrivateMessage, userId, userId, messageId);
        } catch (DataAccessException e) {
            LOGGER.error("Exception while deleting PrivateMessage for messageId = " + messageId, e);
            return -1;
        }
    }

    @Autowired
    public void setDatabase(Database database) {
        this.database = database;
    }
}
