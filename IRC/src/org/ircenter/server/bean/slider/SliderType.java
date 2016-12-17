package org.ircenter.server.bean.slider;

/**
 * Created by IntelliJ IDEA.
 * User: 1
 * Date: 22.05.12
 * Time: 1:05
 * To change this template use File | Settings | File Templates.
 */
public enum SliderType {
    MAIN_SLIDER;

    public static SliderType valueOf(int ordinal) {
        return values()[ordinal];
    }

}
