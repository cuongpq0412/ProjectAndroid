package com.t3h.birdgohome;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyPanel extends JPanel {
    private ManagerGame managerGame;

    public MyPanel() {
        setSize(800, 600);
        addListner();
        managerGame = new ManagerGame();
        initThread();
    }
    private void addListner(){
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                int code = keyEvent.getKeyCode();
                managerGame.keyPress(code);
            }
        });
    }
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        managerGame.drawAll(g2d, getWidth(), getHeight());
    }


    private long myTime = 0;

    public void initThread(){
        //tao khong gian hoat dong cua Thread->Runable
        Runnable r = new Runnable() {
            @Override
            public void run() {
                //day khong gian cua Thread
                while (true){
                    boolean isDe =
                            managerGame.stepThread(myTime);
                    if (isDe){
                        dieBird();
                        break;
                    }
                    myTime++;
                    repaint();
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread th = new Thread(r);
        th.start();
    }

    private void dieBird(){
        JOptionPane.showMessageDialog(this,
                "Ahihi");
        managerGame = new ManagerGame();
        myTime = 0;
        initThread();
    }
}
