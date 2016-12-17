package org.ircenter.server.dao.slider;

import com.googlecode.ehcache.annotations.Cacheable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.bean.news.PieceNews;
import org.ircenter.server.bean.slider.Slider;
import org.ircenter.server.bean.slider.SliderType;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: 1
 * Date: 16.04.12
 * Time: 17:59
 * To change this template use File | Settings | File Templates.
 */
@Repository("sliderDAO")
public class SliderDAO {

    private static final Log LOGGER = LogFactory.getLog(SliderDAO.class.getClass());

    private NamedParameterJdbcTemplate jdbcTemplate;

    public SliderDAO() {
    }

    private final static RowMapper<Slider> sliderRowMapper = new RowMapper<Slider>() {
        @Override
        public Slider mapRow(ResultSet resultSet, int i) throws SQLException {
            Slider slider = new Slider();
            slider.setId(resultSet.getLong("id"));
            slider.setSliderType(SliderType.valueOf(resultSet.getInt("slider_type")));
            slider.setImgId(resultSet.getInt("img_id"));
            return slider;
        }
    };

    //    @Cacheable(cacheName = "slider")
    public List<Slider> getSlider(SliderType sliderType) {
        List<Slider> list = new ArrayList<Slider>();
        String query = "SELECT * FROM slider AS s WHERE slider_type=:slider_type";
        try {
            list = jdbcTemplate.query(query, new MapSqlParameterSource().addValue("slider_type", sliderType.ordinal()), sliderRowMapper);
        } catch (DataAccessException e) {
            LOGGER.error("", e);
        }
        return list;
    }

    public boolean addSlider(Slider slider) {
        String query = "INSERT INTO slider (slider_type, img_id) VALUES (:slider_type, :img_id)";
        SqlParameterSource fileParameters = new MapSqlParameterSource().addValue("slider_type", slider.getSliderType().ordinal()).addValue("img_id", slider.getImgId());

        return jdbcTemplate.update(query, fileParameters) > 0;
    }

    public boolean removeSlider(long id) {
        String query = "DELETE FROM slider WHERE id = :id";

        SqlParameterSource fileParameters = new MapSqlParameterSource().addValue("id", id);

        return jdbcTemplate.update(query, fileParameters) > 0;
    }


    @Autowired
    public void init(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
}
