package org.ircenter.server.bean.news;

/**
 * User: Seledkov Kostyantyn
 * Date: 24.03.12
 * Time: 13:18
 */
public class NewsChapter {

    private int chapterId;
    private int orderIndex;
    private String chapter;

    public NewsChapter() {
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

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

}
