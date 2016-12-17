package org.ircenter.server.bean.forum;

import org.ircenter.server.util.TextUtil;

import java.io.Serializable;
import java.util.Date;

public class Message implements BanFilterable, Serializable {

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

    public Message() {
    }

    public static Message createNew(long theme, long author, String text, boolean banned, boolean deleted) {
        Message message = new Message();
        message.setThemeId(theme);
        message.setAuthorId(author);
        message.setText(text);
        message.setCreateDate(new Date());
        message.setFirstMessage(false);
        message.setBanned(deleted);
        message.setDeleted(deleted);
        return message;
    }

    public static Message createFirst(Theme theme, long author, String text, boolean banned, boolean deleted) {
        Message message = createNew(theme.getId(), author, text, banned, deleted);
        message.setFirstMessage(true);
        return message;
    }

    static Message createQuotted(Message quotingOn, long author, String text, boolean banned, boolean deleted) {
        Message message = createNew(quotingOn.getThemeId(), author, text, banned, deleted);
        message.setQuotingOn(quotingOn.getId());
        return message;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getThemeId() {
        return themeId;
    }

    public void setThemeId(long theme) {
        this.themeId = theme;
    }

    @Override
    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long author) {
        this.authorId = author;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createdate) {
        this.createDate = createdate;
    }

    public String getTextQuotted() {
        return TextUtil.replaceHTMLtext(text);
    }

    public String getTextQuotted(int maxLength, String append) {
        if (text.length() <= maxLength) {
            return TextUtil.replaceHTMLtext(text);
        }
        if (append != null && !append.isEmpty()) {
            return TextUtil.replaceHTMLtext(text.substring(0, maxLength)) + append;
        } else {
            return TextUtil.replaceHTMLtext(text.substring(0, maxLength));
        }
    }

    public String getTextQuotted(int maxLength) {
        if (text.length() <= maxLength) {
            return TextUtil.replaceHTMLtext(text);
        }
        return TextUtil.replaceHTMLtext(text.substring(0, maxLength));
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getQuotingOn() {
        return quotingOn;
    }

    public void setQuotingOn(long quotingOn) {
        this.quotingOn = quotingOn;
    }

    @Override
    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isFirstMessage() {
        return firstMessage;
    }

    public void setFirstMessage(boolean firstmessage) {
        this.firstMessage = firstmessage;
    }

    @Override
    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }
    
    public boolean isExcluded(){
        return deleted || banned;
    }

    public int getComplaints() {
        return complaints;
    }

    public void setComplaints(int complaints) {
        this.complaints = complaints;
    }
}
