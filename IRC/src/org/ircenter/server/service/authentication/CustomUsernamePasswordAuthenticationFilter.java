package org.ircenter.server.service.authentication;

import com.octo.captcha.module.servlet.image.SimpleImageCaptchaServlet;
import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaResponse;
import org.ircenter.server.service.user.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.TextEscapeUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Properties;

public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final String RECAPTCHA_HTML = "reCaptchaHtml";

    @Autowired
    private ReCaptcha reCaptcha;

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException, ServletException {
        //setUseReferer(true);
        request.getSession().setAttribute("loginError", Boolean.FALSE);
        request.getSession().setAttribute("captchaValid", Boolean.TRUE);
        request.getSession().setAttribute("captchaNeeded", Boolean.FALSE);
        LoginFailureCounter.removeByUserName(request.getParameter("j_username"));
        LoginFailureCounter.removeByRemoutHost(request.getRemoteHost());
        super.successfulAuthentication(request, response, authResult);
        LoggedUsers.addLoggedUserInfo((UserProfile) authResult.getPrincipal());


        // super.successfulAuthentication(request, response, authResult);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        request.getSession().setAttribute("loginError", Boolean.TRUE);
        String userName = request.getParameter("j_username");
        LoginFailureCounter.addLoginFailureByUserName(userName);
        String remoteHost = request.getRemoteHost();
        LoginFailureCounter.addLoginFailureByRemoutHost(remoteHost);
        boolean captchaNeededByUserName = LoginFailureCounter.isCaptchaNeededByUserName(userName);
        boolean captchaNeededByRemoteHost = LoginFailureCounter.isCaptchaNeededByRemoutHost(remoteHost);
        if (captchaNeededByUserName || captchaNeededByRemoteHost) {
            request.getSession().setAttribute("captchaNeeded", Boolean.TRUE);
            Properties properties = new Properties();
            properties.setProperty("theme", "white");

            String html = reCaptcha.createRecaptchaHtml(null, properties);
            request.getSession().setAttribute(RECAPTCHA_HTML, html);
        }

        super.unsuccessfulAuthentication(request, response, failed);    //To change body of overridden methods use File | Settings | File Templates.

//        String referer = request.getHeader("referer");
//        saveException(request, exception);
//        if (registrationDAO.isTmpUserPresent(userName)) {   //TODO страница на которой будет место для ввода кода с мыла
//            if (referer.indexOf('?') == -1) {
//                referer = referer + "?activate=1";
//            } else {
//                referer = referer + "&activate=1";
//            }
//            HttpSession session = request.getSession(false);
//            if (session != null) {
//                session.setAttribute("activationUserName", userName);
//            }
//            getRedirectStrategy().sendRedirect(request, response, referer);
//        } else {
//            request.getSession().setAttribute("SPRING_SECURITY_LAST_USERNAME_KEY", TextEscapeUtils.escapeEntities(userName));
//            Object isCaptchaNeeded = request.getSession().getAttribute("captchaNeeded");
//            if (isCaptchaNeeded != null && ((Boolean) isCaptchaNeeded)) {
//                Object captcha = request.getSession().getAttribute("captchaValid");
//                if (captcha == null || (Boolean) captcha) {
//                    loginErrorRedirect(request, response, referer);
//                } else {
//                    getRedirectStrategy().sendRedirect(request, response, referer);
//                }
//            } else {
//                loginErrorRedirect(request, response, referer);
//            }
//        }
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        Object captchaNeeded = request.getSession().getAttribute("captchaNeeded");
        if (captchaNeeded != null && ((Boolean) captchaNeeded)) {
            request.getSession().setAttribute("loginError", Boolean.FALSE);
            String challenge = request.getParameter("recaptcha_challenge_field");
            String captchaResponse = request.getParameter("recaptcha_response_field");

            String remoteAddr = request.getRemoteAddr();
            ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, captchaResponse);
            if (!reCaptchaResponse.isValid()) {
                request.getSession().setAttribute("captchaValid", Boolean.TRUE);
                return super.attemptAuthentication(request, response);
            } else {
                request.getSession().setAttribute("captchaValid", Boolean.FALSE);
                String username = obtainUsername(request);
                if (username == null) {
                    username = "";
                }
                username = username.trim();
                request.getSession().setAttribute(SPRING_SECURITY_LAST_USERNAME_KEY, TextEscapeUtils.escapeEntities(username));
                throw new BadCredentialsException("Captcha is not valid");
            }
        } else {
            return super.attemptAuthentication(request, response);
        }
    }
}
