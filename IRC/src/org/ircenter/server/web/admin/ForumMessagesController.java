package org.ircenter.server.web.admin;

import org.ircenter.server.bean.forum.Message;
import org.ircenter.server.bean.forum.Theme;
import org.ircenter.server.bean.slider.Slider;
import org.ircenter.server.dao.Database;
import org.ircenter.server.util.paging.PageListShort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 1
 * Date: 10.05.12
 * Time: 10:34
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/admin/forum_messages")
public class ForumMessagesController {

    private static final int FORUM_LIMIT = 10;
    private Database database;


    @RequestMapping(method = RequestMethod.GET)
    public String forumMessages(Model model, HttpServletRequest request) {
        int page = 1;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
            if (page < 1) {
                page = 1;
            }
        }
        int messageUncheckedCount=database.getMessageDAO().getUncheckedMessageCount();

        StringBuilder builder = new StringBuilder();
        PageListShort pageListPlain = new PageListShort("/admin/forum_messages?page=", FORUM_LIMIT);
        pageListPlain.setCurrentPage(page - 1);
        pageListPlain.setItemsCount(messageUncheckedCount);
        pageListPlain.generate(builder);

        model.addAttribute("pagination", builder);
        model.addAttribute("messageUncheckedCount", messageUncheckedCount);


        List<Message> messageList = database.getMessageDAO().getUncheckedForumMessages((page - 1) * FORUM_LIMIT, FORUM_LIMIT);
        model.addAttribute("messageList", messageList);
        return "admin/forum_messages";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteMessage(@PathVariable Long id) {
        database.getMessageDAO().removeMessage(id);
        return "redirect:/admin/forum_messages";
    }

    @RequestMapping(value = "/approve/{id}", method = RequestMethod.GET)
    public String approveMessage(@PathVariable Long id) {
        database.getMessageDAO().setCheckedMessage(id);
        return "redirect:/admin/forum_messages";
    }


    public Database getDatabase() {
        return database;
    }

    @Autowired
    public void setDatabase(Database database) {
        this.database = database;
    }
}