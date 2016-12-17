package org.ircenter.server.service.authentication;

import org.ircenter.server.dao.registration.RegistrationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.util.TextEscapeUtils;
import org.springframework.stereotype.Service;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Service
public class CustomSimpleUrlAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private RegistrationDAO registrationDAO;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String userName = request.getParameter("j_username");
        LoginFailureCounter.addLoginFailureByUserName(userName);
        String remoteHost = request.getRemoteHost();
        LoginFailureCounter.addLoginFailureByRemoutHost(remoteHost);
        boolean captchaNeededByUserName = LoginFailureCounter.isCaptchaNeededByUserName(userName);
        boolean captchaNeededByRemoteHost = LoginFailureCounter.isCaptchaNeededByRemoutHost(remoteHost);
        if (captchaNeededByUserName || captchaNeededByRemoteHost) {
            request.getSession().setAttribute("captchaNeeded", Boolean.TRUE);
        }
        String referer = request.getHeader("referer");
        saveException(request, exception);
        if (registrationDAO.isTmpUserPresent(userName)) {   //TODO страница на которой будет место для ввода кода с мыла
            if (referer.indexOf('?') == -1) {
                referer = referer + "?activate=1";
            } else {
                referer = referer + "&activate=1";
            }
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.setAttribute("activationUserName", userName);
            }
            getRedirectStrategy().sendRedirect(request, response, referer);
        } else {
            request.getSession().setAttribute("SPRING_SECURITY_LAST_USERNAME_KEY", TextEscapeUtils.escapeEntities(userName));
            Object isCaptchaNeeded = request.getSession().getAttribute("captchaNeeded");
            if (isCaptchaNeeded != null && ((Boolean) isCaptchaNeeded)) {
                Object captcha = request.getSession().getAttribute("captchaValid");
                if (captcha == null || (Boolean) captcha) {
                    loginErrorRedirect(request, response, referer);
                } else {
                    getRedirectStrategy().sendRedirect(request, response, referer);
                }
            } else {
                loginErrorRedirect(request, response, referer);
            }
        }
    }

    private void loginErrorRedirect(HttpServletRequest request, HttpServletResponse response, String referer) throws IOException {
        request.getSession().setAttribute("loginError", Boolean.TRUE);
        logger.debug("Redirecting to " + referer);
        getRedirectStrategy().sendRedirect(request, response, referer);
    }

    @Autowired
    public void setRegistrationDAO(RegistrationDAO registrationDAO) {
        this.registrationDAO = registrationDAO;
    }
}
