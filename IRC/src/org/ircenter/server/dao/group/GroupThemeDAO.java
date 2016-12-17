package org.ircenter.server.dao.group;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.bean.group.GroupCommunitySubsection;
import org.ircenter.server.bean.group.GroupCommunityTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("groupThemeDAO")
public class GroupThemeDAO {

    private NamedParameterJdbcTemplate jdbcTemplate;
    private static final Log LOGGER = LogFactory.getLog(GroupThemeDAO.class.getClass());


    @Autowired
    public void init(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private final RowMapper<GroupCommunityTheme> themeRowMapper = new RowMapper<GroupCommunityTheme>() {
        public GroupCommunityTheme mapRow(ResultSet rs, int rowNum) throws SQLException {
            GroupCommunityTheme theme = new GroupCommunityTheme();
            theme.setId(rs.getLong("id"));
            theme.setTheme(rs.getString("theme"));

            return theme;
        }
    };

    private final RowMapper<GroupCommunitySubsection> subsectionThemeRowMapper = new RowMapper<GroupCommunitySubsection>() {
        public GroupCommunitySubsection mapRow(ResultSet rs, int rowNum) throws SQLException {
            GroupCommunitySubsection subsection = new GroupCommunitySubsection();
            subsection.setId(rs.getLong("id"));
            subsection.setTitle(rs.getString("title"));
            subsection.setThemeId(rs.getLong("theme_id"));
            return subsection;
        }
    };

    public List<GroupCommunityTheme> getGroupTheme() {
        String selectTheme = "SELECT * FROM groups_theme";

        try {
            return jdbcTemplate.queryForList(selectTheme, new MapSqlParameterSource(), GroupCommunityTheme.class);
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getGroupTheme ", e);
            return null;
        }


    }

    public List<GroupCommunitySubsection> getGroupThemeSubsection(long themeId) {
        String selectSubsectionTheme = "SELECT * FROM groups_subsection WHERE theme_id=:theme_id";

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("theme_id", themeId);
        return jdbcTemplate.queryForList(selectSubsectionTheme, parameterSource, GroupCommunitySubsection.class);
    }


}
