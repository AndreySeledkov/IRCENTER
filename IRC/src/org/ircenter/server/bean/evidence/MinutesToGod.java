package org.ircenter.server.bean.evidence;

import java.util.Date;

/**
 * User: Seledkov Andriy
 * Date: 13.05.12
 * Time: 23:11
 */
public class MinutesToGod {
    private long id;
    private String title;
    private String youtubeId;
    private String text;
    private String link;
    private int countViews;
    private Date createdDate;

    public MinutesToGod(MinutesToGod item) {
        this.id = item.id;
        this.title = item.title;
        this.youtubeId = item.youtubeId;
        this.text = item.text;
        this.link = item.link;
        this.countViews = item.countViews;
        this.createdDate = item.createdDate;
    }

    public MinutesToGod() {
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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
