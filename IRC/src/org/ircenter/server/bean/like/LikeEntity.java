package org.ircenter.server.bean.like;

import java.util.Date;

public class LikeEntity {

    private long id;
    private long userId;
    private long clientId;
    private ServiceType type;
    private long contentId;
    private byte like;
    private Date date;

    public LikeEntity() {
    }

    public LikeEntity(LikeEntity entity) {
        this.id = entity.id;
        this.userId = entity.userId;
        this.clientId = entity.clientId;
        this.type = entity.type;
        this.contentId = entity.contentId;
        this.like = entity.like;
        this.date = entity.date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public ServiceType getType() {
        return type;
    }

    public void setType(ServiceType type) {
        this.type = type;
    }

    public long getContentId() {
        return contentId;
    }

    public void setContentId(long contentId) {
        this.contentId = contentId;
    }

    public byte getLike() {
        return like;
    }

    public void setLike(byte like) {
        this.like = like;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean sameRecord(LikeEntity e) {
        return contentId == e.contentId && type == e.type;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LikeEntity other = (LikeEntity) obj;

        if (this.userId != other.userId && this.clientId != other.clientId) {
            return false;
        }

        if (this.type != other.type) {
            return false;
        }
        if (this.contentId != other.contentId) {
            return false;
        }
        if (this.like != other.like) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (int) (this.clientId ^ (this.clientId >>> 32));
        hash = 67 * hash + (this.type != null ? this.type.hashCode() : 0);
        hash = 67 * hash + (int) (this.contentId ^ (this.contentId >>> 32));
        return hash;
    }
}
