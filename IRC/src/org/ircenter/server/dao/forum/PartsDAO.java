package org.ircenter.server.dao.forum;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.bean.forum.*;
import org.ircenter.server.bean.forum.MainPart;
import org.ircenter.server.bean.forum.Part;
import org.ircenter.server.dao.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository("partsDAO")
public class PartsDAO {

    private JdbcTemplate jdbcTemplate;
    private static final Log LOGGER = LogFactory.getLog(PartsDAO.class.getClass());
    private Database database;

    @Autowired
    public void init(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
    }

    private final RowMapper<MainPart> rowMainPartMapper = new RowMapper<MainPart>() {
        public MainPart mapRow(ResultSet rs, int rowNum) throws SQLException {
            MainPart mainPart = new MainPart();
            mainPart.setId(rs.getLong("mainpart_id"));
            mainPart.setOrderIndex(rs.getInt("orderIndex"));
            mainPart.setName(rs.getString("mainpartname"));
            mainPart.setParts(getParts(mainPart.getId()));
            return mainPart;


        }
    };

    private final RowMapper<Part> rowPartMapper = new RowMapper<Part>() {
        public Part mapRow(ResultSet rs, int rowNum) throws SQLException {
            Part part = new Part();
            part.setId(rs.getLong("part_id"));
            part.setMainPart(rs.getLong("mainpart_id"));
            part.setOrderIndex(rs.getInt("orderIndex"));
            part.setName(rs.getString("themename"));
            part.setThemes(database.getThemeDAO().getThemesIndex(part));
            part.setMessagesCount(rs.getLong("messages"));
            part.setThemeCount(rs.getLong("themes"));
            return part;


        }
    };

    public List<MainPart> getListMainPart() {
        String getListMainPart = "SELECT *  FROM forum_mainpart ORDER BY orderIndex";

        try {
            return jdbcTemplate.query(getListMainPart, rowMainPartMapper);
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getListMainPart = ", e);
            return Collections.emptyList();
        }
    }

    public List<Part> getParts(long mainPartId) {
        String getListParts = "SELECT * FROM forum_part where mainpart_id=? order by orderIndex";
        try {
            return new LinkedList<Part>(jdbcTemplate.query(getListParts, new Object[]{mainPartId}, rowPartMapper));
        } catch (DataAccessException e) {
            LOGGER.error("Exception while getParts = ", e);
            return Collections.emptyList();
        }
    }

    public Part get(long id) {
        String getPartById = "SELECT * FROM forum_part WHERE part_id=?";

        try {
            return jdbcTemplate.queryForObject(getPartById, new Object[]{id}, rowPartMapper);
        } catch (DataAccessException e) {
            LOGGER.error("exeption while get part = ", e);
            return null;
        }
    }

    public void addTheme(Theme theme, Message message) {
        Part part = get(theme.getPartId());
        List<Long> themes = part.getThemes();
        themes.remove(theme.getId());
        if (theme.isExcluded()) {
            return;
        }
        themes.add(0, theme.getId());
//        if (message.isFirstMessage()) {
//            increaseThemes(part);
//        }
    }

    private void increaseThemes(Part part) {
        String increasePartTheme = "UPDATE forum_part SET themes=? WHERE part_id=?";

        part.setThemeCount(part.getThemeCount() + 1);
        try {
            jdbcTemplate.update(increasePartTheme, part.getThemeCount(), part.getId());
        } catch (DataAccessException e) {
            LOGGER.error("Exception while increaseThemes", e);
        }
    }

    public void increaseMessages(long partId) {
        String increaseMessage = "UPDATE forum_part SET messages=? WHERE part_id=?";

        Part part = get(partId);
        part.setMessagesCount(part.getMessagesCount() + 1);
        try {
            jdbcTemplate.update(increaseMessage, part.getMessagesCount(), part.getId());
        } catch (DataAccessException e) {
            LOGGER.error("Exception while increaseMessages", e);
        }
    }

    @Autowired
    public void setDatabase(Database database) {
        this.database = database;
    }
}
