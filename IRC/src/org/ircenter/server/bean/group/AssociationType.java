package org.ircenter.server.bean.group;


public enum AssociationType {
    MEETING, COMMUNITY;

    public static AssociationType valueOf(int ordinal) {
        return values()[ordinal];
    }
}
