package org.ircenter.server.dao.comment;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.bean.comment.Comment;
import org.ircenter.server.dao.user.UserInfoDAO;
import org.ircenter.server.service.user.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Repository
public class CommentDAO {

    private JdbcTemplate jdbcTemplate;

    protected final Log logger = LogFactory.getLog(getClass());

    private static final String INSERT_COMMENT = "INSERT INTO comments (post_id, user_id, comment_date,comment, anonym," +
            " checked) VALUES (?, ?, ?, ?, ?, ?)";

    private Long postOwnerUserId;


    private final RowMapper<Comment> rowMapper = new RowMapper<Comment>() {
        public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
            Comment comment = new Comment();
            comment.setPostId(rs.getLong("post_id"));
            long userId = rs.getLong("user_id");
            comment.setUserId(userId);
            comment.setPostOwnerUserId(postOwnerUserId);
            long commentid = rs.getLong("comment_id");
            comment.setCommentId(commentid);
            comment.setComment(rs.getString("text"));
            comment.setChecked(rs.getBoolean("checked"));
            comment.setCreated(rs.getTimestamp("comment_date"));
            comment.setName(userInfoDAO.getName(userId));
            comment.setAnonym(rs.getBoolean("anonym"));
            comment.setLikeCount(rs.getInt("like_count"));
            return comment;
        }
    };

    private static final String SELECT_COMMENTS_4_POST_ID = "SELECT * FROM comments WHERE post_id = ?" +
            " ORDER BY comment_id DESC";

    private UserInfoDAO userInfoDAO;

    private static final String INSERT_INTO_READED_COMMENTS = "INSERT INTO readed_comments (user_id, post_id, count)" +
            " VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE COUNT = ?";

    private static final String UPDATE_COMMENT_CONTENT
            = "UPDATE comments SET COMMENT = '[\"Комментарий удалён администрацией\"]' WHERE comment_id = ?";

    private static final String DECREASE_ON_ONE_READED_COMMENTS = "UPDATE readed_comments SET count = (count - 1)" +
            " WHERE post_id = ?";

    @Autowired
    public void init(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
    }

    /**
     * Добавить коментарий к посту
     *
     * @param postId   Id поста
     * @param comment  коментарий
     * @param isAnonym анонимный или нет
     * @return id комментария
     */
    public Number addComment(final long postId, final String comment, final Boolean isAnonym) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final Long userId = UserHelper.getUserId();
        try {
            jdbcTemplate.update(
                    new PreparedStatementCreator() {
                        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                            PreparedStatement ps
                                    = connection.prepareStatement(INSERT_COMMENT,
                                    new String[]{"post_id", "user_id", "comment", "anonym", "pictures_count",
                                            "audios_count", "videos_count"});
                            ps.setLong(1, postId);
                            ps.setLong(2, userId);
                            ps.setString(3, comment);
                            ps.setBoolean(4, isAnonym);
                            return ps;
                        }
                    }, keyHolder);
        } catch (DataAccessException e) {
            logger.error("exeption while insert comment postId = " + postId + " userId = " + userId, e);
        }
        return keyHolder.getKey();
    }

    /**
     * Получить все коментарии поста
     *
     * @param postId          Id поста
     * @param postOwnerUserId Id владельца поста
     * @return все коментарии поста
     */
    public List<Comment> getComments(Long postId, Long postOwnerUserId) {
        this.postOwnerUserId = postOwnerUserId;
        try {
            return jdbcTemplate.query(SELECT_COMMENTS_4_POST_ID, new Object[]{postId}, rowMapper);
        } catch (DataAccessException e) {
            logger.error("exeption while getting comments for post_id = " + postId, e);
            return Collections.emptyList();
        }
    }

    public List<Comment> getComments(int start, int limit) {
        String getComments = "SELECT * FROM comments WHERE checked=FALSE LIMIT ?,? ";
        try {
            return jdbcTemplate.query(getComments, new Object[]{start, limit}, rowMapper);
        } catch (DataAccessException e) {
            logger.error("Exception while getComments = ", e);
            return Collections.emptyList();
        }
    }

    public Comment getComment(long id) {
        String getComment = "SELECT * FROM comments WHERE id=?";
        try {
            return jdbcTemplate.queryForObject(getComment, new Object[]{id}, rowMapper);
        } catch (DataAccessException e) {
            logger.error("Exception while getThemesCount = ", e);
            return null;
        }
    }

    public void removeComment(long id) {
        String removeComment = "DELETE FROM comments WHERE comment_id=?";
        try {
            jdbcTemplate.update(removeComment, new Object[]{id});
        } catch (DataAccessException e) {
            logger.error("Exception while removeComment = ", e);
        }
    }

    public int getUncheckedCommentsCount() {
        String getUncheckedCommentsCount = "SELECT COUNT(1) FROM comments WHERE checked=FALSE";
        try {
            return jdbcTemplate.queryForInt(getUncheckedCommentsCount, new Object[]{});
        } catch (DataAccessException e) {
            logger.error("Exception while getUncheckedCommentsCount = ", e);
            return 0;
        }
    }

    public int updateCheckComments(long id) {
        String updateCheckComments = "UPDATE comments SET checked=true WHERE comment_id=?";
        try {
            return jdbcTemplate.update(updateCheckComments, new Object[]{id});
        } catch (DataAccessException e) {
            logger.error("Exception while updateCheckComments = ", e);
            return 0;
        }
    }


//    public int getUnChekedCountComments(){
//
//        try {
//            return jdbcTemplate.query(SELECT_COMMENTS_4_POST_ID, new Object[]{postId}, rowMapper);
//        } catch (DataAccessException e) {
//            logger.error("exeption while getting comments for post_id = " + postId, e);
//            return Collections.emptyList();
//        }
//    }


    @Autowired
    public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
        this.userInfoDAO = userInfoDAO;
    }

    /**
     * Обновить информацию о прочитанных комментариях для клиента
     *
     * @param postId      Id поста
     * @param readedCount количество прочитанных сообщений
     */
    public void updateReadedComments(Long postId, Long readedCount) {
        Long userId = UserHelper.getUserId();
        if (userId != -1) {
            try {
                jdbcTemplate.update(INSERT_INTO_READED_COMMENTS, userId, postId, readedCount, readedCount);
            } catch (DataAccessException e) {
                logger.error("exception while update readed comments postId = " + postId + " userId = " + userId);
            }
        }
    }

    /**
     * Удалить текст коментарий
     *
     * @param commentId Id коментария
     */
    public void updateCommentContent(Long commentId) {
        try {
            jdbcTemplate.update(UPDATE_COMMENT_CONTENT, commentId);
        } catch (DataAccessException e) {
            logger.error("error deleting comment content with id " + commentId, e);
        }
    }

    /**
     * Уменьшить на единицу количество виденных всеми пользователями коментариев к посту
     *
     * @param postId Id поста
     */
    public void decreaseByOneReadedComments(Long postId) {
        try {
            jdbcTemplate.update(DECREASE_ON_ONE_READED_COMMENTS, postId);
        } catch (DataAccessException e) {
            logger.error("error decreasing readed comments for post with id " + postId, e);
        }
    }
}
