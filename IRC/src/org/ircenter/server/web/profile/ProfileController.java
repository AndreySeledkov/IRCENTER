package org.ircenter.server.web.profile;

import org.ircenter.server.bean.friends.Friend;
import org.ircenter.server.bean.friends.FriendRequest;
import org.ircenter.server.bean.privatemessage.PrivateMessage;
import org.ircenter.server.dao.friends.FriendsDAO;
import org.ircenter.server.dao.friends.FriendsRequestDAO;
import org.ircenter.server.dao.privatemessage.PrivateMessageDAO;
import org.ircenter.server.dao.user.UserInfoDAO;
import org.ircenter.server.service.user.UserHelper;
import org.ircenter.server.service.user.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class ProfileController {

    private FriendsDAO friendsDAO;
    private FriendsRequestDAO friendsRequestDAO;
    private UserInfoDAO userInfoDAO;
    private PrivateMessageDAO privateMessageDAO;

    @RequestMapping(method = RequestMethod.GET)
    public String profile(Model model, HttpServletRequest request) {
        if (request.getParameter("id") != null) {
            long id = Long.parseLong(request.getParameter("id"));
            if (UserHelper.getUserId() == id) {
                return "redirect:/";
            }

            boolean isFriends = friendsDAO.isFriends(UserHelper.getUserId(), id);
            boolean isRequest = friendsRequestDAO.isRequest(id, UserHelper.getUserId());
            UserProfile user = userInfoDAO.getUser(id);
            model.addAttribute("isFriend", isFriends);
            model.addAttribute("isRequest", isRequest);
            model.addAttribute("user", user);

            return "/profile/profile";
        } else if (request.getParameter("add_to_friend") != null) {
            long userId = Long.parseLong(request.getParameter("add_to_friend"));

            if (!friendsDAO.isFriends(userId, UserHelper.getUserId()) && !friendsDAO.isFriends(UserHelper.getUserId(), userId) && !friendsRequestDAO.isRequest(UserHelper.getUserId(), userId)) {
                FriendRequest friendRequest = new FriendRequest();
                friendRequest.setRequestClientFrom(UserHelper.getUserId());
                friendRequest.setRequestClientTo(userId);
                friendRequest.setDate(new Date());
                friendsRequestDAO.insertRequestFriend(friendRequest);
            }

            if (!friendsDAO.isFriends(userId, UserHelper.getUserId()) && !friendsDAO.isFriends(UserHelper.getUserId(), userId) && friendsRequestDAO.isRequest(UserHelper.getUserId(), userId)) {

                Date date = new Date();

                Friend selfFriend = new Friend();
                selfFriend.setSelfId(UserHelper.getUserId());
                selfFriend.setFriendId(userId);
                selfFriend.setFriendDate(date);
                friendsDAO.insertFriend(selfFriend);

                Friend otherFriend = new Friend();
                otherFriend.setSelfId(userId);
                otherFriend.setFriendId(UserHelper.getUserId());
                otherFriend.setFriendDate(date);
                friendsDAO.insertFriend(otherFriend);

                friendsRequestDAO.removeRequest(userId, UserHelper.getUserId());
            }
            return "redirect:/profile?id=" + userId;

        } else if (request.getParameter("delete_friend") != null) {
            long userId = Long.parseLong(request.getParameter("delete_friend"));
            friendsDAO.remove(UserHelper.getUserId(), userId);

            return "redirect:/profile?id=" + userId;
        }
        return "/profile/profile";
    }


    @RequestMapping(value = "/send_message", method = RequestMethod.GET)
    public
    @ResponseBody
    String sendMessage(@RequestParam Long user_id, @RequestParam String message, HttpServletRequest request) {
        PrivateMessage privateMessage = new PrivateMessage();
        privateMessage.setBody(message);
        privateMessage.setFromUserId(UserHelper.getUserId());
        privateMessage.setToUserId(user_id);

        return privateMessageDAO.insertPrivateMessage(privateMessage) > 0 ? "Ok" : "False";
    }


    public PrivateMessageDAO getPrivateMessageDAO() {
        return privateMessageDAO;
    }

    @Autowired
    public void setPrivateMessageDAO(PrivateMessageDAO privateMessageDAO) {
        this.privateMessageDAO = privateMessageDAO;
    }

    public FriendsRequestDAO getFriendsRequestDAO() {
        return friendsRequestDAO;
    }

    @Autowired
    public void setFriendsRequestDAO(FriendsRequestDAO friendsRequestDAO) {
        this.friendsRequestDAO = friendsRequestDAO;
    }

    public FriendsDAO getFriendsDAO() {
        return friendsDAO;
    }

    @Autowired
    public void setFriendsDAO(FriendsDAO friendsDAO) {
        this.friendsDAO = friendsDAO;
    }

    public UserInfoDAO getUserInfoDAO() {
        return userInfoDAO;
    }

    @Autowired
    public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
        this.userInfoDAO = userInfoDAO;
    }
}
