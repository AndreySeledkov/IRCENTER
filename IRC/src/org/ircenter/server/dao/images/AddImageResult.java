package org.ircenter.server.dao.images;

/**
 * User: Seledkov Kostyantyn
 * Date: 15.05.12
 * Time: 8:46
 */
public class AddImageResult {
    public static final int OK = 0;
    public static final int NOT_IMAGE = 1;
    public static final int UNKNOWN_ERROR = 2;

    private int code;
    private long imageId;


    public AddImageResult(int code, long imageId) {
        this.code = code;
        this.imageId = imageId;
    }

    public long getImageId() {
        return imageId;
    }

    public int getCode() {
        return code;
    }

}
