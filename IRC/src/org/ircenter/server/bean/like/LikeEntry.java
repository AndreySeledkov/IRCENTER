package org.ircenter.server.bean.like;

public class LikeEntry<T> {

    private T likeEntry;
    private int rate;

    public LikeEntry(T object, Integer rate) {
        this.likeEntry = object;
        this.rate = rate;
    }

    public T getLikeEntry() {
        return likeEntry;
    }

    public void setLikeEntry(T object) {
        this.likeEntry = object;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
