package org.ircenter.server.bean.forum;


import java.util.Date;

public class Settings {
    private long id;
    private long userId;
    private Integer version;
    private boolean showAvatar = true;
    private boolean showLocation = true;
    private Date canWriteFrom;
    private int countBans;
    private int showRules;
    private long lastNews;

    public Settings() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Settings(long userId) {
        this.userId = userId;
        this.showRules = 1;
        //this.canWriteFrom = new Date(System.currentTimeMillis() + ForumConstants.NEW_USER_WRITE_DELAY_MILLIS);
    }

    private int isCanWrite = -1;

    public boolean isCanWrite() {
        if (isCanWrite == -1) {
            if (System.currentTimeMillis() > canWriteFrom.getTime()) {
                isCanWrite = 1;
            } else {
                isCanWrite = 0;
            }
        }
        return isCanWrite == 1;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean isShowAvatar() {
        return showAvatar;
    }

    public void setShowAvatar(boolean showAvatar) {
        this.showAvatar = showAvatar;
    }

    public boolean isShowLocation() {
        return showLocation;
    }

    public void setShowLocation(boolean showLocation) {
        this.showLocation = showLocation;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCanWriteFrom() {
        return canWriteFrom;
    }

    public void setCanWriteFrom(Date canWritefrom) {
        this.canWriteFrom = canWritefrom;
    }

    public int getCountBans() {
        return countBans;
    }

    public void setCountBans(int countBans) {
        this.countBans = countBans;
        isCanWrite = -1;
    }

    public int getShowRules() {
        return showRules;
    }

    public void setShowRules(int showRules) {
        this.showRules = showRules;
    }

    public long getLastNews() {
        return lastNews;
    }

    public void setLastNews(long lastNews) {
        this.lastNews = lastNews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Settings settings = (Settings) o;

        return countBans == settings.countBans && isCanWrite == settings.isCanWrite &&
                showAvatar == settings.showAvatar && showLocation == settings.showLocation &&
                showRules == settings.showRules &&
                !(canWriteFrom != null ? !canWriteFrom.equals(settings.canWriteFrom) : settings.canWriteFrom != null) &&
                !(lastNews == settings.lastNews) &&
                userId == settings.userId &&
                !(version != null ? !version.equals(settings.version) : settings.version != null);

    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (int) (this.userId ^ (this.userId >>> 32));
        hash = 89 * hash + (this.version != null ? this.version.hashCode() : 0);
        hash = 89 * hash + (this.showAvatar ? 1 : 0);
        hash = 89 * hash + (this.showLocation ? 1 : 0);
        hash = 89 * hash + (this.canWriteFrom != null ? this.canWriteFrom.hashCode() : 0);
        hash = 89 * hash + this.countBans;
        hash = 89 * hash + this.showRules;
        hash = 89 * hash + (int) (this.lastNews ^ (this.lastNews >>> 32));
        hash = 89 * hash + this.isCanWrite;
        return hash;
    }
}
