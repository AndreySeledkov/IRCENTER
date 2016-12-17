package org.ircenter.server.web.forum;

import org.ircenter.server.MUtil;
import org.ircenter.server.bean.forum.*;
import org.ircenter.server.dao.Database;
import org.ircenter.server.dao.forum.MessageDAO;
import org.ircenter.server.dao.forum.PartsDAO;
import org.ircenter.server.dao.forum.SettingDAO;
import org.ircenter.server.dao.forum.ThemeDAO;
import org.ircenter.server.dao.location.UserLocationDAO;
import org.ircenter.server.dao.user.UserInfoDAO;
import org.ircenter.server.service.user.UserProfile;
import org.ircenter.server.service.user.UserHelper;
import org.ircenter.server.util.paging.PageListShort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/forum")
public class ForumController {

    private static final int RPP = 10;
    private Database database;


    @RequestMapping(method = RequestMethod.GET)
    public String forum(Model model, HttpServletRequest request) {
        long unReadMessage = database.getPrivateMessageDAO().getNotReadedPrivateMessageCount();
        long requestFriend = database.getFriendsRequestDAO().getRequestCount(UserHelper.getUserId());

        request.getSession().setAttribute("unReadMessage", unReadMessage);
        request.getSession().setAttribute("requestFriend", requestFriend);

        List<MainPart> mainPartList = database.getPartsDAO().getListMainPart();
        List<ThemeView> themesLastEdit = new ArrayList<ThemeView>();

        for (MainPart mainPart : mainPartList) {
            for (Part part : mainPart.getParts()) {
                Theme theme = database.getThemeDAO().getLastEditTheme(part.getId());
                if (theme != null) {
                    themesLastEdit.add(convertThemeToThemView(theme));
                }
            }
        }

        model.addAttribute("themesLastEdit", themesLastEdit);
        model.addAttribute("mainPartList", mainPartList);

        model.addAttribute("themesCount", database.getThemeDAO().getThemesCount());
        model.addAttribute("messagesCount", database.getMessageDAO().getMessagesCount());

        return "new/forum";
    }

    private ThemeView convertThemeToThemView(Theme theme) {
        ThemeView themeView = new ThemeView();
        themeView.setId(theme.getId());
        themeView.setPartId(theme.getPartId());
        themeView.setAuthorId(theme.getAuthorId());
        themeView.setBanned(theme.isBanned());
        themeView.setDeleted(theme.isDeleted());
        themeView.setLastEdit(theme.getLastEdit());
        themeView.setMessagesCount(theme.getMessagesCount());
        themeView.setOrderIndex(theme.getOrderIndex());
        themeView.setName(theme.getName());
        themeView.setNumberViews(theme.getNumberViews());

        UserProfile userProfile = database.getUserInfoDAO().getUser(theme.getAuthorId());
        themeView.setUserProfile(userProfile);
        return themeView;
    }

    private MessageView convertMessagesToMessageView(Message message) {
        MessageView messageView = new MessageView();
        messageView.setId(message.getId());
        messageView.setThemeId(message.getThemeId());
        messageView.setCreateDate(message.getCreateDate());
        UserProfile userProfile = database.getUserInfoDAO().getUser(message.getAuthorId());
        messageView.setUserProfile(userProfile);
        return messageView;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView getPrivateMessages(HttpServletRequest request, @PathVariable Long id) {
        long unReadMessage = database.getPrivateMessageDAO().getNotReadedPrivateMessageCount();
        long requestFriend = database.getFriendsRequestDAO().getRequestCount(UserHelper.getUserId());

        request.getSession().setAttribute("unReadMessage", unReadMessage);
        request.getSession().setAttribute("requestFriend", requestFriend);

        int page = 1;

        if (request.getParameter("page") != null) {
            page = MUtil.str2int(request.getParameter("page"), 1);
        }


        HashMap<String, Object> model = new HashMap<String, Object>();
        Part part = database.getPartsDAO().get(id);
        List<Theme> themes = database.getThemeDAO().getThemes(part, (page - 1) * RPP, RPP);

        List<MessageView> messages = new ArrayList<MessageView>();
        for (Theme theme : themes) {
            Message message = database.getMessageDAO().getLastMessage(theme.getId());
            if (message != null) {
                messages.add(convertMessagesToMessageView(message));
            }
        }

        model.put("part", part);
        model.put("themes", themes);
        model.put("messages", messages);
        model.put("themesCount", database.getThemeDAO().getThemesCount(part));


        int themesCount = database.getThemeDAO().getThemesCount(part);

        StringBuilder builder = new StringBuilder();
        PageListShort pageListPlain = new PageListShort("/forum/" + id + "?page=", RPP);
        pageListPlain.setCurrentPage(page - 1);
        pageListPlain.setItemsCount(themesCount);
        pageListPlain.generate(builder);

        model.put("pagination", builder);

        return new ModelAndView("new/forum_theme", model);
    }

    @RequestMapping(value = "/{id}/messages", method = RequestMethod.GET)
    public ModelAndView getThemeMessages(HttpServletRequest request, @PathVariable Long id) {
        long unReadMessage = database.getPrivateMessageDAO().getNotReadedPrivateMessageCount();
        long requestFriend = database.getFriendsRequestDAO().getRequestCount(UserHelper.getUserId());

        request.getSession().setAttribute("unReadMessage", unReadMessage);
        request.getSession().setAttribute("requestFriend", requestFriend);

        HashMap<String, Object> model = new HashMap<String, Object>();

        int page = 1;

        if (request.getParameter("page") != null) {
            page = MUtil.str2int(request.getParameter("page"), 1);
        }

        Theme theme = database.getThemeDAO().get(id);

        Part part = database.getPartsDAO().get(theme.getPartId());

        database.getThemeDAO().increaseViews(id);
//        Message message = database.getMessageDAO().getFirstMessage(theme);

        int messageCount = database.getMessageDAO().getMessagesCount(theme);
        List<Message> messages = database.getMessageDAO().getMessageList(theme, (page - 1) * RPP, RPP);
        List<UserDataForum> userDataForumList = new ArrayList<UserDataForum>();
//        model.put("firstmessage", message);

        for (Message message1 : messages) {
            UserDataForum userDataForum = new UserDataForum();
            UserProfile userProfile = database.getUserInfoDAO().getUser(message1.getAuthorId());
            userDataForum.setId(message1.getAuthorId());
            userDataForum.setName(userProfile.getLoginName());
            userDataForum.setMessage(message1.getText());
            userDataForum.setMessagePrint(message1.getCreateDate());

            userDataForumList.add(userDataForum);
        }

        StringBuilder builder = new StringBuilder();
        PageListShort pageListPlain = new PageListShort("/forum/" + id + "?page=", RPP);
        pageListPlain.setCurrentPage(page - 1);
        pageListPlain.setItemsCount(database.getMessageDAO().getMessagesCount(theme));
        pageListPlain.generate(builder);

        model.put("pagination", builder);

        model.put("theme", theme);
        model.put("part", part);
        model.put("messageCount", messageCount);


        model.put("userforumlist", userDataForumList);
        return new ModelAndView("new/forum_messages", model);
    }

    @RequestMapping(value = "/newtheme", method = RequestMethod.POST)
    public String newTheme(@RequestParam Long partId, @RequestParam String nameTheme, @RequestParam String text) {
        database.getThemeDAO().addTheme(UserHelper.getUserId(), partId, nameTheme, text);
        return "redirect:/forum";
    }

    @RequestMapping(value = "/{id}/forum_new_tem", method = RequestMethod.GET)
    public ModelAndView addNewTheme(HttpServletRequest request, @PathVariable Long id) {
        HashMap<String, Object> model = new HashMap<String, Object>();

        long unReadMessage = database.getPrivateMessageDAO().getNotReadedPrivateMessageCount();
        long requestFriend = database.getFriendsRequestDAO().getRequestCount(UserHelper.getUserId());

        request.getSession().setAttribute("unReadMessage", unReadMessage);
        request.getSession().setAttribute("requestFriend", requestFriend);

        Part part = database.getPartsDAO().get(id);
        model.put("part", part);

        return new ModelAndView("new/forum_new_tem", model);
    }

    @RequestMapping(value = "/{id}/forum_new_message", method = RequestMethod.GET)
    public ModelAndView addNewMessage(HttpServletRequest request, @PathVariable Long id) {
        HashMap<String, Object> model = new HashMap<String, Object>();

        long unReadMessage = database.getPrivateMessageDAO().getNotReadedPrivateMessageCount();
        long requestFriend = database.getFriendsRequestDAO().getRequestCount(UserHelper.getUserId());

        request.getSession().setAttribute("unReadMessage", unReadMessage);
        request.getSession().setAttribute("requestFriend", requestFriend);

        Theme theme = database.getThemeDAO().get(id);

        Part part = database.getPartsDAO().get(theme.getPartId());
        model.put("part", part);
        model.put("theme", theme);


        return new ModelAndView("new/forum_new_message", model);
    }

    @RequestMapping(value = "/addmessage", method = RequestMethod.POST)
    public String addMessage(@RequestParam Long themeId, @RequestParam String message) {
        database.getMessageDAO().addMessageNew(themeId, UserHelper.getUserId(), message);
//        database.getThemeDAO().increaseMessages(themeId);

        return "redirect:/forum";

    }


    private class AvailableAjaxResponse {

        private boolean complete;

        private AvailableAjaxResponse(boolean complete) {
            this.complete = complete;
        }

        public boolean isComplete() {
            return complete;
        }

        public void setComplete(boolean complete) {
            this.complete = complete;
        }
    }

    public Database getDatabase() {
        return database;
    }

    @Autowired
    public void setDatabase(Database database) {
        this.database = database;
    }

}
