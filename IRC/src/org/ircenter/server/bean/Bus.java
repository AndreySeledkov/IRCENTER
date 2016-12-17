package org.ircenter.server.bean;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: 1
 * Date: 18.05.12
 * Time: 10:17
 * To change this template use File | Settings | File Templates.
 */
public class Bus {
    private long id;
    private long cityId;

    private int defaultImage;
    private Date departure;
    private String description;

    public int getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(int defaultImage) {
        this.defaultImage = defaultImage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
