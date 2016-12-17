package org.ircenter.server.bean;

/**
 * Created by IntelliJ IDEA.
 * User: 1
 * Date: 16.05.12
 * Time: 0:03
 * To change this template use File | Settings | File Templates.
 */
public class Deliveries {
    private long id;
    private String mail;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
