package org.ircenter.server.bean.evidence;

import java.util.Date;

/**
 * User: Seledkov Andriy
 * Date: 14.05.12
 * Time: 0:38
 */
public class SecretSpiritualWorld {
    private long id;
    private String title;
    private String youtubeId;
    private String link;
    private String text;
    private int countViews;
    private Date createdDate;

    public SecretSpiritualWorld(SecretSpiritualWorld copy) {
        this.id = copy.id;
        this.title = copy.title;
        this.youtubeId = copy.youtubeId;
        this.link = copy.link;
        this.text = copy.text;
        this.countViews = copy.countViews;
        this.createdDate = copy.createdDate;
    }

    public SecretSpiritualWorld() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCountViews() {
        return countViews;
    }

    public void setCountViews(int countViews) {
        this.countViews = countViews;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
