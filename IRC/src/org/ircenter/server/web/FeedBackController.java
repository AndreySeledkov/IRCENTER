package org.ircenter.server.web;

import org.ircenter.server.MUtil;
import org.ircenter.server.bean.Deliveries;
import org.ircenter.server.bean.feedback.FeedBack;
import org.ircenter.server.bean.feedback.FeedBackTheme;
import org.ircenter.server.dao.Database;
import org.ircenter.server.web.edit.EditAjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: 1
 * Date: 06.05.12
 * Time: 10:30
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/feedback")
public class FeedBackController {

    private Database database;

    @RequestMapping(method = RequestMethod.GET)
    public String feedback(Model model, HttpServletRequest request) {
        return "new/feedback";
    }

    @RequestMapping(value = "/leave_feedback", method = RequestMethod.POST)
    @ResponseBody
    public EditAjaxResponse sendFeedBack(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String mail, @RequestParam int theme, @RequestParam String info, HttpServletRequest request, HttpServletResponse response) {

        if (mail.isEmpty() || info.isEmpty() || FeedBackTheme.dbValueOf(theme) == null) {
            return new EditAjaxResponse(false);
        }
        if (!MUtil.isValidLogin(mail)) {
            return new EditAjaxResponse(false);
        }

        if (firstName.length() > 50) {
            firstName = firstName.substring(0, 50);
        }
        if (lastName.length() > 50) {
            lastName = lastName.substring(0, 50);
        }
        if (info.length() > 500) {
            info = info.substring(0, 500);
        }

        FeedBack feedBack = new FeedBack();
        feedBack.setFirstName(firstName);
        feedBack.setLastName(lastName);
        feedBack.setMail(mail);
        feedBack.setTheme(FeedBackTheme.dbValueOf(theme));
        feedBack.setInfo(info);
        feedBack.setDate(new Date());

        database.getFeedBackDAO().saveFeedBack(feedBack);
        EditAjaxResponse editAjaxResponse = new EditAjaxResponse(true);

        return editAjaxResponse;
    }

    @RequestMapping(value = "/delivery", method = RequestMethod.POST)
    @ResponseBody
    public EditAjaxResponse sendDelivery(@RequestParam String mail, HttpServletRequest request, HttpServletResponse response) {

        EditAjaxResponse editAjaxResponse;
        if (MUtil.isValidLogin(mail)) {
            Deliveries deliveries = new Deliveries();
            deliveries.setMail(mail);

            database.getDeliveriesDAO().saveDeliveries(deliveries);
            editAjaxResponse = new EditAjaxResponse(true);
        } else {
            editAjaxResponse = new EditAjaxResponse(false);
        }
        return editAjaxResponse;
    }


    public Database getDatabase() {
        return database;
    }

    @Autowired
    public void setDatabase(Database database) {
        this.database = database;
    }
}
