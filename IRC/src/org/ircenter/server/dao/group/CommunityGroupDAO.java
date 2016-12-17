package org.ircenter.server.dao.group;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.bean.group.CommunityGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository("communityGroupDAO")
public class CommunityGroupDAO {
    private NamedParameterJdbcTemplate jdbcTemplate;
    private static final Log LOGGER = LogFactory.getLog(CommunityGroupDAO.class.getClass());


    @Autowired
    public void init(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    public CommunityGroup getCommunityGroupById(long groupId) {
        String selectCommunityGroupById = "SELECT * FROM groups_community WHERE group_id=:group_id";

        try {
            return jdbcTemplate.queryForObject(selectCommunityGroupById, new MapSqlParameterSource().addValue("group_id", groupId), CommunityGroup.class);
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getMeetingGroupById ", e);
            return null;
        }
    }

}
