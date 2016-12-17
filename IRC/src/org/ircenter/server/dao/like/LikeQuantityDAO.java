package org.ircenter.server.dao.like;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.bean.like.LikeEntity;
import org.ircenter.server.bean.like.LikeQuantity;
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

/**
 * User: Seledkov Kostyantyn
 * Date: 18.05.12
 * Time: 1:37
 */
@Repository("likeQuantityDAO")
public class LikeQuantityDAO {
    private static final Log LOGGER = LogFactory.getLog(LikeQuantityDAO.class.getClass());

    private NamedParameterJdbcTemplate jdbcTemplate;

    private RowMapper<LikeQuantity> likeQuantityRowMapper = new RowMapper<LikeQuantity>() {
        @Override
        public LikeQuantity mapRow(ResultSet r, int i) throws SQLException {
            LikeQuantity quantity = new LikeQuantity();
            quantity.setType(ServiceType.valueOfDbType(r.getInt("content_type")));
            quantity.setContentId(r.getLong("content_id"));
            quantity.setLikeCount(r.getInt("like_count"));
            quantity.setDislikeCount(r.getInt("dislike_count"));
            return quantity;
        }
    };

    public void updateLikeItem(LikeEntity likeEntity, LikeService.QueryStatus status) {
        String update;
        int like = likeEntity.getLike() > 0 ? 1 : 0;
        int dislike = likeEntity.getLike() < 0 ? 1 : 0;
        if (status == LikeService.QueryStatus.INSERTED) {
            update = "INSERT INTO user_like_quantity (content_type,content_id,like_count,dislike_count) VALUES (:content_type,:content_id,:like_count,:dislike_count)"
                    + "ON DUPLICATE KEY UPDATE like_count = IF( :l = 1,like_count + 1,like_count) , dislike_count =IF(:d = 1,dislike_count + 1,dislike_count)";
            try {
                jdbcTemplate.update(update, new MapSqlParameterSource("content_type", likeEntity.getType().getDbType()).addValue("content_id", likeEntity.getContentId())
                        .addValue("like_count", like).addValue("dislike_count", dislike).addValue("l", like).addValue("d", dislike));
            } catch (DataAccessException ex) {
                LOGGER.error("", ex);
            }
        } else if (status == LikeService.QueryStatus.UPDATED_WITH_ROW_EFFECT) {
            update = "UPDATE user_like_quantity SET like_count = IF(" + like + " = 1,like_count + 1,like_count - 1) , dislike_count = IF(" + like + " = 0,dislike_count + 1,dislike_count - 1)  WHERE content_type = " + likeEntity.getType().getDbType() + " AND content_id = " + likeEntity.getContentId();
            jdbcTemplate.update(update, new MapSqlParameterSource());
        }
    }

    public LikeQuantity getQuantityInfo(ServiceType type, long contentId) {
        LikeQuantity content = null;
        String query = "SELECT * FROM user_like_quantity where content_type = " + type.getDbType() + " AND content_id = " + contentId;
        try {
            content = jdbcTemplate.queryForObject(query, new MapSqlParameterSource(), likeQuantityRowMapper);
        } catch (DataAccessException ex) {
            LOGGER.error("", ex);
        }
        if (content == null) {
            content = new LikeQuantity();
            content.setType(type);
            content.setContentId(contentId);
            content.setLikeCount(0);
            content.setDislikeCount(0);
        }

        return content;
    }

    @Autowired
    public void init(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
}
