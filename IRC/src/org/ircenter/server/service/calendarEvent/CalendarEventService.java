package org.ircenter.server.service.calendarEvent;

import org.ircenter.server.bean.calendarEvent.CalendarEvent;
import org.ircenter.server.dao.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * User: Seledkov Kostyantyn
 * Date: 09.05.12
 * Time: 21:29
 */
@Service
public class CalendarEventService {
    private Database database;

    public Collection<CalendarEvent> getEvents() {
        return database.getCalendarEventDAO().getEvents().values();
    }
    
    public CalendarEvent getEvent(int eventId) {
       return database.getCalendarEventDAO().getEvents().get(eventId);
    }

    public void addEvent(CalendarEvent event) {
        database.getCalendarEventDAO().addEvent(event);
    }

    public void editEvent(CalendarEvent event) {
        database.getCalendarEventDAO().editEvent(event);
    }

    public void removeEvent(int calendarId) {
        database.getCalendarEventDAO().removeEvent(calendarId);
    }

    @Autowired
    public void setDatabase(Database database) {
        this.database = database;
    }
}
