package org.ircenter.server.bean.forum;

import java.util.Date;

public class Complaint {
    private long id;
    private Date dateCreate;
    private long messageId;
    private long authorId;

    protected Complaint() {
    }

    public Complaint(long messageId, long authorId) {
        this.dateCreate = new Date();
        this.messageId = messageId;
        this.authorId = authorId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date datecreate) {
        this.dateCreate = datecreate;
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long message) {
        this.messageId = message;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Complaint complaint = (Complaint) o;

        return messageId == complaint.messageId && authorId == complaint.authorId &&
                dateCreate.equals(complaint.dateCreate);

    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.dateCreate != null ? this.dateCreate.hashCode() : 0);
        hash = 89 * hash + (int) (this.messageId ^ (this.messageId >>> 32));
        hash = 89 * hash + (int) (this.authorId ^ (this.authorId >>> 32));
        return hash;
    }
}
