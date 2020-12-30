package com.wisekrakr.wisedisplay.ui;

import com.wisekrakr.wisedisplay.device.Display;
import com.wisekrakr.wisedisplay.device.DisplayModeInstance;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class DisplayFrame extends JFrame {

    public DisplayFrame(GraphicsDevice device, DisplayModeInstance selectedMode) throws HeadlessException {
        setSize(selectedMode.getWidth(), selectedMode.getHeight());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        device.setFullScreenWindow(this);
        device.setDisplayMode(new DisplayMode(
                selectedMode.getWidth(), selectedMode.getHeight(), selectedMode.getBitDepth(), selectedMode.getRefreshRate()
        ));

        setVisible(true);
    }

    public DisplayFrame(ArrayList<Display> displays) throws HeadlessException {
        Rectangle2D result = new Rectangle2D.Double();
        for (Display display: displays){
            Rectangle2D.union(result, display.getDevice().getDefaultConfiguration().getBounds(), result);

            display.getDevice().setFullScreenWindow(this);
            display.getDevice().setDisplayMode(new DisplayMode(
                    (int)result.getWidth(), (int)result.getHeight(), 32, 60
            ));
        }
        setSize((int)result.getWidth(), (int)result.getHeight());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);

        System.out.println("MULTI DISPLAY ON! SPREAD THAT....");
    }


}
