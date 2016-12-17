package org.ircenter.server.bean.feedback;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: 1
 * Date: 06.05.12
 * Time: 22:16
 * To change this template use File | Settings | File Templates.
 */
public class FeedBack {
    private long id;
    private String firstName;
    private String lastName;
    private String mail;
    private FeedBackTheme theme;
    private String info;
    private Date date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public FeedBackTheme getTheme() {
        return theme;
    }

    public void setTheme(FeedBackTheme theme) {
        this.theme = theme;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
