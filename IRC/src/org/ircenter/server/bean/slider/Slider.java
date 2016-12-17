package org.ircenter.server.bean.slider;

/**
 * Created by IntelliJ IDEA.
 * User: 1
 * Date: 16.04.12
 * Time: 17:57
 * To change this template use File | Settings | File Templates.
 */
public class Slider {
    private long id;
    private SliderType sliderType;
    private int imgId;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public SliderType getSliderType() {
        return sliderType;
    }

    public void setSliderType(SliderType sliderType) {
        this.sliderType = sliderType;
    }
}
