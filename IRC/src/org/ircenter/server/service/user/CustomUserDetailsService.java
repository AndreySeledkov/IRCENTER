package org.ircenter.server.service.user;

import org.ircenter.server.dao.user.UserInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service("customUserDetailsService")
public class CustomUserDetailsService extends JdbcDaoImpl {

    private UserInfoDAO userInfoDAO;

    @Override
    protected UserDetails createUserDetails(String username, UserDetails userFromUserQuery, List<GrantedAuthority> combinedAuthorities) {
        if (userInfoDAO.getLoginCountByLogin(username) == 0 && userInfoDAO.getNameCount(username) > 0) {
            username = userInfoDAO.getLoginByName(username);
        }
//        String returnUsername = userFromUserQuery.getUsername();
//        if (!isUsernameBasedPrimaryKey()) { //TODO ????
//            returnUsername = username;
//        }

        return load(username);
    }

    @Override
    protected List<GrantedAuthority> loadUserAuthorities(String username) {
        long userId = UserHelper.getUserId();
        return userInfoDAO.getAuthorities(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return load(username);
    }

    private UserProfile load(String username) {
        return userInfoDAO.getUserByLogin(username);
    }

    @Autowired
    public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
        this.userInfoDAO = userInfoDAO;
    }

    @Autowired
    public void init(DataSource dataSource) {
        setDataSource(dataSource);
    }
}
