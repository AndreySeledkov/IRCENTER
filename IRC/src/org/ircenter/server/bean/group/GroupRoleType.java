package org.ircenter.server.bean.group;


public enum GroupRoleType {
    CREATOR,ADMIN, MEMBER;

    public static GroupRoleType valueOf(int ordinal) {
        return values()[ordinal];
    }
}
