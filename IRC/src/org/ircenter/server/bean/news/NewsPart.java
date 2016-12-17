package org.ircenter.server.bean.news;

/**
 * User: Seledkov Kostyantyn
 * Date: 24.03.12
 * Time: 13:21
 */
public class NewsPart {
    private int id;
    private int chapterId;
    private int orderIndex;
    private String name;
    private long newsCount;
    private long commentsCount;

    public NewsPart() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNewsCount() {
        return newsCount;
    }

    public void setNewsCount(long newsCount) {
        this.newsCount = newsCount;
    }

    public long getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(long commentsCount) {
        this.commentsCount = commentsCount;
    }

}
