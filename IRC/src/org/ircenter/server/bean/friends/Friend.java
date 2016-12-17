package org.ircenter.server.bean.friends;


import java.util.Date;

public class Friend {

    private long id;
    private long selfId;
    private long friendId;
    private Date friendDate;


    public long getSelfId() {
        return selfId;
    }

    public void setSelfId(long selfId) {
        this.selfId = selfId;
    }

    public long getFriendId() {
        return friendId;
    }

    public void setFriendId(long friendId) {
        this.friendId = friendId;
    }

    public Date getFriendDate() {
        return friendDate;
    }

    public void setFriendDate(Date friendDate) {
        this.friendDate = friendDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
