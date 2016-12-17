package org.ircenter.server.bean.forum;


import java.util.Date;

public class UserDataForum {
    private Date messagePrint;
    private long id;
    private String name;
    private String country;
    private String city;
    private Date birthDay;
    private byte gender;
    private String message;

    public Date getMessagePrint() {
        return messagePrint;
    }

    public void setMessagePrint(Date messagePrint) {
        this.messagePrint = messagePrint;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public byte getGender() {
        return gender;
    }

    public void setGender(byte gender) {
        this.gender = gender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
