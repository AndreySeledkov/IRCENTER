package org.ircenter.server.dao.group;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.bean.group.GroupRoleType;
import org.ircenter.server.bean.group.UsersGroup;
import org.ircenter.server.service.user.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("userGroupDAO")
public class UserGroupDAO {
    private NamedParameterJdbcTemplate jdbcTemplate;
    private static final Log LOGGER = LogFactory.getLog(UserGroupDAO.class.getClass());


    @Autowired
    public void init(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private final RowMapper<UsersGroup> usersGroupRowMapper = new RowMapper<UsersGroup>() {
        public UsersGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
            UsersGroup userGroup = new UsersGroup();
            userGroup.setId(rs.getLong("id"));
            userGroup.setUserId(rs.getLong("user_id"));
            userGroup.setGroupId(rs.getLong("group_id"));
            userGroup.setRoleType(GroupRoleType.valueOf(rs.getInt("role_type")));

            return userGroup;
        }
    };

    public List<UsersGroup> getUsersGroup() {
        String selectUsersGroup = "SELECT * FROM groups_user WHERE user_id=:user_id";

        try {
            return jdbcTemplate.queryForList(selectUsersGroup, new MapSqlParameterSource().addValue("user_id", UserHelper.getUserId()), UsersGroup.class);
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getUsersGroup ", e);
            return null;
        }


    }


}
