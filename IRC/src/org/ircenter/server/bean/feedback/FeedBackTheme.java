package org.ircenter.server.bean.feedback;

/**
 * Created by IntelliJ IDEA.
 * User: 1
 * Date: 06.05.12
 * Time: 22:00
 * To change this template use File | Settings | File Templates.
 */
public enum FeedBackTheme {
    MISTAKE(0),
    PROPOSE(1);

    private int id;

    private FeedBackTheme(int i) {
        this.id = i;
    }

    public static FeedBackTheme dbValueOf(int id) {
        for (FeedBackTheme a : values()) {
            if (a.id == id) {
                return a;
            }
        }
        return null;
    }
}
