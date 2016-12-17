package org.ircenter.server.dao.group;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.bean.group.AssociationType;
import org.ircenter.server.bean.group.Group;
import org.ircenter.server.bean.group.GroupRoleType;
import org.ircenter.server.bean.group.GroupType;
import org.ircenter.server.service.user.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@Repository("groupDAO")
public class GroupDAO {

    private NamedParameterJdbcTemplate jdbcTemplate;
    private static final Log LOGGER = LogFactory.getLog(GroupDAO.class.getClass());


    @Autowired
    public void init(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private final RowMapper<Group> rowMapper = new RowMapper<Group>() {
        public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
            Group group = new Group();
            group.setId(rs.getLong("id"));
            group.setAssociationType(AssociationType.valueOf(rs.getInt("type")));
            group.setTitle(rs.getString("title"));
            group.setDescription(rs.getString("description"));
            group.setCountryId(rs.getInt("country_id"));
            group.setRegionId(rs.getInt("region_id"));
            group.setCityId(rs.getInt("city_id"));
            group.setGroupType(GroupType.values(rs.getInt("group_type")));
            return group;
        }
    };


    public Group getGroupById(long groupId) {
        String getUsersByGroupId = "SELECT * FROM users_group WHERE id=:id";
        try {
            return jdbcTemplate.queryForObject(getUsersByGroupId, new MapSqlParameterSource("id", groupId), rowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getUsersByGroupId  groupId= " + groupId, e);
            return null;
        }
    }

    @Transactional
    public Group saveUserGroup(final Group group) {

        final String insertGroup = "INSERT INTO groups (association_type,title,description,country_id,region_id,city_id,group_type) VALUES (:association_type,:title,:description,:country_id,:region_id,:city_id,:group_type)";
        group.setGroupType(GroupType.OPENED);

        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            SqlParameterSource parameterSource = new MapSqlParameterSource()
                    .addValue("association_type", group.getAssociationType().ordinal())
                    .addValue("title", group.getTitle())
                    .addValue("description", group.getDescription())
                    .addValue("country_id", group.getCountryId())
                    .addValue("region_id", group.getRegionId())
                    .addValue("city_id", group.getCityId())
                    .addValue("group_type", group.getGroupType().ordinal());

            jdbcTemplate.update(insertGroup, parameterSource, keyHolder);
            group.setId(keyHolder.getKey().longValue());

            switch (group.getAssociationType()) {
                case COMMUNITY:
                    final String insertCommunityGroup = "INSERT INTO groups_community (group_id,theme_id,subsection_id) VALUES(:group_id,:theme_id,:subsection_id)";
                    parameterSource = new MapSqlParameterSource()
                            .addValue("group_id", group.getId())
                            .addValue("theme_id", 1)
                            .addValue("subsection_id", 1);
                    jdbcTemplate.update(insertCommunityGroup, parameterSource);
                    break;
                case MEETING:
                    final String insertMeetingGroup = "INSERT INTO groups_metting (group_id,start_time) VALUES(:group_id,:start_time)";
                    parameterSource = new MapSqlParameterSource()
                            .addValue("group_id", group.getId())
                            .addValue("start_time", new java.util.Date());
                    jdbcTemplate.update(insertMeetingGroup, parameterSource);
                    break;
            }

            final String insertUserGroup = "INSERT INTO groups_user (group_id,user_id,role_type) VALUES(:group_id,:user_id,:role_type)";
            parameterSource = new MapSqlParameterSource()
                    .addValue("group_id", group.getId())
                    .addValue("user_id", UserHelper.getUserId())
                    .addValue("role_type", GroupRoleType.CREATOR.ordinal());
            jdbcTemplate.update(insertUserGroup, parameterSource);


        } catch (DataAccessException e) {
            LOGGER.error("Exception while saveGroup", e);
        }
        return group;
    }

    public boolean deleteGroup(long groupId) {
        String deleteUserGroup = "DELETE FROM users_group WHERE id=?";

        try {
            return jdbcTemplate.update(deleteUserGroup, new MapSqlParameterSource("id", groupId)) > 0;
        } catch (DataAccessException e) {
            LOGGER.error("Exception while deleteGroup groupId=" + groupId, e);
            return false;
        }
    }
}
