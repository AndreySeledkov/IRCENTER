package org.ircenter.server.web.admin;

import org.ircenter.server.dao.Database;
import org.ircenter.server.service.authentication.LoggedUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User: 1
 * Date: 17.05.12
 * Time: 1:04
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/admin/statistic")
public class AdminStatisticController {

    private Database database;

    @RequestMapping(method = RequestMethod.GET)
    public String getSecretSpiritWorld(Model model, HttpServletRequest request) {

        int loggedCount = LoggedUsers.getLoggedUsersCount();
        long usersCount = database.getUserInfoDAO().getUsersCount();
        int vkUsers = database.getUserInfoDAO().getUserByVk();
        int fBUsers = database.getUserInfoDAO().getUserByFb();
        model.addAttribute("loggedCount", loggedCount);
        model.addAttribute("usersCount", usersCount);
        model.addAttribute("vkUsers", vkUsers);
        model.addAttribute("fBUsers", fBUsers);

        return "admin/statistic";
    }

    public Database getDatabase() {
        return database;
    }

    @Autowired
    public void setDatabase(Database database) {
        this.database = database;
    }
}
