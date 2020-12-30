package com.wisekrakr.wisedisplay.device;

import java.awt.*;
import java.util.HashMap;

public class Display {

    private final GraphicsDevice device;
    private final String name;

    public Display(GraphicsDevice device, String name) {
        this.device = device;
        this.name = name;
    }

    public HashMap<String,DisplayModeInstance> displayModeInfo(){
        HashMap<String,DisplayModeInstance> infoList = new HashMap<>();
        DisplayModeInstance modeInstance;
        for(DisplayMode mode: device.getDisplayModes()){
            modeInstance = new DisplayModeInstance(mode.getWidth(), mode.getHeight(), mode.getBitDepth(), mode.getRefreshRate());

            infoList.put(modeInstance.toString(), modeInstance);
        }
        return infoList;
    }

    @Override
    public String toString() {
        return "Display{" +
                "Device      : " + device.getIDstring() +
                "Name        : " + name +
                "Width       : " + device.getDisplayMode().getWidth() +
                "Height      : " + device.getDisplayMode().getHeight() +
                "Bit Depth   : " + device.getDisplayMode().getBitDepth() +
                "Refresh Rate: " + device.getDisplayMode().getRefreshRate() +
                "Bounds      : " + device.getDefaultConfiguration().getBounds() +
                "}";
    }

    public GraphicsDevice getDevice() {
        return device;
    }
    public String getName() {
        return name;
    }
}
