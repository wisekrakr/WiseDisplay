package com.wisekrakr.wisedisplay.device;

import java.awt.*;

public class DisplayModeInstance {

    private int width;
    private int height;
    private int refreshRate;
    private int bitDepth;

    public DisplayModeInstance(int width, int height, int bitDepth, int refreshRate) {
        this.width = width;
        this.height = height;
        this.refreshRate = refreshRate;
        this.bitDepth = bitDepth;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getRefreshRate() {
        return refreshRate;
    }

    public int getBitDepth() {
        return bitDepth;
    }

    @Override
    public String toString() {
        return "Width: " + width + " Height: " + height + " Bit Depth: " + bitDepth + " Refresh Rate: " + refreshRate;
    }
}
