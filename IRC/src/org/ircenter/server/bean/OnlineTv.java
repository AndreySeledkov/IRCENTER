package org.ircenter.server.bean;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: 1
 * Date: 26.05.12
 * Time: 14:29
 * To change this template use File | Settings | File Templates.
 */
public class OnlineTv {
    private long id;
    private Date date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
