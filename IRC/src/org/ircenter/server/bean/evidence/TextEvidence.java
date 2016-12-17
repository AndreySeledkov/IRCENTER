package org.ircenter.server.bean.evidence;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: 1
 * Date: 10.05.12
 * Time: 17:19
 * To change this template use File | Settings | File Templates.
 */
public class TextEvidence {
    private long id;
    private int defaultImage;
    private String title;
    private String text;
    private int countViews;
    private Date createDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(int defaultImage) {
        this.defaultImage = defaultImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
