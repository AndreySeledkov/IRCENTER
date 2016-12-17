package org.ircenter.server.bean.calendarEvent;

import java.util.Date;

/**
 * User: Seledkov Kostyantyn
 * Date: 09.05.12
 * Time: 22:02
 */
public class CalendarEventBean {
    private int eventId;
    private Date startEvent;
    private Date endEvent;
    private String city;
    private String place;
    private String eventName;
    private String month;
    private int defImageId;

    public CalendarEventBean() {
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public Date getStartEvent() {
        return startEvent;
    }

    public void setStartEvent(Date startEvent) {
        this.startEvent = startEvent;
    }

    public Date getEndEvent() {
        return endEvent;
    }

    public void setEndEvent(Date endEvent) {
        this.endEvent = endEvent;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getDefImageId() {
        return defImageId;
    }

    public void setDefImageId(int defImageId) {
        this.defImageId = defImageId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
