package org.ircenter.server.service.friends;


import org.ircenter.server.bean.friends.Friend;
import org.ircenter.server.bean.friends.FriendRequest;
import org.ircenter.server.dao.friends.FriendsDAO;
import org.ircenter.server.dao.friends.FriendsRequestDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FriendsService {

    private FriendsDAO friendsDAO;
    private FriendsRequestDAO friendsRequestDAO;


    @Autowired
    public void setFriendsRequestDAO(FriendsRequestDAO friendsRequestDAO) {
        this.friendsRequestDAO = friendsRequestDAO;
    }

    @Autowired
    public void setFriendsDAO(FriendsDAO friendsDAO) {
        this.friendsDAO = friendsDAO;
    }


    public void addFriend(long selfId, long friendId) {
        Date date = new Date();

        Friend selfFriend = new Friend();
        selfFriend.setSelfId(selfId);
        selfFriend.setFriendId(friendId);
        selfFriend.setFriendDate(date);
        friendsDAO.insertFriend(selfFriend);

        Friend otherFriend = new Friend();
        otherFriend.setSelfId(friendId);
        otherFriend.setFriendId(selfId);
        otherFriend.setFriendDate(date);
        friendsDAO.insertFriend(otherFriend);
    }

    public List<Long> getFriends(long selfId) {
        return friendsDAO.getFriendsId(selfId);
    }

//    /**
//     * @param selfId своя ид
//     * @return возвращает список  друзей
//     */
//    public List<Friend> getOnlineFriends(long selfId) {
//        List<Friend> idList = friendsDAO.getFriendsClientId(selfId);
//
//        return idList;
//    }

    public void removeFriend(long selfId, long frendId) {
        friendsDAO.remove(selfId, frendId);
        friendsDAO.remove(frendId, selfId);
    }

    public boolean isFriends(long fromClientId, long toClientId) {
        return friendsDAO.isFriends(fromClientId, toClientId);
    }

    public void addFriendRequest(long fromClientId, long toClientId) {
        if (isRequest(toClientId, fromClientId)) {
            return;
        }
        FriendRequest request = new FriendRequest();
        request.setRequestClientFrom(fromClientId);
        request.setRequestClientTo(toClientId);
        request.setDate(new Date());
        friendsRequestDAO.insertRequestFriend(request);

        //incFriendRequest(toClientId);
    }

//    private void incFriendRequest(long clientId) {
//        if (friendListener != null) {
//            friendListener.incFriendRequest(clientId);
//        }
//    }
//
//    private void decFriendRequest(long clientId) {
//        if (friendListener != null) {
//            friendListener.decFriendRequest(clientId);
//        }
//    }

    public List<Long> getFriendRequests(long selfId) {
        return friendsRequestDAO.getRequestsId(selfId);
    }

    public long getRequestCount(long selfClientId) {
        long count = friendsRequestDAO.getRequestCount(selfClientId);
        return count;
    }

    public void rejectRequest(long requestId) {
        FriendRequest request = getFriendRequest(requestId);
        //decFriendRequest(request.getRequestClientTo());
        removeRequest(requestId);
    }

    private void removeRequest(long requestId) {
        friendsRequestDAO.removeRequest(requestId);
    }

    public void acceptRequest(FriendRequest request) {
        addFriend(request.getRequestClientTo(), request.getRequestClientFrom());
        removeRequest(request.getId());

        //decFriendRequest(request.getRequestClientTo());
    }

    public void removeRequest(long fromClientId, long toClientId) {
        friendsRequestDAO.removeRequest(fromClientId, toClientId);
        //decFriendRequest(toClientId);
    }

    public boolean isRequest(long fromClientId, long toClientId) {
        return friendsRequestDAO.isRequest(fromClientId, toClientId);
    }

    public boolean mayAddToFriends(long selfClientId, long otherClientId) {
        if (isFriends(selfClientId, otherClientId) || isFriends(otherClientId, selfClientId)) {
            return false;
        } else if (isRequest(selfClientId, otherClientId) || isRequest(otherClientId, selfClientId)) {
            return false;
        }
        return true;
    }


    public Friend loadFriend(long id) {
        return friendsDAO.getFriendById(id);
    }

    public FriendRequest getFriendRequest(long id) {
        return friendsRequestDAO.getRequestFriendByID(id);
    }
}
