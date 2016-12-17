package org.ircenter.server.service.session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.service.authentication.LoggedUsers;
import org.ircenter.server.service.user.UserProfile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MySessionListener implements HttpSessionListener {

    private static final Log LOGGER = LogFactory.getLog(MySessionListener.class.getClass());

    @Override
    public void sessionCreated(HttpSessionEvent arg0) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent arg0) {
        HttpSession session = arg0.getSession();
        SecurityContextImpl attribute = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
        if (attribute != null) {
            Authentication authentication = attribute.getAuthentication();
            UserProfile userInfo = (UserProfile) authentication.getPrincipal();
            LoggedUsers.removeLoggedUserInfo(userInfo.getUserId());
        }
    }
}
