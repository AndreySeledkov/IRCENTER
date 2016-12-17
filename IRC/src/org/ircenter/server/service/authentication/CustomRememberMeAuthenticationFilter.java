package org.ircenter.server.service.authentication;

import org.ircenter.server.service.user.UserProfile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class CustomRememberMeAuthenticationFilter extends RememberMeAuthenticationFilter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        boolean isLogged = SecurityContextHolder.getContext().getAuthentication() != null;
        super.doFilter(req, res, chain);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!isLogged && authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserProfile) {
                String userName = ((UserProfile) principal).getUsername();
                if (userName != null) {
                    LoggedUsers.addLoggedUserInfo((UserProfile) principal);
                }
            }
        }
    }
}
