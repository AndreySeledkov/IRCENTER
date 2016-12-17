package org.ircenter.server.bean.evidence;

import java.util.Date;

/**
 * User: Seledkov Andriy
 * Date: 10.05.12
 * Time: 17:19
 */
public class VideoEvidence {
    private long id;
    private String title;
    private String link;
    private String youtubeId;
    private int countViews;
    private Date createDate;

    public VideoEvidence() {
    }

    public VideoEvidence(VideoEvidence evidence) {
        this.id = evidence.id;
        this.title = evidence.title;
        this.link = evidence.link;
        this.youtubeId = evidence.youtubeId;
        this.countViews = evidence.countViews;
        this.createDate = evidence.createDate;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
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
