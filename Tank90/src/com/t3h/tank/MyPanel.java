package com.t3h.tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyPanel extends JPanel implements Constants {
    private GameManager gameManager;
    public MyPanel(){
        setSize(WIDTH_FRAME, HEIGHT_FRAME);
        setBackground(Color.BLACK);
        gameManager = new GameManager();

        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                gameManager.keyPressed(keyEvent.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                gameManager.keyRelease(keyEvent.getKeyCode(), time);
            }
        });
        initThread();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2d = (Graphics2D)graphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gameManager.drawAll(g2d);
    }

    private long time;
    private void initThread(){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                while (true){
                    gameManager.stepThread(time);
                    time++;
                    repaint();
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        new Thread(r).start();
    }
}
