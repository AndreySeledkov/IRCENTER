package org.ircenter.server.service.user.options;

import org.ircenter.server.bean.user.options.PrivacyOptions;
import org.ircenter.server.dao.Database;
import org.ircenter.server.service.user.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: Seledkov Kostyantyn
 * Date: 23.04.12
 * Time: 10:01
 */
public class PrivacyChecker {
    private Database database;

    public static enum Action {
        WRITE_MESSAGE;  //Кто может писать мне сообщения
    }

    public PrivacyChecker() {
    }

    public static PrivacyOptions.Type getOption(Action action, UserProfile info) {
        PrivacyOptions options = info.getPrivacyOptions();
        switch (action) {
            case WRITE_MESSAGE:
                return options.getWriteMessage();
            default:
                throw new IllegalArgumentException("wrong type: " + action);
        }
    }

    public boolean check(Action action, UserProfile info, long myId) {       //TODO add admins
        if ((info.getUserId() == myId)) { //|| (getDatabase().getAdminContainer().isAdmin(myId))
            return true;
        }

        PrivacyOptions.Type t = getOption(action, info);

        switch (t) {
            case EVERYBODY:
                return true;
            case NOBODY:
                return false;
            case FRIENDS:
                return database.getFriendsDAO().isFriends(info.getUserId(), myId);
            default:
                throw new IllegalArgumentException("some shit here..");
        }
    }

    public boolean check(Action action, long userId, long myId) {
        if (userId == myId) {
            return true;
        }
        return check(action, database.getUserInfoDAO().getUser(userId), myId);
    }

    @Autowired
    public void setDatabase(Database database) {
        this.database = database;
    }

}
