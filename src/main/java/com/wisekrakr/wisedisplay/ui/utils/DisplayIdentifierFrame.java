package com.wisekrakr.wisedisplay.ui.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayIdentifierFrame extends JFrame {

    public DisplayIdentifierFrame(Point location, String id) throws HeadlessException {

        JLabel displayText = new JLabel(id);
        displayText.setBounds(new Rectangle(getWidth()/2, getHeight()/2));
        displayText.setFont(new Font("Verdana", Font.BOLD, 50));
        displayText.setForeground(Color.WHITE);
        add(displayText);

        setBounds(location.x, location.y, 500, 500);
        getContentPane().setBackground(new Color(50, 50, 50));
        setVisible(true);

        Timer timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        timer.start();
    }
}
