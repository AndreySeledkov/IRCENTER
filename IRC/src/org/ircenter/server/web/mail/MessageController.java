package org.ircenter.server.web.mail;

import org.ircenter.server.MUtil;
import org.ircenter.server.bean.friends.Friend;
import org.ircenter.server.bean.privatemessage.PrivateMessage;
import org.ircenter.server.dao.Database;
import org.ircenter.server.dao.privatemessage.PrivateMessageDAO;
import org.ircenter.server.service.authentication.LoggedUsers;
import org.ircenter.server.service.user.UserHelper;
import org.ircenter.server.service.user.UserProfile;
import org.ircenter.server.util.paging.PageListShort;
import org.ircenter.server.web.edit.EditAjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/messages")
public class MessageController {

    private static final int MESSAGE_LIMIT = 3;
    private Database database;

    @RequestMapping(method = RequestMethod.GET)
    public String mail(Model model, HttpServletRequest request) {
        int page = 1;

        if (request.getParameter("page") != null) {
            page = MUtil.str2int(request.getParameter("page"), 1);
            if (page < 1) {
                page = 1;
            }
        }


        long unReadMessage = database.getPrivateMessageDAO().getNotReadedPrivateMessageCount();
        long requestFriend = database.getFriendsRequestDAO().getRequestCount(UserHelper.getUserId());

        int privateMessagesInboxCount = database.getPrivateMessageDAO().getPrivateMessageCount(false);
        int privateMessagesOutboxCount = database.getPrivateMessageDAO().getPrivateMessageCount(true);
        model.addAttribute("privateMessagesInboxCount", privateMessagesInboxCount);
        model.addAttribute("privateMessagesOutboxCount", privateMessagesOutboxCount);

        int friendRequestCount = database.getFriendsRequestDAO().getRequestCount(UserHelper.getUserId());
        int friendCount = database.getFriendsDAO().getFriendsCount(UserHelper.getUserId());

        model.addAttribute("friendRequestCount", friendRequestCount);
        model.addAttribute("friendCount", friendCount);


        request.getSession().setAttribute("unReadMessage", unReadMessage);
        request.getSession().setAttribute("requestFriend", requestFriend);
        model.addAttribute("isMessageTab", true);
        List<Friend> friendAllList = database.getFriendsDAO().getFriendsClientId(UserHelper.getUserId(), 0, 6);
        List<UserProfile> userListFriends = new ArrayList<UserProfile>();
        for (Friend friend : friendAllList) {
            UserProfile user = database.getUserInfoDAO().getUser(friend.getFriendId());
            userListFriends.add(user);
        }
        model.addAttribute("userListFriends", userListFriends);

        String section = request.getParameter("section");
        if (section == null || request.getParameter("section").equals("inbox")) {


            List<PrivateMessage> privateMessages = database.getPrivateMessageDAO().getPrivateMessages(page - 1, false);
            for (PrivateMessage privateMessage : privateMessages) {
                UserProfile userProfile = database.getUserInfoDAO().getUser(privateMessage.getFromUserId());
                privateMessage.setUserName(userProfile.getLoginName());
                privateMessage.setToUserId(userProfile.getUserId());
            }

            StringBuilder builder = new StringBuilder();
            PageListShort pageListPlain = new PageListShort("/messages/messages_form?section=inbox&page=", true, MESSAGE_LIMIT);
            pageListPlain.setCurrentPage(page - 1);
            pageListPlain.setItemsCount(privateMessagesInboxCount);
            pageListPlain.generate(builder);

            model.addAttribute("pagination", builder);
            model.addAttribute("privateMessages", privateMessages);
            model.addAttribute("privateMessagesInboxCount", privateMessagesInboxCount);
            return "new/profile/message_inbox";
        } else if (section != null && request.getParameter("section").equals("outbox")) {

            List<PrivateMessage> privateMessages = database.getPrivateMessageDAO().getPrivateMessages(page - 1, true);
            for (PrivateMessage privateMessage : privateMessages) {
                UserProfile userProfile = database.getUserInfoDAO().getUser(privateMessage.getToUserId());
                privateMessage.setUserName(userProfile.getLoginName());
                privateMessage.setToUserId(userProfile.getUserId());
            }

            StringBuilder builder = new StringBuilder();
            PageListShort pageListPlain = new PageListShort("/messages/messages_form?section=outbox&page=", true, MESSAGE_LIMIT);
            pageListPlain.setCurrentPage(page - 1);
            pageListPlain.setItemsCount(privateMessagesOutboxCount);
            pageListPlain.generate(builder);

            model.addAttribute("pagination", builder);
            model.addAttribute("privateMessages", privateMessages);
            return "new/profile/message_outbox";
        } else if (section != null && request.getParameter("section").equals("spam")) {

        }
        return "/";
    }

    @RequestMapping(value = "/delete_message", method = RequestMethod.GET)
    public
    @ResponseBody
    String deleteMessage(@RequestParam Long message_id) {
        return database.getPrivateMessageDAO().deletePrivateMessage(message_id) > 0 ? "Ok" : "False";
    }

    @RequestMapping(value = "/send_message", method = RequestMethod.POST)
    public
    @ResponseBody
    EditAjaxResponse addMessage(HttpServletRequest request, @RequestParam Long userId, @RequestParam String message) {

        PrivateMessage privateMessage = new PrivateMessage();
        privateMessage.setBody(message);
        privateMessage.setFromUserId(UserHelper.getUserId());
        privateMessage.setToUserId(userId);

        EditAjaxResponse editAjaxResponse = new EditAjaxResponse(database.getPrivateMessageDAO().insertPrivateMessage(privateMessage) > 0);

        return editAjaxResponse;
    }


    @RequestMapping(value = "/message_form", method = RequestMethod.GET)
    public String getFriendsForm(Model model, HttpServletRequest request, long userId) {

        UserProfile userProfile = database.getUserInfoDAO().getUser(userId);


        model.addAttribute("userReceiver", userProfile);
        return "new/message_form";
    }

    @RequestMapping(value = "/messages_form", method = RequestMethod.GET)
    public String getMessagesForm(Model model, HttpServletRequest request) {
        int page = 1;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
            if (page < 1) {
                page = 1;
            }
        }
        int privateMessagesInboxCount = database.getPrivateMessageDAO().getPrivateMessageCount(false);
        int privateMessagesOutboxCount = database.getPrivateMessageDAO().getPrivateMessageCount(true);
        model.addAttribute("privateMessagesInboxCount", privateMessagesInboxCount);
        model.addAttribute("privateMessagesOutboxCount", privateMessagesOutboxCount);


        if (request.getParameter("section").equals("inbox")) {

            List<PrivateMessage> privateMessages = database.getPrivateMessageDAO().getPrivateMessages(page - 1, false);
            for (PrivateMessage privateMessage : privateMessages) {
                UserProfile userProfile = database.getUserInfoDAO().getUser(privateMessage.getFromUserId());
                privateMessage.setUserName(userProfile.getLoginName());
                privateMessage.setToUserId(userProfile.getUserId());
            }

            StringBuilder builder = new StringBuilder();
            PageListShort pageListPlain = new PageListShort("/messages/messages_form?section=inbox&page=", true, MESSAGE_LIMIT);
            pageListPlain.setCurrentPage(page - 1);
            pageListPlain.setItemsCount(privateMessagesInboxCount);
            pageListPlain.generate(builder);

            model.addAttribute("pagination", builder);
            model.addAttribute("privateMessages", privateMessages);
            return "new/profile/message_form_inbox";
        } else if (request.getParameter("section").equals("outbox")) {

            List<PrivateMessage> privateMessages = database.getPrivateMessageDAO().getPrivateMessages(page - 1, true);
            for (PrivateMessage privateMessage : privateMessages) {
                UserProfile userProfile = database.getUserInfoDAO().getUser(privateMessage.getFromUserId());
                privateMessage.setUserName(userProfile.getLoginName());
                privateMessage.setToUserId(userProfile.getUserId());
            }

            StringBuilder builder = new StringBuilder();
            PageListShort pageListPlain = new PageListShort("/messages/messages_form?section=outbox&page=", true, MESSAGE_LIMIT);
            pageListPlain.setCurrentPage(page - 1);
            pageListPlain.setItemsCount(privateMessagesOutboxCount);
            pageListPlain.generate(builder);

            model.addAttribute("pagination", builder);
            model.addAttribute("privateMessages", privateMessages);
            model.addAttribute("privateMessagesOutboxCount", privateMessagesOutboxCount);
            return "new/profile/message_form_outbox";
        }
        return "/";
    }

    public Database getDatabase() {
        return database;
    }

    @Autowired
    public void setDatabase(Database database) {
        this.database = database;
    }


}
