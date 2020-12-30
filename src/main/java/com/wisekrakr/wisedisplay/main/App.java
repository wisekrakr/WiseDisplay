package com.wisekrakr.wisedisplay.main;

import com.wisekrakr.wisedisplay.device.Display;
import com.wisekrakr.wisedisplay.ui.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class App {

    private final GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
    private final GraphicsDevice[] devices = env.getScreenDevices();

    public App() {
        ArrayList<Display> displays = new ArrayList<>();
        for (GraphicsDevice device : devices) {
            if(device.getIDstring().contains("Display0")){
                displays.add(new Display(device, "MAIN"));
            }
            if(device.getIDstring().contains("Display2")){
                displays.add(new Display(device, "SECONDARY"));
            }
            if(device.getIDstring().contains("Display1")){
                displays.add(new Display(device, "TV"));
            }
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame mainFrame = new MainFrame(displays);
                mainFrame.init();
            }
        });
    }

    private Display testDevice(){
        GraphicsDevice d = null;
        for (GraphicsDevice device : devices) {
            if(device.getIDstring().contains("Display2")){
                d = device;
            }
        }
        return new Display(d, "S24C750");
    }


}
