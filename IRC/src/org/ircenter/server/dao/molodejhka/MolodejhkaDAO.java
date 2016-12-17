package org.ircenter.server.dao.molodejhka;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.bean.molodejhka.MolodejhkaVideo;
import org.ircenter.server.dao.AbstractDAO;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * User: Seledkov Kostyantyn
 * Date: 24.05.12
 * Time: 2:40
 */
@Repository("molodejhkaDAO")
public class MolodejhkaDAO extends AbstractDAO {
    private static final Log LOGGER = LogFactory.getLog(MolodejhkaDAO.class.getClass());

    public MolodejhkaDAO() {
    }

    private final static RowMapper<MolodejhkaVideo> rowMapper = new RowMapper<MolodejhkaVideo>() {
        @Override
        public MolodejhkaVideo mapRow(ResultSet resultSet, int i) throws SQLException {
            MolodejhkaVideo video = new MolodejhkaVideo();
            video.setId(resultSet.getLong("id"));
            video.setUserId(resultSet.getLong("user_id"));
            video.setYoutubeId(resultSet.getString("youtube_id"));
            video.setDate(resultSet.getDate("date"));
            video.setLink(resultSet.getString("video_link"));
            video.setDescription(resultSet.getString("description"));
            video.setText(resultSet.getString("text"));

            return video;
        }
    };

    public MolodejhkaVideo getVideo(long id) {
        MolodejhkaVideo video = null;
        String query = "SELECT * FROM molodejhka_videos WHERE id = :id";
        try {
            video = namedParameterJdbcTemplate.queryForObject(query, new MapSqlParameterSource("id", id), rowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("", e);
        }
        return video;
    }

    public List<MolodejhkaVideo> getVideos(int offset, int count) {
        List<MolodejhkaVideo> video;
        String query = "SELECT * FROM molodejhka_videos ORDER BY id DESC LIMIT :offset,:count";
        try {
            video = namedParameterJdbcTemplate.query(query, new MapSqlParameterSource("offset", offset).addValue("count", count), rowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("", e);
            video = Collections.EMPTY_LIST;
        }
        return video;
    }

    public void addVideo(long userId, String youtubeId, String link, String description, String text) {
        String query = "INSERT INTO molodejhka_videos(user_id,youtube_id,video_link,date,description,text) VALUES (:user_id,:youtube_id,:video_link,NOW(),:description,:text)";
        try {
            namedParameterJdbcTemplate.update(query, new MapSqlParameterSource("user_id", userId).addValue("youtube_id", youtubeId).
                    addValue("video_link", link).addValue("description", description).addValue("text", text));
        } catch (DataAccessException e) {
            LOGGER.error("", e);
        }
    }

    public void editVideo(String youtubeId, String link, String description, String text, long id) {
        String query = "UPDATE molodejhka_videos SET youtube_id=:youtubeId,video_link=:video_link, description=:description, date=NOW(),text=:text WHERE id=:id";
        try {
            namedParameterJdbcTemplate.update(query, new MapSqlParameterSource("youtubeId", youtubeId).addValue("video_link", link).
                    addValue("description", description).addValue("text", text).addValue("id", id));
        } catch (DataAccessException e) {
            LOGGER.error("", e);
        }
    }

    public void removeVideo(long id) {
        String query = "DELETE FROM molodejhka_videos WHERE id=:id";
        try {
            namedParameterJdbcTemplate.update(query, new MapSqlParameterSource("id", id));
        } catch (DataAccessException e) {
            LOGGER.error("", e);
        }
    }

    public int getTotalVideosCount() {
        int count = 0;
        String query = "SELECT COUNT(*) FROM molodejhka_videos";
        try {
            count = jdbcTemplate.queryForInt(query);
        } catch (DataAccessException e) {
            LOGGER.error("", e);
        }
        return count;
    }
}
