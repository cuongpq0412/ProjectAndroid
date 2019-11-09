package com.t3h.tank;

import jdk.internal.util.xml.impl.ReaderUTF8;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class GameManager implements Constants {
    private Item[][] arrItem;
    private MyTank myTank;

    public GameManager() {
        loadMap();
        initTank();
    }

    private void initTank(){
        myTank = new MyTank();
        myTank.setX(100);
        myTank.setY(100);
        myTank.setW(SIZE_ITEM * 2 - 6);
        myTank.setH(SIZE_ITEM * 2 - 6);
        myTank.setDelay(20);
        myTank.setStep(2);
        myTank.setOrientation(Constants.UP);
        Image[] imgs = myTank.getImages();
        imgs[LEFT] =new ImageIcon(GameManager.class.getResource("/imgs/player_green_left.png")).getImage();
        imgs[UP] =new ImageIcon(GameManager.class.getResource("/imgs/player_green_up.png")).getImage();
        imgs[RIGHT] =new ImageIcon(GameManager.class.getResource("/imgs/player_green_right.png")).getImage();
        imgs[DOWN] =new ImageIcon(GameManager.class.getResource("/imgs/player_green_down.png")).getImage();
        myTank.setImage(imgs[UP]);


    }

    private void loadMap() {
        arrItem = new Item[ROW][COLUMN];

        try {
            URL url = GameManager.class.getResource("/maps/map.txt");
            InputStream in = url.openStream();
            BufferedReader reader =
                    new BufferedReader(new ReaderUTF8(in));
            String s = reader.readLine();
            int row = 0;
            while (s != null) {
                for (int i = 0; i < s.length(); i++) {
                    int id = s.charAt(i) - '0';
                    if (id == NONE_ID){
                        continue;
                    }
                    Item item = new Item();
                    item.setX(i * SIZE_ITEM);
                    item.setY(row * SIZE_ITEM);
                    if (id == HOME_ID){
                        item.setW(SIZE_ITEM*2);
                        item.setH(SIZE_ITEM*2);
                    }else {
                        item.setW(SIZE_ITEM);
                        item.setH(SIZE_ITEM);
                    }

                    item.setId(id);
                    item.setImage(getImageItem(id));

                    arrItem[row][i] = item;
                }
                row++;
                s = reader.readLine();
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Image getImageItem(int id) {
        switch (id) {
            case ROCK_ID:
                return new ImageIcon(GameManager.class.getResource("/imgs/rock.png")).getImage();
            case BRICK_ID:
                return new ImageIcon(GameManager.class.getResource("/imgs/brick.png")).getImage();
            case TREE_ID:
                return new ImageIcon(GameManager.class.getResource("/imgs/tree.png")).getImage();
            case WATER_ID:
                return new ImageIcon(GameManager.class.getResource("/imgs/water.png")).getImage();
            case HOME_ID:
                return new ImageIcon(GameManager.class.getResource("/imgs/bird.png")).getImage();
            default:
                return null;


        }
    }

    public void drawAll(Graphics2D g2d){
        myTank.draw(g2d);
        for ( int i = 0; i < ROW; i++){
            for (int j = 0; j < COLUMN; j++){
                if (arrItem[i][j] == null){
                    continue;
                }
                arrItem[i][j].draw(g2d);
            }
        }

    }

    public void keyPressed(int keyCode) {
        myTank.keyPress(keyCode);
    }

    public void keyRelease(int keyCode, long time) {
        myTank.releaseKey(keyCode, time);
    }

    public void stepThread(long time) {
        myTank.move(time, arrItem);
        myTank.moveAllBullet(time, arrItem);
        myTank.fireBullet(time);
    }
}
