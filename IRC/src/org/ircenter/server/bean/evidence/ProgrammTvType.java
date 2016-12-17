package org.ircenter.server.bean.evidence;

/**
 * Created by IntelliJ IDEA.
 * User: 1
 * Date: 11.05.12
 * Time: 8:29
 * To change this template use File | Settings | File Templates.
 */
public enum ProgrammTvType {
    CNL, LIFE_TV, IMPACT, TET;

    public static ProgrammTvType valueOf(int ordinal) {
        return values()[ordinal];
    }

}
