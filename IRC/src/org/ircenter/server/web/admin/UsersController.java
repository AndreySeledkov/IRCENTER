package org.ircenter.server.web.admin;

import org.ircenter.server.bean.forum.Theme;
import org.ircenter.server.dao.Database;
import org.ircenter.server.service.user.UserProfile;
import org.ircenter.server.util.paging.PageListShort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 1
 * Date: 10.05.12
 * Time: 13:52
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/admin/users")
public class UsersController {
    private Database database;
    private ShaPasswordEncoder passwordEncoder;
    private static final int USERS_LIMIT = 10;

    @RequestMapping(method = RequestMethod.GET)
    public String getUsers(Model model, HttpServletRequest request) {
        int page = 1;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
            if (page < 1) {
                page = 1;
            }
        }
        long usersCount = database.getUserInfoDAO().getUsersCount();
        List<UserProfile> userProfileList = database.getUserInfoDAO().getUsers(page - 1);

        StringBuilder builder = new StringBuilder();
        PageListShort pageListPlain = new PageListShort("/admin/users?page=", USERS_LIMIT);
        pageListPlain.setCurrentPage(page - 1);
        pageListPlain.setItemsCount((int) usersCount);
        pageListPlain.generate(builder);

        model.addAttribute("pagination", builder);
        model.addAttribute("usersCount", usersCount);


        model.addAttribute("profiles", userProfileList);
        return "admin/users";
    }

    @RequestMapping(value = "/search_user", method = RequestMethod.GET)       //todo search
    public String getSearchUsers(Model model, HttpServletRequest request, @RequestParam String searchUser) {
        List<UserProfile> userProfileList = database.getUserInfoDAO().searchUser(searchUser);
        model.addAttribute("profiles", userProfileList);
        return "admin/users";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable Long id) {
        database.getUserInfoDAO().removeUser(id);
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/new_user", method = RequestMethod.GET)
    public String newUser(Model model, HttpServletRequest request) {
        return "admin/new_user";
    }

    @RequestMapping(value = "/new_user", method = RequestMethod.POST)   //todo save new user
    public String addNewUser(@RequestParam String userLogin, String email, @RequestParam String pass1, @RequestParam String role, HttpServletRequest request, HttpServletResponse response) {
        String passEncode = passwordEncoder.encodePassword(pass1, null);

        database.getRegistrationDAO().userRegistrationFromAdmin(userLogin, email, passEncode);
        return "redirect:/admin/users";
    }

    public ShaPasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    @Autowired
    public void setPasswordEncoder(ShaPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Database getDatabase() {
        return database;
    }

    @Autowired
    public void setDatabase(Database database) {
        this.database = database;
    }
}
