package org.ircenter.server.dao.friends;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.bean.friends.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository("friendsDAO")
public class FriendsDAO {

    private JdbcTemplate jdbcTemplate;

    private static final Log LOGGER = LogFactory.getLog(FriendsDAO.class.getClass());


    @Autowired
    public void init(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
    }

    private final RowMapper<Friend> rowMapper = new RowMapper<Friend>() {
        public Friend mapRow(ResultSet rs, int rowNum) throws SQLException {
            Friend friend = new Friend();
            friend.setId(rs.getLong("id"));
            friend.setFriendDate(rs.getDate("friend_date"));
            friend.setFriendId(rs.getLong("otherClient_id"));
            friend.setSelfId(rs.getLong("selfClient_id"));

            return friend;
        }
    };

    public boolean insertFriend(Friend friend) {
        String insertFriend = "INSERT INTO friends (friend_date,otherClient_id,selfClient_id) VALUES(?,?,?)";
        try {
            return jdbcTemplate.update(insertFriend, friend.getFriendDate(),
                    friend.getFriendId(), friend.getSelfId()) > 0;
        } catch (DataAccessException e) {
            LOGGER.error("exception while insert insertFriend", e);
            return false;
        }
    }

    public Friend getFriendById(long id) {
        return null;
    }

    public List<Long> getFriendsId(long selfId) {
        List<Long> friends = new ArrayList<Long>();


        return friends;
    }

    public boolean remove(long selfId, long friendId) {
        String remove = "DELETE FROM friends WHERE selfClient_id=? && otherClient_id=? OR selfClient_id=? && otherClient_id=?";

        try {
            return jdbcTemplate.update(remove, selfId, friendId, friendId, selfId) > 0;
        } catch (DataAccessException e) {
            LOGGER.error("error remove " + friendId, e);
            return false;
        }

    }

    public boolean isFriends(long fromClientId, long toClientId) {
        String isFriends = "SELECT COUNT(*) FROM friends WHERE selfClient_id = ? AND otherClient_id = ?";
        try {
            return jdbcTemplate.queryForInt(isFriends, fromClientId, toClientId) > 0;
        } catch (DataAccessException e) {
            LOGGER.error("error isFriends fromClientId=" + fromClientId + ",toClientId=" + toClientId, e);
            return false;
        }
    }

    public int getFriendsCount(long selfId) {
        String selectFriendsId = "SELECT COUNT(1) FROM friends WHERE selfClient_id=?";
        try {
            return jdbcTemplate.queryForInt(selectFriendsId, new Object[]{selfId});
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getFriendsCount = ", e);
            return 0;
        }
    }

    public List<Friend> getFriendsClientId(long selfId, int start, int limit) {
        String selectFriendsId = "SELECT * FROM friends WHERE selfClient_id=? LIMIT ?,?";
        try {
            return jdbcTemplate.query(selectFriendsId, new Object[]{selfId, start, limit}, rowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getFriendsClientId = ", e);
            return Collections.emptyList();
        }
    }

    public List<Friend> getFriendsClientId(long selfId) {
        String selectFriendsId = "SELECT * FROM friends WHERE selfClient_id=?";
        try {
            return jdbcTemplate.query(selectFriendsId, new Object[]{selfId}, rowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getFriendsClientId = ", e);
            return Collections.emptyList();
        }
    }


}
