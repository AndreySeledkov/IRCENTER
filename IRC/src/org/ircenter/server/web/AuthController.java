package org.ircenter.server.web;

import net.tanesha.recaptcha.ReCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private static final String RECAPTCHA_HTML = "reCaptchaHtml";
    @Autowired
    private ReCaptcha reCaptcha;

    @RequestMapping(method = RequestMethod.GET)
    public String auth(HttpServletRequest request, HttpServletResponse response) {
        return "new/auth";
    }


    @RequestMapping(value = "/auth_form", method = RequestMethod.GET)
    public String authFrom(HttpServletRequest request, HttpServletResponse response) {
        return "new/auth_form";
    }

    @RequestMapping(value = "/remind_password", method = RequestMethod.GET)
    public ModelAndView remindPassword(HttpServletRequest request, HttpServletResponse response) {
        Properties properties = new Properties();
        properties.setProperty("width", "200");
        properties.setProperty("height", "100");

        String html = reCaptcha.createRecaptchaHtml(null, properties);

        ModelMap modelMap = new ModelMap();
        modelMap.put(RECAPTCHA_HTML, html);
        return new ModelAndView("new/remind_password", modelMap);
    }
}
