package org.ircenter.server.service.user;

import org.springframework.security.core.context.SecurityContextHolder;

public class UserHelper {

    public final static String UNREG_NAME = "Без регистрации";

    public static long getUserId() {
        try {
            return ((UserProfile) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        } catch (Exception e) {
            return -1L;
        }
    }

    public static UserProfile getUserProfile() {
        try {
            return ((UserProfile) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        } catch (Exception e) {
            return null;
        }
    }

    public static String getUserName() {
        try {
            return ((UserProfile) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        } catch (Exception e) {
            return "";
        }
    }

    public static String getName() {
        try {
            return ((UserProfile) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getLoginName();
        } catch (Exception e) {
            return "";
        }
    }

//    public static String getIrcUserName() {
//        try {
//            return ((UserProfile) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getIrcUserName();
//        } catch (Exception e) {
//            return "";
//        }
//    }

    public static long getUserCountryId() {
        try {
            return ((UserProfile) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getCountryId();
        } catch (Exception e) {
            return -1L;
        }
    }

    public static long getUserRegionId() {
        try {
            return ((UserProfile) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRegionId();
        } catch (Exception e) {
            return -1L;
        }
    }

    public static long getUserCityId() {
        try {
            return ((UserProfile) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getCityId();
        } catch (Exception e) {
            return -1L;
        }
    }

    public static boolean isBanned() {
        try {
            return ((UserProfile) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).isBanned();
        } catch (Exception e) {
            return false;
        }
    }
}
