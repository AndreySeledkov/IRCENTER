package org.ircenter.server.service.authentication;

import org.ircenter.server.service.user.UserProfile;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class CustomSimpleUrlAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        setUseReferer(true);
        request.getSession().setAttribute("loginError", Boolean.FALSE);
        request.getSession().setAttribute("captchaValid", Boolean.TRUE);
        request.getSession().setAttribute("captchaNeeded", Boolean.FALSE);
        LoginFailureCounter.removeByUserName(request.getParameter("j_username"));
        LoginFailureCounter.removeByRemoutHost(request.getRemoteHost());
        super.onAuthenticationSuccess(request, response, authentication);
        LoggedUsers.addLoggedUserInfo((UserProfile) authentication.getPrincipal());
    }
}
