package org.ircenter.server.bean.group;


public class UsersGroup {
    private long id;
    private long groupId;
    private long userId;
    private GroupRoleType roleType;

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

    public GroupRoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(GroupRoleType roleType) {
        this.roleType = roleType;
    }
}
