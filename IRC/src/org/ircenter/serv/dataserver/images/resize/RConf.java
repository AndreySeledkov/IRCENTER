package org.ircenter.serv.dataserver.images.resize;

import java.io.Serializable;

/**
 * User: Seledkov Kostyantyn
 * Date: 15.05.12
 * Time: 7:57
 */
public class RConf implements Serializable {

    private int normalizedSize; //480 at Russia server
    private int borderColor;
    private boolean onBackground;

    private boolean process240 = true;
    private boolean process480 = true;
    private boolean processNormalized = true;

    public RConf(boolean onBackground, int normalizedSize) {
        this.onBackground = onBackground;
        this.normalizedSize = normalizedSize;
    }

    public RConf setProcess240(boolean process240) {
        this.process240 = process240;
        return this;
    }

    public RConf setProcess480(boolean process480) {
        this.process480 = process480;
        return this;
    }

    public RConf setProcessNormalized(boolean processNormalized) {
        this.processNormalized = processNormalized;
        return this;
    }

    public int getBorderColor() {
        return borderColor;
    }

    public int getNormalizedSize() {
        return normalizedSize;
    }

    public boolean isOnBackground() {
        return onBackground;
    }

    public boolean isProcess240() {
        return process240;
    }

    public boolean isProcess480() {
        return process480;
    }

    public boolean isProcessNormalized() {
        return processNormalized;
    }


}
