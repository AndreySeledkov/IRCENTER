package org.ircenter.server.bean.evidence;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: 1
 * Date: 11.05.12
 * Time: 8:28
 * To change this template use File | Settings | File Templates.
 */
public class ProgrammTv {
    private long id;
    private String title;
    private String days;
    private Date date;
    private ProgrammTvType programmTvType;

    private String day;
    private String time;


    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ProgrammTvType getProgrammTvType() {
        return programmTvType;
    }

    public void setProgrammTvType(ProgrammTvType programmTvType) {
        this.programmTvType = programmTvType;
    }
}
