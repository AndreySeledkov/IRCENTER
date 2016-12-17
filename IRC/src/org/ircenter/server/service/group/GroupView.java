package org.ircenter.server.service.group;

import org.ircenter.server.bean.group.*;

import java.util.Date;
import java.util.List;

public class GroupView {

    private long id;
    private long groupId;
    private long userId;
    private String title;
    private String description;
    private List<GroupCommunityTheme> themeList;
    private List<GroupCommunitySubsection> subsectionList;
    private long themeId;
    private long subsectionId;
    private int countryId;
    private int regionId;
    private int cityId;
    private GroupType groupType;
    private GroupRoleType roleType;
    private AssociationType associationType;
    
    private Date startTime;
    private Date endTime;

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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<GroupCommunityTheme> getThemeList() {
        return themeList;
    }

    public void setThemeList(List<GroupCommunityTheme> themeList) {
        this.themeList = themeList;
    }

    public List<GroupCommunitySubsection> getSubsectionList() {
        return subsectionList;
    }

    public void setSubsectionList(List<GroupCommunitySubsection> subsectionList) {
        this.subsectionList = subsectionList;
    }

    public long getThemeId() {
        return themeId;
    }

    public void setThemeId(long themeId) {
        this.themeId = themeId;
    }

    public long getSubsectionId() {
        return subsectionId;
    }

    public void setSubsectionId(long subsectionId) {
        this.subsectionId = subsectionId;
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

    public GroupType getGroupType() {
        return groupType;
    }

    public void setGroupType(GroupType groupType) {
        this.groupType = groupType;
    }

    public GroupRoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(GroupRoleType roleType) {
        this.roleType = roleType;
    }

    public AssociationType getAssociationType() {
        return associationType;
    }

    public void setAssociationType(AssociationType associationType) {
        this.associationType = associationType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
