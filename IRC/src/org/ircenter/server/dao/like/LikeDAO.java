package org.ircenter.server.dao.like;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.bean.like.LikeEntity;
import org.ircenter.server.bean.like.ServiceType;
import org.ircenter.server.service.like.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * User: Seledkov Kostyantyn
 * Date: 18.05.12
 * Time: 1:37
 */
@Repository("likeDAO")
public class LikeDAO {
    private static final Log LOGGER = LogFactory.getLog(LikeDAO.class.getClass());

    private NamedParameterJdbcTemplate jdbcTemplate;

    public LikeService.QueryStatus updateLikeItem(LikeEntity likeEntity) {
        LikeService.QueryStatus status = null;
        String update = "INSERT INTO user_like (user_id,client_id,content_type,content_id,may_like,date) VALUES(:user_id,:client_id,:content_type,:content_id,:may_like,:date)";

        try {
            jdbcTemplate.update(update, new MapSqlParameterSource("user_id", likeEntity.getUserId()).
                    addValue("client_id", likeEntity.getClientId()).addValue("content_type", likeEntity.getType().getDbType()).
                    addValue("content_id", likeEntity.getContentId()).addValue("may_like", likeEntity.getLike()).addValue("date", likeEntity.getDate()));
            status = LikeService.QueryStatus.INSERTED;
        } catch (DataAccessException ex) {
            LOGGER.error("", ex);

            update = "UPDATE user_like SET may_like = :may_like, date = NOW() WHERE content_type = :content_type AND content_id = :content_id AND user_id = :user_id AND client_id = :client_id AND may_like != :may_like";
            try {
                int updatedCount = jdbcTemplate.update(update, new MapSqlParameterSource("may_like", likeEntity.getLike()).addValue("content_type", likeEntity.getType().getDbType())
                        .addValue("content_id", likeEntity.getContentId()).addValue("user_id", likeEntity.getUserId()).addValue("client_id", likeEntity.getClientId()).addValue("may_like", likeEntity.getLike()));
                status = updatedCount > 0 ? LikeService.QueryStatus.UPDATED_WITH_ROW_EFFECT : LikeService.QueryStatus.UPDATED_WITHOUT_ROW_EFFECT;
            } catch (Exception ex1) {
                LOGGER.error("", ex);
            }
        }
        return status;
    }

    private RowMapper<LikeEntity> likeEntityRowMapper = new RowMapper<LikeEntity>() {
        @Override
        public LikeEntity mapRow(ResultSet result, int i) throws SQLException {
            LikeEntity entity = new LikeEntity();
            entity.setUserId(result.getLong("user_id"));
            entity.setClientId(result.getLong("client_id"));
            entity.setType(ServiceType.valueOfDbType(result.getInt("content_type")));
            entity.setContentId(result.getLong("content_id"));
            entity.setLike((byte) result.getInt("may_like"));
            entity.setDate(result.getTimestamp("date"));
            return entity;
        }
    };

    public List<LikeEntity> getLikeInfo(ServiceType type, long contentId) {
        List<LikeEntity> content;
        String query = "SELECT * FROM user_like where content_type = " + type.getDbType() + " AND content_id = " + contentId;
        try {
            content = jdbcTemplate.query(query, new MapSqlParameterSource(), likeEntityRowMapper);
        } catch (DataAccessException ex) {
            LOGGER.error("", ex);
            content = Collections.EMPTY_LIST;
        }
        return content;
    }

    @Autowired
    public void init(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
}
