package org.ircenter.server.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.dao.user.UserInfoDAO;
import org.ircenter.server.service.registration.RegistrationAjaxResponse;
import org.ircenter.server.web.registration.RegistrationFormController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;

@Controller
@RequestMapping("/mailRuAuth")
public class MailRuAuthController {

    private static final Log LOGGER = LogFactory.getLog(MailRuAuthController.class.getClass());
    private UserInfoDAO userInfoDAO;
    private RegistrationFormController registrationFormController;

    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    RegistrationAjaxResponse getMailRuAuth(HttpServletRequest request, HttpServletResponse httpResponse) throws UnsupportedEncodingException {

        request.setCharacterEncoding("UTF-8");

        RegistrationAjaxResponse registrationAjaxResponse = new RegistrationAjaxResponse();
        registrationAjaxResponse.setComplete(true);

        String uid = request.getParameter("uid");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");

        //todo не работает на реальном сервере

        if (userInfoDAO.isMailRuUserNew(uid)) {

            userInfoDAO.createMailRuUser(firstName + " " + lastName, uid);
            registrationFormController.authenticate(request, httpResponse, firstName + " " + lastName);

            return registrationAjaxResponse;
        } else {
            String login = userInfoDAO.getLoginByMailRuId(uid);
            registrationFormController.authenticate(request, httpResponse, login);
            return registrationAjaxResponse;
        }
    }

    @Autowired
    public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
        this.userInfoDAO = userInfoDAO;
    }

    @Autowired
    public void setRegistrationFormController(RegistrationFormController registrationFormController) {
        this.registrationFormController = registrationFormController;
    }
}
