package org.ircenter.server.service.user;

import org.ircenter.server.bean.user.options.PrivacyOptions;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

public class UserProfile extends User {

    private long userId;
    private String loginName;
    private Date createDate;
    private String givenName;
    private String surname;
    private Gender gender;
    private Date dayOfBirth;
    private int countryId;
    private int regionId;
    private int cityId;
    private Date banned;

    private boolean isRequest;
    private boolean isFriend;
    private PrivacyOptions privacyOptions;

    //private String ircUserName;

    public UserProfile(long userId, String username, String password, boolean enabled, boolean accountNonExpired,
                       boolean credentialsNonExpired, boolean accountNonLocked,
                       Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userId;
    }

//    public String getIrcUserName() {
//        return ircUserName;
//    }
//
//    public void setIrcUserName(String ircUserName) {
//        this.ircUserName = ircUserName;
//    }


    public boolean isRequest() {
        return isRequest;
    }

    public void setRequest(boolean request) {
        isRequest = request;
    }

    public boolean isFriend() {
        return isFriend;
    }

    public void setFriend(boolean friend) {
        isFriend = friend;
    }

    public Date getBanned() {
        return banned;
    }

    public void setBanned(Date banned) {
        this.banned = banned;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public boolean isBanned() {
        return banned.getTime() > (new GregorianCalendar()).getTimeInMillis();
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(Date dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public PrivacyOptions getPrivacyOptions() {
        return null;
        //return Database.getInstance().getUserOptionService().getUserOptions(userId).getPrivacyOptions();
    }
}
