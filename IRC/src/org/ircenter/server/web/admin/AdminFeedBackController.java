package org.ircenter.server.web.admin;

import org.ircenter.server.bean.evidence.MinutesToGod;
import org.ircenter.server.bean.feedback.FeedBack;
import org.ircenter.server.dao.Database;
import org.ircenter.server.util.paging.PageListShort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 1
 * Date: 17.05.12
 * Time: 1:04
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/admin/feedback")
public class AdminFeedBackController {

    private Database database;
    private static final int Evidence_LIMIT = 10;

    @RequestMapping(method = RequestMethod.GET)
    public String getFeedBack(Model model, HttpServletRequest request) {
        int page = 1;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
            if (page < 1) {
                page = 1;
            }
        }
        int feedBackCount = database.getFeedBackDAO().getFeedBackCount();
        List<FeedBack> feedBackList = database.getFeedBackDAO().getFeedBackList((page - 1) * Evidence_LIMIT, Evidence_LIMIT);

        StringBuilder builder = new StringBuilder();
        PageListShort pageListPlain = new PageListShort("/admin/feedback?page=", Evidence_LIMIT);
        pageListPlain.setCurrentPage(page - 1);
        pageListPlain.setItemsCount(feedBackCount);
        pageListPlain.generate(builder);

        model.addAttribute("feedBackCount", feedBackCount);

        model.addAttribute("pagination", builder);
        model.addAttribute("feedBackList", feedBackList);
        return "admin/feedback";

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String deleteFeedBack(@PathVariable Long id) {
        database.getFeedBackDAO().removeFeedBack(id);
        return "redirect:/admin/feedback";
    }

    public Database getDatabase() {
        return database;
    }

    @Autowired
    public void setDatabase(Database database) {
        this.database = database;
    }
}
