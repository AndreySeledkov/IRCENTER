package org.ircenter.server.bean.group;

public class CommunityGroup {

    private long id;
    private long groupId;
    private int themeId;
    private int subsectionId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    public int getSubsectionId() {
        return subsectionId;
    }

    public void setSubsectionId(int subsectionId) {
        this.subsectionId = subsectionId;
    }
}
