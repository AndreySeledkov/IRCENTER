package org.ircenter.server.dao.group;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.bean.group.MeetingGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;


@Repository("meetingGroupDAO")
public class MeetingGroupDAO {
    private NamedParameterJdbcTemplate jdbcTemplate;
    private static final Log LOGGER = LogFactory.getLog(MeetingGroupDAO.class.getClass());


    @Autowired
    public void init(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public MeetingGroup getMeetingGroupById(long groupId) {
        String selectMettingGroupById = "SELECT * FROM groups_metting WHERE group_id=:group_id";

        try {
            return jdbcTemplate.queryForObject(selectMettingGroupById, new MapSqlParameterSource().addValue("group_id", groupId), MeetingGroup.class);
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getGroupTheme ", e);
            return null;
        }
    }
}
