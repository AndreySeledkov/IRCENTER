package org.ircenter.server.dao.calendarEvent;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.googlecode.ehcache.annotations.When;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.bean.calendarEvent.CalendarEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Seledkov Kostyantyn
 * Date: 09.05.12
 * Time: 20:57
 */
@Repository("CalendarEventDAO")
public class CalendarEventDAO {
    private static final Log LOGGER = LogFactory.getLog(CalendarEventDAO.class.getClass());

    private NamedParameterJdbcTemplate jdbcTemplate;

    public CalendarEventDAO() {
    }

    private static final RowMapper<CalendarEvent> calendarRowMapper = new RowMapper<CalendarEvent>() {
        @Override
        public CalendarEvent mapRow(ResultSet resultSet, int i) throws SQLException {
            CalendarEvent event = new CalendarEvent();
            event.setEventId(resultSet.getInt("id"));
            event.setStartEvent(resultSet.getTimestamp("start_event"));
            event.setEndEvent(resultSet.getTimestamp("end_event"));
            event.setCountryId(resultSet.getInt("country_id"));
            event.setRegionId(resultSet.getInt("region_id"));
            event.setCityId(resultSet.getInt("city_id"));
            event.setPlace(resultSet.getString("place"));
            event.setEventName(resultSet.getString("event_name"));
            event.setDefImageId(resultSet.getInt("image_id"));
            return event;
        }
    };

    @Cacheable(cacheName = "calendarEvent")
    public Map<Integer, CalendarEvent> getEvents() {
        Map<Integer, CalendarEvent> map = new LinkedHashMap<Integer, CalendarEvent>();
        //String query = "SELECT * FROM calendar_events AS t1 WHERE t1.start_event BETWEEN NOW() AND DATE_ADD(NOW(),INTERVAL 1 MONTH)";
        String query = "SELECT * FROM calendar_events AS t1 WHERE t1.start_event >= NOW() ORDER BY t1.start_event ASC";
        try {
            List<CalendarEvent> events = jdbcTemplate.query(query, new MapSqlParameterSource(), calendarRowMapper);
            for (CalendarEvent event : events) {
                map.put(event.getEventId(), event);
            }
        } catch (DataAccessException e) {
            LOGGER.error("", e);
        }
        return map;
    }

    @TriggersRemove(cacheName = "calendarEvent", when = When.AFTER_METHOD_INVOCATION, removeAll = true)
    public void addEvent(CalendarEvent event) {
        String query = "INSERT INTO calendar_events VALUES(DEFAULT,:start_event,:end_event,:country_id," +
                ":region_id,:city_id,:place,:event_name,:image_id)";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("start_event", event.getStartEvent());
        source.addValue("end_event", event.getEndEvent());
        source.addValue("country_id", event.getCountryId());
        source.addValue("region_id", event.getRegionId());
        source.addValue("city_id", event.getCityId());
        source.addValue("place", event.getPlace());
        source.addValue("event_name", event.getEventName());
        source.addValue("image_id", event.getDefImageId());
        try {
            jdbcTemplate.update(query, source);
        } catch (DataAccessException e) {
            LOGGER.error("", e);
        }
    }

    @TriggersRemove(cacheName = "calendarEvent", when = When.AFTER_METHOD_INVOCATION, removeAll = true)
    public void editEvent(CalendarEvent event) {
        String query = "UPDATE calendar_events SET start_event=:start_event,end_event=:end_event,country_id = :country_id," +
                "region_id=:region_id,city_id=:city_id,place=:place,event_name=:event_name,image_id=:image_id WHERE id=:id";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("start_event", event.getStartEvent());
        source.addValue("end_event", event.getEndEvent());
        source.addValue("country_id", event.getCountryId());
        source.addValue("region_id", event.getRegionId());
        source.addValue("city_id", event.getCityId());
        source.addValue("place", event.getPlace());
        source.addValue("event_name", event.getEventName());
        source.addValue("image_id", event.getDefImageId());
        source.addValue("id", event.getEventId());
        try {
            jdbcTemplate.update(query, source);
        } catch (DataAccessException e) {
            LOGGER.error("", e);
        }

    }

    @TriggersRemove(cacheName = "calendarEvent", when = When.AFTER_METHOD_INVOCATION, removeAll = true)
    public void removeEvent(int calendarId) {
        String query = "DELETE FROM calendar_events WHERE id = :id";
        try {
            jdbcTemplate.update(query, new MapSqlParameterSource("id", calendarId));
        } catch (DataAccessException e) {
            LOGGER.error("", e);
        }
    }

    @Autowired
    public void init(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
}
