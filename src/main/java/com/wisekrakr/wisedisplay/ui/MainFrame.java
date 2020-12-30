package com.wisekrakr.wisedisplay.ui;

import com.wisekrakr.wisedisplay.device.Display;
import com.wisekrakr.wisedisplay.device.DisplayModeInstance;
import com.wisekrakr.wisedisplay.ui.utils.DisplayIdentifierFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class MainFrame extends JFrame {

    private final ArrayList<Display> displayDevices;

    public MainFrame(ArrayList<Display> devices) throws HeadlessException {
        this.displayDevices = devices;

        setTitle("Wise Display Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBounds(500,500,700,400);
        setLayout(new FlowLayout());
    }

    public void init(){
        displayModeSelection();
        identifyDisplaysButton();
        multiDisplayTest();

        setVisible(true);
    }

    /**
     * Handles the selection of a Display Mode via a JComboBox
     * When a JComboBoxItem gets clicked, the Graphics Device in question will change immediately
     * Works only for singular Graphic Device Display Mode changes
     */
    private void displayModeSelection(){
        for (Display display: displayDevices){
            JLabel selectLabel = new JLabel("Select a display mode for: " + display.getDevice().getIDstring());

            JComboBox<HashMap<String, DisplayModeInstance>> modeList = new JComboBox(display.displayModeInfo().values().toArray());
            modeList.setBounds(0,0, 200, 50);

            JPanel panel = new JPanel();
            add(panel);
            panel.add(selectLabel);
            panel.add(modeList);

            modeList.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JComboBox combo = (JComboBox)e.getSource();

                    DisplayModeInstance selectedMode = (DisplayModeInstance)combo.getSelectedItem();
                    System.out.println(selectedMode);

                    try {
                        SwingUtilities.invokeLater(new Runnable() {
                           @Override
                           public void run() {
                               new DisplayFrame(display.getDevice() ,selectedMode);
                           }
                        });
                    }catch (Throwable t){
                        throw new IllegalStateException("Could not create new Display Frame with display mode: " + selectedMode, t);
                    }
                }
            });
        }
    }


    /**
     * JButton that will create a JFrame on GraphicDevices/Displays with the ID of the device to identify it.
     */
    private void identifyDisplaysButton(){
        JButton button = new JButton("Identify Displays");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Display display: displayDevices){
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            new DisplayIdentifierFrame(display.getDevice().getDefaultConfiguration().getBounds().getLocation(),
                                    display.getName());

                            System.out.println("Identifying: " + display.getDevice().getDefaultConfiguration().getBounds());
                        }
                    });
                }
            }
        });
        add(button);
    }

    private void multiDisplayTest(){
        JButton button = new JButton("Multi Display Test");
        add(button);

        ArrayList<Display>displays = new ArrayList<>();

        for(Display display: displayDevices){
            if(display.getName().equals("MAIN") || display.getName().equals("SECONDARY")){
                displays.add(display);
                System.out.println("Displays added");
            }
        }

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DisplayFrame(displays);
            }
        });
    }
}
