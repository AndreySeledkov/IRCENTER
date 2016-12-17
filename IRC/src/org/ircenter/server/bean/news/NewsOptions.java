package org.ircenter.server.bean.news;

/**
 * User: Seledkov Kostyantyn
 * Date: 28.03.12
 * Time: 23:40
 */
public enum NewsOptions {

    ALL(-1), LOCAL(2), CAPITAL(3);
    private int id;

    private NewsOptions(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static NewsOptions getSection(int id) {
        for (NewsOptions s : values()) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }
}

