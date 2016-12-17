package org.ircenter.server.service.session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.dao.registration.RegistrationDAO;
import org.ircenter.server.dao.statistic.UserStatisticDAO;
import org.ircenter.server.dao.user.UserInfoDAO;
import org.ircenter.server.service.user.UserHelper;
import org.ircenter.server.web.registration.RegistrationFormController;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.UUID;


public class SessionCreationFilter extends OncePerRequestFilter {

    private static final Log LOGGER = LogFactory.getLog(SessionCreationFilter.class.getClass());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpSession httpSession = request.getSession(false);
        Cookie[] cookies = request.getCookies();
        if (httpSession == null) {
            boolean isIrcCookie = false;
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("irc".equals(cookie.getName())) {
                        isIrcCookie = true;
                    }
                }
            }
            if (isIrcCookie) {
                UserStatisticDAO.increaseNotUniqCount();
            } else {
                Cookie cookie = new Cookie("irc", UUID.randomUUID().toString());
                GregorianCalendar calendar = new GregorianCalendar();
                long currentTime = calendar.getTimeInMillis();
                calendar.add(GregorianCalendar.DAY_OF_YEAR, 1);
                calendar.add(GregorianCalendar.HOUR_OF_DAY, -calendar.get(GregorianCalendar.HOUR_OF_DAY));
                calendar.add(GregorianCalendar.MINUTE, -calendar.get(GregorianCalendar.MINUTE));
                calendar.add(GregorianCalendar.SECOND, -calendar.get(GregorianCalendar.SECOND) - 1);
                int time2nextDayInSec = (int) ((calendar.getTimeInMillis() - currentTime) / 1000);
                cookie.setMaxAge(time2nextDayInSec);
                cookie.setPath("/");
                response.addCookie(cookie);
                UserStatisticDAO.increaseUniqCount();
            }
            httpSession = request.getSession(true);
            //httpSession.setAttribute("ctry", getCtry(request));
            httpSession.setAttribute("ctry", "ua");
        }
        if (UserHelper.getUserId() == -1) {
            boolean isIrcRegCookie = false;
            String ircRegVal = "";
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("ircr".equals(cookie.getName())) {
                        isIrcRegCookie = true;
                        ircRegVal = cookie.getValue();
                    }
                }
            }
            if (isIrcRegCookie) {      //TODO ?????     how does it work
                ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
                UserInfoDAO userInfoDAO = (UserInfoDAO) context.getBean("userInfoDAO");
                if (userInfoDAO.getLoginCountByLogin(ircRegVal) > 0) {
                    RegistrationFormController registrationFormController
                            = (RegistrationFormController) context.getBean("registrationFormController");
                    registrationFormController.authenticate(request, response, ircRegVal);
                }
                RegistrationDAO registrationDAO = (RegistrationDAO) context.getBean("registrationDAO");
                if (registrationDAO.isTmpUidPresent(ircRegVal)) {
                    if (httpSession == null) {
                        httpSession = request.getSession(true);
                    }
                    httpSession.setAttribute("activation", true);
                }
            }
        }
        if (httpSession != null && httpSession.getAttribute("ctry") == null) {
            //httpSession.setAttribute("ctry", getCtry(request));
            httpSession.setAttribute("ctry", "ua");
        }
        filterChain.doFilter(request, response);
    }
}
