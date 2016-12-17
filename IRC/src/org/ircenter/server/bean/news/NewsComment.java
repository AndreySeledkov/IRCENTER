package org.ircenter.server.bean.news;

import java.util.Date;

/**
 * User: Seledkov Kostyantyn
 * Date: 28.03.12
 * Time: 0:29
 */
public class NewsComment {

    private long newsId;
    private long userId;
    private String name;
    private Date date;
    private String text;


    public long getNewsId() {
        return newsId;
    }

    public void setNewsId(long newsId) {
        this.newsId = newsId;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "NewsComment[" + "userId = " + userId + ", newsId = " + newsId + ", date = " + date + ", text = " + text + ']';
    }
}
