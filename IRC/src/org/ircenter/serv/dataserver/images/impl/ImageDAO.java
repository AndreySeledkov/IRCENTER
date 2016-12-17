package org.ircenter.serv.dataserver.images.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User: Seledkov Kostyantyn
 * Date: 15.05.12
 * Time: 2:13
 */

@Repository("fileDAO")
public class ImageDAO {

    private static final Log LOGGER = LogFactory.getLog(ImageDAO.class.getClass());
    private String tableName = "dataserver_images";
    private NamedParameterJdbcTemplate jdbcTemplate;


    public ImageDAO() {
    }

    private boolean executeUpdate(String update) {
        try {
            jdbcTemplate.update(update, new MapSqlParameterSource());
        } catch (DataAccessException e) {
            LOGGER.error("", e);
            return false;
        }
        return true;
    }

    public boolean add(long imageId) {
        return executeUpdate("INSERT INTO " + tableName + " (image_id, angle, last_access_time, deleted_big_size) VALUES(" + imageId + ", 0, NOW(), 0)");
    }

    public boolean setAngle(long imageId, int angle) {
        return executeUpdate("UPDATE " + tableName + " SET deleted_big_size=0, angle=" + angle + " WHERE image_id=" + imageId);
    }

    public boolean delete(long imageId) {
        return executeUpdate("DELETE FROM " + tableName + " WHERE image_id=" + imageId);
    }

    public boolean setDeletedBigSize(long imageId, boolean deleted) {
        int v = deleted ? 1 : 0;
        return executeUpdate("UPDATE " + tableName + " SET deleted_big_size=" + v + " WHERE image_id=" + imageId);
    }

    public int getAngle(long imageId) {
        int angle = 0;
//        DBConnection connection = pool.getConnection();
//        try {
//            DBResult r = connection.executeQuery("SELECT angle FROM " + tableName + " WHERE image_id=" + imageId);
//
//            qGetAngle.trackTime(r.getExecutionTimeMillis());
//
//            if (r.next()) {
//                angle = r.getInt("angle");
//            }
//            r.close();
//        } catch (Exception ex) {
//            LOGGER.log(Level.WARNING, "", ex);
//        } finally {
//            connection.close();
//        }
        return angle;
    }

    public List<Long> getOld(int preserveCount) {
        ArrayList<Long> list = new ArrayList<Long>();
//        DBConnection connection = pool.getConnection();
//        try {
//            int count = 0;
//            DBResult r = connection.executeQuery("SELECT COUNT(*) as C FROM " + tableName + " WHERE deleted_big_size=0");
//            if (r.next()) {
//                count = r.getInt("C");
//            }
//            r.close();
//
//            int limit = Math.max(0, count - preserveCount);
//            list.ensureCapacity(limit);
//            r = connection.executeQuery("SELECT image_id FROM " + tableName + " WHERE deleted_big_size=0 ORDER BY last_access_time LIMIT " + limit);
//            while (r.next()) {
//                long id = r.getLong("image_id");
//                if (id > InitDefaultImages.MAX_ID) {
//                    list.add(id);
//                }
//            }
//            r.close();
//        } catch (Exception ex) {
//            LOGGER.log(Level.WARNING, "", ex);
//        } finally {
//            connection.close();
//        }
        return list;
    }

    public void updateAccessTime(Long[] list) {
        Arrays.sort(list); //to help db, less disk seeks will be made

        String update = "UPDATE " + tableName + " SET last_access_time=NOW() WHERE image_id IN ";
        StringBuilder in = new StringBuilder();
        int c = 0;
        for (int i = 0; i < list.length; ++i) {
            if (c++ > 0) {
                in.append(',');
            } else {
                in.append('(');
            }
            in.append(list[i]);

            if (c >= 100 || i == list.length - 1) {
                in.append(')');

                executeUpdate(update + in);

                in.setLength(0);
                c = 0;
            }
        }
    }

    @Autowired
    public void init(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

}
