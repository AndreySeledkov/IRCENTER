package org.ircenter.server.bean.evidence;

/**
 * User: Seledkov Andiry
 * Date: 11.05.12
 * Time: 8:28
 */
public class Tv {
    private long id;
    private String youtubeId;
    private String title;
    private String link;
    private TvType tvType;

    public Tv() {
    }

    public Tv(Tv tv) {
        this.id = tv.id;
        this.youtubeId = tv.youtubeId;
        this.title = tv.title;
        this.link = tv.link;
        this.tvType = tv.tvType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public TvType getTvType() {
        return tvType;
    }

    public void setTvType(TvType tvType) {
        this.tvType = tvType;
    }
}
