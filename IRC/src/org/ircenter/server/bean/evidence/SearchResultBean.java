package org.ircenter.server.bean.evidence;

/**
 * User: Seledkov Kostyantyn
 * Date: 19.06.12
 * Time: 8:46
 */
public class SearchResultBean {
    private long id;
    private String link;
    private String defaultImage;
    private String title;

    public SearchResultBean(long id, String link, String defaultImage, String title) {
        this.id = id;
        this.link = link;
        this.defaultImage = defaultImage;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public String getLink() {
        return link;
    }

    public String getDefaultImage() {
        return defaultImage;
    }

    public String getTitle() {
        return title;
    }
}
