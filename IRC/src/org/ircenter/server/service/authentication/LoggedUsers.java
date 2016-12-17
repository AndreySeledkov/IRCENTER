package org.ircenter.server.service.authentication;

import org.ircenter.server.service.user.UserProfile;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class LoggedUsers {

    private static Map<Long, UserProfile> loggedUsers = new ConcurrentHashMap<Long, UserProfile>();

    public static void addLoggedUserInfo(UserProfile userInfo) {
        loggedUsers.put(userInfo.getUserId(), userInfo);
    }

    public static UserProfile getLoggedUserInfo(long userId) {
        return loggedUsers.get(userId);
    }

    public static int getLoggedUsersCount() {
        return loggedUsers.size();
    }


    public static void removeLoggedUserInfo(long userId) {
        loggedUsers.remove(userId);
    }

}
