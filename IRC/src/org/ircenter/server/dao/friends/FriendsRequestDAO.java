package org.ircenter.server.dao.friends;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.bean.friends.FriendRequest;
import org.ircenter.server.service.user.UserHelper;
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

@Repository("friendsRequestDAO")
public class FriendsRequestDAO {

    private JdbcTemplate jdbcTemplate;

    private static final Log LOGGER = LogFactory.getLog(FriendsRequestDAO.class.getClass());

    @Autowired
    public void init(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
    }

    private final RowMapper<FriendRequest> rowMapper = new RowMapper<FriendRequest>() {
        public FriendRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
            FriendRequest friendRequest = new FriendRequest();
            friendRequest.setId(rs.getLong("id"));
            friendRequest.setDate(rs.getDate("requestDate"));
            friendRequest.setRequestClientFrom(rs.getLong("fromClient_id"));
            friendRequest.setRequestClientTo(rs.getLong("toClient_id"));
            return friendRequest;
        }
    };

    public List<FriendRequest> getAllRequestFriend(long clientId, int start, int limit) {
        String selectRequestFriend = "SELECT * FROM friends_requests WHERE toClient_id = ? LIMIT ?,?";

        try {
            return jdbcTemplate.query(selectRequestFriend, new Object[]{clientId, start, limit}, rowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("exeption while getAllRequestFriend = ", e);
            return Collections.emptyList();
        }
    }


    public FriendRequest getRequestFriendByID(long id) {
        return null;

    }

    public List<Long> getRequestsId(long selfId) {
        List<Long> friendsRequest = new ArrayList<Long>();

        return friendsRequest;
    }

    public boolean isRequest(long selfId, long otherId) {
        String isRequest = "SELECT COUNT(*) FROM friends_requests WHERE fromClient_id = ? AND toClient_id = ?";

        try {
            return jdbcTemplate.queryForInt(isRequest, otherId, selfId) > 0;
        } catch (DataAccessException e) {
            LOGGER.error("error isRequest selfId=" + selfId + ",otherId=" + otherId, e);
            return false;
        }
    }

    public int getRequestCount(long toClientId) {
        String getRequestCount = "SELECT COUNT(*) FROM friends_requests WHERE toClient_id = ?";
        try {
            return jdbcTemplate.queryForInt(getRequestCount, UserHelper.getUserId());
        } catch (DataAccessException e) {
            LOGGER.error("exception while getting privateMessage count for userid = " + toClientId, e);
            return 0;
        }
    }

    public void removeRequest(long requestId) {

    }

    public boolean removeRequest(long fromClientId, long toClientId) {
        String removeRequest = "DELETE FROM friends_requests WHERE fromClient_id = ? AND toClient_id = ? OR fromClient_id = ? AND toClient_id = ?";
        try {
            return jdbcTemplate.update(removeRequest, fromClientId, toClientId, toClientId, fromClientId) > 0;
        } catch (DataAccessException e) {
            LOGGER.error("error removeRequest " + fromClientId, e);
            return false;
        }
    }

    public boolean insertRequestFriend(FriendRequest request) {
        String removeRequest = "INSERT INTO friends_requests(requestDate,fromClient_id,toClient_id) VALUES(?,?,?)";
        try {
            return jdbcTemplate.update(removeRequest, request.getDate(), request.getRequestClientFrom(), request.getRequestClientTo()) > 0;
        } catch (DataAccessException e) {
            LOGGER.error("error insertRequestFriend requestClientFrom" + request.getRequestClientFrom() + ",requestClientTo=" + request.getRequestClientTo(), e);
            return false;
        }

    }
}
