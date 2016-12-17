package org.ircenter.server.dao.favorite;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.bean.favorite.FavoritePost;
import org.ircenter.server.bean.news.PieceNews;
import org.ircenter.server.dao.news.NewsDAO;
import org.ircenter.server.service.user.UserHelper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 1
 * Date: 09.04.12
 * Time: 9:39
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class FavoritePostDAO {

    private static final Log LOGGER = LogFactory.getLog(FavoritePostDAO.class.getClass());
    private NamedParameterJdbcTemplate jdbcTemplate;
    private NewsDAO newsDAO;
    public static final int POST_PER_PAGE = 10;


    public NewsDAO getNewsDAO() {
        return newsDAO;
    }

//    @Autowired
//    public void setNewsDAO(NewsDAO newsDAO) {
//        this.newsDAO = newsDAO;
//    }
//
//    @Autowired
//    public void init(DataSource dataSource) {
//        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
//    }

    public void addFavoritePost(long postId) {
        String insertFavoritePost = "INSERT INTO favorite_posts (user_id, post_id) VALUES (:user_id, :post_id)" +
                " ON DUPLICATE KEY UPDATE post_id = :post_id";
        try {
            jdbcTemplate.update(insertFavoritePost, new MapSqlParameterSource("user_id", UserHelper.getUserId()).addValue("post_id", postId).addValue("post_id", postId));
        } catch (DataAccessException e) {
            LOGGER.error("exeption while add FavoritePost for postId = " + postId);
        }

    }

    private final RowMapper<FavoritePost> rowMapper = new RowMapper<FavoritePost>() {
        public FavoritePost mapRow(ResultSet rs, int rowNum) throws SQLException {
            FavoritePost favoritePost = new FavoritePost();

            favoritePost.setPostId(rs.getLong("post_id"));
            favoritePost.setUserId(rs.getLong("user_id"));

            return favoritePost;
        }
    };

    public List<PieceNews> getPagedFavoritePosts4User(int page) {

        String selectPostIdFromFavoritePosts = "SELECT * FROM favorite_posts" +
                " WHERE user_id =:user_id ORDER BY post_id DESC LIMIT :start,:end";

        List<FavoritePost> favoritePostList = jdbcTemplate.query(selectPostIdFromFavoritePosts, new MapSqlParameterSource("user_id", UserHelper.getUserId()).addValue("start", POST_PER_PAGE * page).addValue("end", POST_PER_PAGE * (page + 1)), rowMapper);
        List<PieceNews> pieceNewsList = new ArrayList<PieceNews>(favoritePostList.size());

        for (FavoritePost favoritePost : favoritePostList) {
            PieceNews pieceNews = newsDAO.getNews().get(favoritePost.getUserId());
            pieceNewsList.add(pieceNews);
        }

        return pieceNewsList;
    }

    /**
     * Удалить пост из избранного
     *
     * @param postId пост id
     * @return колличество удалённых строк
     */
    public int deleteFavoritePost(Long postId) {
        String deleteFromFavoritePosts = "DELETE FROM favorite_posts" +
                " WHERE user_id = :user_id AND post_id = :post_id";
        try {
            return jdbcTemplate.update(deleteFromFavoritePosts, new MapSqlParameterSource("user_id", UserHelper.getUserId()).addValue("post_id", postId));
        } catch (DataAccessException e) {
            LOGGER.error("exeption while deleting FavoritePost for postId = " + postId, e);
            return -1;
        }
    }

}
