package org.ircenter.server.bean.forum;

import org.ircenter.server.service.user.UserProfile;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: 1
 * Date: 06.05.12
 * Time: 23:31
 * To change this template use File | Settings | File Templates.
 */
public class MessageView {

    private long id;
    private long themeId;
    private long authorId;
    private Date createDate;
    private long quotingOn;
    private String text;
    private boolean firstMessage;
    private boolean checked;
    private boolean deleted;
    private boolean banned;
    private int complaints;
    private UserProfile userProfile;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getThemeId() {
        return themeId;
    }

    public void setThemeId(long themeId) {
        this.themeId = themeId;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public long getQuotingOn() {
        return quotingOn;
    }

    public void setQuotingOn(long quotingOn) {
        this.quotingOn = quotingOn;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isFirstMessage() {
        return firstMessage;
    }

    public void setFirstMessage(boolean firstMessage) {
        this.firstMessage = firstMessage;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
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

    public int getComplaints() {
        return complaints;
    }

    public void setComplaints(int complaints) {
        this.complaints = complaints;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
