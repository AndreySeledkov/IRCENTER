package org.ircenter.server.bean.edit;

import org.ircenter.server.bean.location.Country;
import org.ircenter.server.service.user.Gender;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

/**
 * User: Seledkov Kostyantyn
 * Date: 14.02.12
 * Time: 10:03
 */
public class UserMain {
    private static final Calendar calendar = Calendar.getInstance();
    private String name;
    private String surname;
    private int genderId;
    private int yearDb;
    private int monthDb;
    private int dayDb;
    private int countryId;
    private int regionId;
    private int cityId;

    public UserMain() {

    }
    public UserMain(String name, String surname, int genderId, Date dayOfBirth, int countryId, int regionId, int cityId) {
        this.name = name;
        this.surname = surname;
        this.genderId = genderId;
        if (dayOfBirth != null) {
            calendar.setTime(dayOfBirth);
            this.yearDb = calendar.get(Calendar.YEAR);
            this.monthDb = calendar.get(Calendar.MONTH) + 1;
            this.dayDb = calendar.get(Calendar.DAY_OF_MONTH);
        }
        this.countryId = countryId;
        this.regionId = regionId;
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getGenderId() {
        return genderId;
    }

    public void setGenderId(int gender) {
        this.genderId = gender;
    }

    public int getYearDb() {
        return yearDb;
    }

    public void setYearDb(int yearDb) {
        this.yearDb = yearDb;
    }

    public int getMonthDb() {
        return monthDb;
    }

    public void setMonthDb(int monthDb) {
        this.monthDb = monthDb;
    }

    public int getDayDb() {
        return dayDb;
    }

    public void setDayDb(int dayDb) {
        this.dayDb = dayDb;
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

}
