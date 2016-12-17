package org.ircenter.server.bean.edit;


public class UserContacts {
    private long id;
    private long userId;
    private String cellPhone;
    private String homePhone;
    private String skype;
    private String ownSite;


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

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getOwnSite() {
        return ownSite;
    }

    public void setOwnSite(String ownSite) {
        this.ownSite = ownSite;
    }
}
