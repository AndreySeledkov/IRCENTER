package org.ircenter.server.bean.favorite;

/**
 * Created by IntelliJ IDEA.
 * User: 1
 * Date: 09.04.12
 * Time: 10:05
 * To change this template use File | Settings | File Templates.
 */
public class FavoritePost {

    private long userId;
    private long postId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }
}
