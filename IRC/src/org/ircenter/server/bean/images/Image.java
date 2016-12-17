package org.ircenter.server.bean.images;

import java.util.Date;

/**
 * User: Seledkov Kostyantyn
 * Date: 15.05.12
 * Time: 19:33
 */
public class Image {
    private long imageId;
    private String title;
    private long userId;
    private Date date;

    public Image(long imageId, String title, long userId, Date date) {
        this.imageId = imageId;
        this.title = title;
        this.userId = userId;
        this.date = date;
    }

    public Image() {
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
