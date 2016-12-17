package org.ircenter.server.lucene;

/**
 * User: Seledkov Kostyantyn
 * Date: 19.06.12
 * Time: 8:19
 */
public enum CategoryType {
    NEWS(0), VIDEO(1);
    private int id;

    private CategoryType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static CategoryType getCatType(int id) {
        for (CategoryType type : values()) {
            if (type.id == id) {
                return type;
            }
        }
        return null;
    }
}
