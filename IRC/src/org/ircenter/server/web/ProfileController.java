package org.ircenter.server.web;

import org.ircenter.server.bean.friends.Friend;
import org.ircenter.server.bean.privatemessage.PrivateMessage;
import org.ircenter.server.dao.Database;
import org.ircenter.server.service.authentication.LoggedUsers;
import org.ircenter.server.service.user.UserHelper;
import org.ircenter.server.service.user.UserProfile;
import org.ircenter.server.util.paging.PageListShort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private static final int MESSAGE_LIMIT = 3;
    private Database database;

    @Autowired
    public void setDatabase(Database database) {
        this.database = database;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String profile(Model model, HttpServletRequest request, HttpServletResponse response) {

        long unReadMessage = database.getPrivateMessageDAO().getNotReadedPrivateMessageCount();
        long requestFriend = database.getFriendsRequestDAO().getRequestCount(UserHelper.getUserId());

        request.getSession().setAttribute("unReadMessage", unReadMessage);
        request.getSession().setAttribute("requestFriend", requestFriend);

        int friendsCount = database.getFriendsDAO().getFriendsCount(UserHelper.getUserId());
        List<Friend> friendList = database.getFriendsDAO().getFriendsClientId(UserHelper.getUserId(), 0, 6);
        List<UserProfile> userList = new ArrayList<UserProfile>();
        for (Friend friend : friendList) {
            UserProfile user = database.getUserInfoDAO().getUser(friend.getFriendId());
            userList.add(user);
        }
        model.addAttribute("userListFriends", userList);
        model.addAttribute("friendsCount", friendsCount);

        model.addAttribute("isMessageTab", true);

        int privateMessagesInboxCount = database.getPrivateMessageDAO().getPrivateMessageCount(false);
        int privateMessagesOutboxCount = database.getPrivateMessageDAO().getPrivateMessageCount(true);

        int friendRequestCount = database.getFriendsRequestDAO().getRequestCount(UserHelper.getUserId());
        int friendCount = database.getFriendsDAO().getFriendsCount(UserHelper.getUserId());

        model.addAttribute("friendRequestCount", friendRequestCount);
        model.addAttribute("friendCount", friendCount);


        List<PrivateMessage> privateMessages = database.getPrivateMessageDAO().getPrivateMessages(0, false);
        for (PrivateMessage privateMessage : privateMessages) {
            UserProfile userProfile = database.getUserInfoDAO().getUser(privateMessage.getFromUserId());
            privateMessage.setUserName(userProfile.getLoginName());
            privateMessage.setToUserId(userProfile.getUserId());
        }

        StringBuilder builder = new StringBuilder();
        PageListShort pageListPlain = new PageListShort("/messages/messages_form?section=inbox&page=", true, MESSAGE_LIMIT);
        pageListPlain.setCurrentPage(0);
        pageListPlain.setItemsCount(privateMessagesInboxCount);
        pageListPlain.generate(builder);

        model.addAttribute("pagination", builder);
        model.addAttribute("privateMessages", privateMessages);
        model.addAttribute("privateMessagesInboxCount", privateMessagesInboxCount);
        model.addAttribute("privateMessagesOutboxCount", privateMessagesOutboxCount);


        return "new/profile/profile";
    }

    @RequestMapping(value = "/change_avatar", method = RequestMethod.GET)
    public String registration(HttpServletRequest request,
                               HttpServletResponse response) {

        return "new/change_avatar";
    }


}
