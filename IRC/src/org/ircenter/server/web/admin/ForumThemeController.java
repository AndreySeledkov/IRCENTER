package org.ircenter.server.web.admin;

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
 * Date: 03.05.12
 * Time: 9:05
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/admin/forum_theme")
public class ForumThemeController {

    private static final int FORUM_LIMIT = 10;
    private Database database;


    @RequestMapping(method = RequestMethod.GET)
    public String forumTheme(Model model, HttpServletRequest request) {
        int page = 1;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
            if (page < 1) {
                page = 1;
            }
        }
        int themeUncheckedCount=database.getThemeDAO().getUncheckedThemesCount();
        List<Theme> themeList = database.getThemeDAO().getUncheckedTheme((page - 1) * FORUM_LIMIT, FORUM_LIMIT);
        model.addAttribute("themeList", themeList);

        StringBuilder builder = new StringBuilder();
        PageListShort pageListPlain = new PageListShort("/admin/forum_theme?page=", FORUM_LIMIT);
        pageListPlain.setCurrentPage(page - 1);
        pageListPlain.setItemsCount(themeUncheckedCount);
        pageListPlain.generate(builder);

        model.addAttribute("pagination", builder);
        model.addAttribute("themeUncheckedCount", themeUncheckedCount);

        return "admin/forum_theme";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteTheme(@PathVariable Long id) {
        database.getThemeDAO().removeTheme(id);
        return "redirect:/admin/forum_theme";
    }

    @RequestMapping(value = "/approve/{id}", method = RequestMethod.GET)
    public String approveTheme(@PathVariable Long id) {
        database.getThemeDAO().setCheckedTheme(id);
        return "redirect:/admin/forum_theme";
    }


    public Database getDatabase() {
        return database;
    }

    @Autowired
    public void setDatabase(Database database) {
        this.database = database;
    }
}
