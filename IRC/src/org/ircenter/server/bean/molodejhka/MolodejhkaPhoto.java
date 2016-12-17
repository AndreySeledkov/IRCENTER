package org.ircenter.server.bean.molodejhka;

import java.util.Date;

/**
 * User: Seledkov Kostyantyn
 * Date: 24.05.12
 * Time: 3:08
 */
public class MolodejhkaPhoto {
    private long id;
    private long userId;
    private long imageId;
    private Date date;
    private String description;

    public MolodejhkaPhoto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
