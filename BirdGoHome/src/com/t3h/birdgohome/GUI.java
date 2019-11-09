package com.t3h.birdgohome;

import javax.swing.*;

public class GUI extends JFrame {
    private MyPanel myPanel;
    public GUI(){
        setTitle("GUI");
        setSize(800, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        myPanel = new MyPanel();
        add(myPanel);
    }
}
