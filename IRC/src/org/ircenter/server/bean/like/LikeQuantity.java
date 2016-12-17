package org.ircenter.server.bean.like;

public class LikeQuantity {

    private ServiceType type;
    private long contentId;
    private int likeCount;
    private int dislikeCount;

    public LikeQuantity() {
    }

    public LikeQuantity(LikeQuantity likeQuantity) {
        this.type = likeQuantity.type;
        this.contentId = likeQuantity.contentId;
        this.likeCount = likeQuantity.likeCount;
        this.dislikeCount = likeQuantity.dislikeCount;
    }

    public ServiceType getType() {
        return type;
    }

    public void setType(ServiceType type) {
        this.type = type;
    }

    public long getContentId() {
        return contentId;
    }

    public void setContentId(long contentId) {
        this.contentId = contentId;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(int dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    public int getRate() {
        return likeCount - dislikeCount;
    }
    
    public boolean sameRecord(LikeQuantity likeQuantity) {
        return this.type == likeQuantity.type && this.contentId == likeQuantity.contentId;
    }
}
