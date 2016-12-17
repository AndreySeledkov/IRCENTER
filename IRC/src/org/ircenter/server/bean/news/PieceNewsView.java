package org.ircenter.server.bean.news;

import java.util.Date;

/**
 * User: Seledkov Kostyantyn
 * Date: 24.03.12
 * Time: 15:52
 */
public class PieceNewsView {
    private long id;
    private int partId;
    private int orderIndex;
    private Date startDate;
    private Date stopDate;
    private long viewCount;
    private long authorId;
    private String title;
    private String info;
    private boolean deleted;
    private boolean banned;
    private long commentsCount;

    private String authorName;


    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public PieceNewsView(PieceNews pieceNews) {
        this.id = pieceNews.getId();
        this.partId = pieceNews.getPartId();
        this.orderIndex = pieceNews.getOrderIndex();
        this.startDate = pieceNews.getStartDate();
        this.stopDate = pieceNews.getStopDate();
        this.viewCount = pieceNews.getViewCount();
        this.authorId = pieceNews.getAuthorId();
        this.title = pieceNews.getTitle();
        this.info = pieceNews.getText();
        this.deleted = pieceNews.isDeleted();
        this.banned = pieceNews.isBanned();
        this.commentsCount = pieceNews.getCommentsCount();

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }

    public long getViewCount() {
        return viewCount;
    }

    public void setViewCount(long viewCount) {
        this.viewCount = viewCount;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public long getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(long commentsCount) {
        this.commentsCount = commentsCount;
    }
}
