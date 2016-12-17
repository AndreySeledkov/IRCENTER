package org.ircenter.server.bean.forum;

import java.io.Serializable;
import java.util.List;

public class Part implements Serializable {
    private long id;
    private long mainPart;
    private int orderIndex;
    private String name;
    private List<Long> themes;
    private long messagesCount;
    private long themeCount;

    public Part() {
    }

    public Part(long mainpart) {
        this.setMainPart(mainpart);
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMainPart() {
        return mainPart;
    }

    public void setMainPart(long mainPart) {
        this.mainPart = mainPart;
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

    public List<Long> getThemes() {
        return themes;
    }

    public void setThemes(List<Long> themes) {
        this.themes = themes;
    }

    public long getMessagesCount() {
        return messagesCount;
    }

    public void setMessagesCount(long messagesCount) {
        this.messagesCount = messagesCount;
    }

    public long getThemeCount() {
        return themeCount;
    }

    public void setThemeCount(long themeCount) {
        this.themeCount = themeCount;
    }
}
