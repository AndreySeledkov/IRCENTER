package org.ircenter.server.bean.calendarEvent;

import java.util.Date;

/**
 * User: Seledkov Kostyantyn
 * Date: 09.05.12
 * Time: 20:52
 */
public class CalendarEvent {

    private int eventId;
    private Date startEvent;
    private Date endEvent;
    private int countryId;
    private int regionId;
    private int cityId;
    private String place;
    private String eventName;
    private int defImageId;

    public CalendarEvent() {
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

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
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
}
