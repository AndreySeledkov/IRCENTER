package org.ircenter.server.bean.friends;


import java.util.Date;

public class FriendRequest {
    private long id;
    private Date date;
    private long requestClientFrom;
    private long requestClientTo;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getRequestClientFrom() {
        return requestClientFrom;
    }

    public void setRequestClientFrom(long requestClientFrom) {
        this.requestClientFrom = requestClientFrom;
    }

    public long getRequestClientTo() {
        return requestClientTo;
    }

    public void setRequestClientTo(long requestClientTo) {
        this.requestClientTo = requestClientTo;
    }

}
