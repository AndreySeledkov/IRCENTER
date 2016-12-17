package org.ircenter.server.dao.images;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.serv.ImageServer;
import org.ircenter.serv.dataserver.ImageService;
import org.ircenter.server.MUtil;
import org.ircenter.server.bean.images.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * User: Seledkov Kostyantyn
 * Date: 15.05.12
 * Time: 8:44
 */
@Repository("imageDAO")
public class ImageDAO {
    public static final int DEFAULT_COUNT_PER_PAGE = 10;
    private static final Log LOGGER = LogFactory.getLog(ImageDAO.class.getClass());

    private NamedParameterJdbcTemplate jdbcTemplate;
    private ImageServer imageServer;

    public ImageDAO() {
    }

    private static String getHash(byte[] data) {
        return MUtil.md5(data);
    }

    public AddImageResult addNewPhoto(String title, long userId, byte[] originalBytes) {

//        byte[] scaled = imageServer.scale(originalBytes, ImageService.IMG_SIZE_ORIGINAL);
//        if (scaled == null) {
//            return new AddImageResult(AddImageResult.NOT_IMAGE);
//        }
        String hash = getHash(originalBytes);

        long newImageId = 0;
        try {
            String insert = "INSERT INTO imagesdescr(user_id,title, image_date,hash)  VALUES (:user_id,:title, NOW(), :hash)";
            KeyHolder keyGenerator = new GeneratedKeyHolder();
            jdbcTemplate.update(insert, new MapSqlParameterSource("user_id", userId).addValue("title", title).addValue("hash", hash), keyGenerator);
            newImageId = keyGenerator.getKey().longValue();
        } catch (Exception ex) {
            LOGGER.error("", ex);
        }

        if (newImageId > 0) {
            //save binaries
            boolean ok = imageServer.addImage(newImageId, originalBytes);
            if (!ok) {
                String update = "DELETE FROM imagesdescr WHERE image_id=" + newImageId;
                try {
                    jdbcTemplate.update(update, new MapSqlParameterSource());
                } catch (DataAccessException e) {
                    LOGGER.error("", e);
                }
            }
        }

        if (newImageId > 0) {
            return new AddImageResult(AddImageResult.OK, newImageId);
        }
        return new AddImageResult(AddImageResult.UNKNOWN_ERROR, 0);
    }

    public void deletePhoto(long imageId) {
        boolean ok = false;
        //binaries deleted by scheduler each day
        String update = "DELETE FROM imagesdescr  WHERE image_id=" + imageId;
        try {
            ok = jdbcTemplate.update(update, new MapSqlParameterSource()) > 0;
        } catch (DataAccessException e) {
            LOGGER.error("", e);
        }
        if (ok) {
            imageServer.removeImage(imageId);
        }
    }

    public List<Image> getImages(int offset, int count) {
        String query = "SELECT * FROM imagesdescr ORDER BY image_id DESC  LIMIT :offset,:count";
        List<Image> images = null;
        try {
            images = jdbcTemplate.query(query, new MapSqlParameterSource("offset", offset).addValue("count", count), new RowMapper<Image>() {
                @Override
                public Image mapRow(ResultSet resultSet, int i) throws SQLException {
                    Image image = new Image();
                    image.setImageId(resultSet.getLong("image_id"));
                    image.setTitle(resultSet.getString("title"));
                    image.setUserId(resultSet.getLong("user_id"));
                    image.setDate(resultSet.getTime("image_date"));
                    return image;
                }
            });
        } catch (DataAccessException e) {
            LOGGER.error("", e);
        }
        return images;
    }

    public int getTotalCount() {
        String query = "SELECT COUNT(*) FROM imagesdescr";
        try {
            return jdbcTemplate.queryForInt(query, new MapSqlParameterSource());
        } catch (DataAccessException e) {
            LOGGER.error("", e);
        }
        return 0;
    }

    @Autowired
    public void init(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Autowired
    public void setImageServer(ImageServer imageServer) {
        this.imageServer = imageServer;
    }
}
