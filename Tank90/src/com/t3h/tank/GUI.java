package com.t3h.tank;

import javax.swing.*;

public class GUI extends JFrame
        implements Constants {
    public GUI() {
        setTitle("TANK");
        setSize(WIDTH_FRAME,
                HEIGHT_FRAME);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        add(new MyPanel());
    }
}
