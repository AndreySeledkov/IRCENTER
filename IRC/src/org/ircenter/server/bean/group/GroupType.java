package org.ircenter.server.bean.group;


public enum GroupType {
    OPENED, CLOSED;

    public static GroupType values(int ordinal) {
        return values()[ordinal];
    }
}
