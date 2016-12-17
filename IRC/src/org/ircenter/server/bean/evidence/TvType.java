package org.ircenter.server.bean.evidence;

/**
 * User: Seledkov Kostyantyn
 * Date: 11.05.12
 * Time: 8:29
 */
public enum TvType {
    KIEV, DNEPR;

    public static TvType valueOf(int ordinal) {
        return values()[ordinal];
    }

}
