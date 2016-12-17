package org.ircenter.server.bean.molodejhka;

import java.util.Date;

/**
 * User: Seledkov Kostyantyn
 * Date: 24.05.12
 * Time: 3:09
 */
public class MolodejhkaVideo {
    private long id;
    private long userId;
    private String youtubeId;
    private String link;
    private Date date;
    private String description;
    private String text;

    public MolodejhkaVideo() {
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

    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
