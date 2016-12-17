package org.ircenter.server.service.user.options;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.bean.user.options.UserOptions;
import org.ircenter.server.dao.Database;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: Seledkov Kostyantyn
 * Date: 23.04.12
 * Time: 10:26
 */
public class UserOptionService {
    private static final Log LOGGER = LogFactory.getLog(UserOptionService.class.getClass());
    private Database database;

    @Autowired
    public void setDatabase(Database database) {

    }

    public UserOptions getUserOptions(long userId) {
        return getFromCache(userId);
    }

    private UserOptions getFromCache(long userId) {
        return database.getUserOptionsDAO().loadUserOptions(userId);
    }

    public boolean savePrivacyOptions(UserOptions o) {
        boolean ok = database.getUserOptionsDAO().savePrivacyOptions(o);
        return ok;
    }

    public boolean saveUserOptions(UserOptions o) {
        boolean ok = database.getUserOptionsDAO().saveUserOptions(o);
        return ok;
    }
}
