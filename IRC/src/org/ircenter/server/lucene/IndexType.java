package org.ircenter.server.lucene;

/**
 * User: Seledkov Kostyantyn
 * Date: 29.05.12
 * Time: 23:10
 */
public enum IndexType {
    NEWS(0), MINUTES_TO_GOD(1), SECRET_SPIRITUAL_WORLD(2),TV(3),VIDEO_EVIDENCE(4);

    private IndexType(int id) {
        this.id = id;
    }

    public static IndexType getSearchIndex(int id) {
        for (IndexType type : IndexType.values()) {
            if (type.id == id) {
                return type;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    private int id;
}
