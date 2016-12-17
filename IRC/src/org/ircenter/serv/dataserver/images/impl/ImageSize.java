package org.ircenter.serv.dataserver.images.impl;


import org.ircenter.serv.dataserver.ImageService;

/**
 * User: Seledkov Kostyantyn
 * Date: 15.05.12
 * Time: 7:56
 */
public enum ImageSize {
    size176big(330, 220, ImageService.IMG_SIZE_330_220),
    size240big(120, 67, ImageService.IMG_SIZE_120_67),
    size320big(170, 121, ImageService.IMG_SIZE_170_121),
    size480big(800, 600, ImageService.IMG_SIZE_800_600),

    size176small(46, 62, ImageService.IMG_SIZE_176_220_SMALL),
    size240small(62, 84, ImageService.IMG_SIZE_240_320_SMALL),
    size320small(82, 112, ImageService.IMG_SIZE_320_480_SMALL),
    size480small(124, 168, ImageService.IMG_SIZE_480_640_SMALL);

    private int width;
    private int height;
    private int id;

    ImageSize(int width, int height, int id) {
        this.width = width;
        this.height = height;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public static ImageSize valueOfId(int id) {
        switch (id) {
            case ImageService.IMG_SIZE_330_220: return size176big;
            case ImageService.IMG_SIZE_120_67: return size240big;
            case ImageService.IMG_SIZE_170_121: return size320big;
            case ImageService.IMG_SIZE_800_600: return size480big;

            case ImageService.IMG_SIZE_176_220_SMALL: return size176small;
            case ImageService.IMG_SIZE_240_320_SMALL: return size240small;
            case ImageService.IMG_SIZE_320_480_SMALL: return size320small;
            case ImageService.IMG_SIZE_480_640_SMALL: return size480small;
            default:
                throw new IllegalArgumentException();
        }
    }
}
