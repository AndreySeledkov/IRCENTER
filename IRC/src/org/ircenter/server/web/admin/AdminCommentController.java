package org.ircenter.server.web.admin;

import org.ircenter.server.bean.comment.Comment;
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
 * Date: 14.05.12
 * Time: 2:04
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/admin/comment")
public class AdminCommentController {

    private Database database;
    private static final int Evidence_LIMIT = 10;

    @RequestMapping(method = RequestMethod.GET)
    public String getComments(Model model, HttpServletRequest request) {
        int page = 1;

        if (request.getParameter("id") != null) {
            long id = Long.parseLong(request.getParameter("id"));
            if (request.getParameter("remove") != null) {
                database.getCommentDAO().removeComment(id);
            } else if (request.getParameter("check") != null) {
                database.getCommentDAO().updateCheckComments(id);
            }
            return "redirect:/admin/comment";
        }

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
            if (page < 1) {
                page = 1;
            }
        }
        int uncheckedCommentsCount = database.getCommentDAO().getUncheckedCommentsCount();
        List<Comment> commentList = database.getCommentDAO().getComments((page - 1) * Evidence_LIMIT, Evidence_LIMIT);

        StringBuilder builder = new StringBuilder();
        PageListShort pageListPlain = new PageListShort("/admin/comment?page=", Evidence_LIMIT);
        pageListPlain.setCurrentPage(page - 1);
        pageListPlain.setItemsCount(uncheckedCommentsCount);
        pageListPlain.generate(builder);

        model.addAttribute("uncheckedCommentsCount", uncheckedCommentsCount);

        model.addAttribute("pagination", builder);
        model.addAttribute("commentList", commentList);
        return "admin/comments";

    }


    public Database getDatabase() {
        return database;
    }

    @Autowired
    public void setDatabase(Database database) {
        this.database = database;
    }
}
