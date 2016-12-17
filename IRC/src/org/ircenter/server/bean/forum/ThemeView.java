package org.ircenter.server.bean.forum;

import org.ircenter.server.service.user.UserProfile;
import org.ircenter.server.util.TextUtil;

import java.io.Serializable;
import java.util.Date;

public class ThemeView implements BanFilterable, Serializable {

    private long id;
    private long partId;
    private int orderIndex;
    private long authorId;
    private String name;
    private String nameQuoted;
    private Date lastEdit;
    private int numberViews;
    private boolean deleted;
    private boolean banned;
    private int messagesCount;
    private UserProfile userProfile;

    public ThemeView() {
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPartId() {
        return partId;
    }

    public void setPartId(long part) {
        this.partId = part;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    public String getNameQuotted() {
        return nameQuoted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.nameQuoted = TextUtil.replaceHTMLtext(name);
    }

    public Date getLastEdit() {
        return lastEdit;
    }

    public void setLastEdit(Date lastEdit) {
        this.lastEdit = lastEdit;
    }

    public void setNumberViews(int numberViews) {
        this.numberViews = numberViews;
    }

    @Override
    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long author) {
        this.authorId = author;
    }

    @Override
    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public boolean isExcluded() {
        return deleted || banned;
    }

    public int getMessagesCount() {
        return messagesCount;
    }

    public void setMessagesCount(int messagesCount) {
        this.messagesCount = messagesCount;
    }

    public int getNumberViews() {
        return numberViews;
    }
}
