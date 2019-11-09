package com.t3h.birdgohome;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ManagerGame {
    private Image bg;
    private Home home;
    private Bird bird;
    private List<Fruit> fruits;
    private List<Bomb> bombs;
    public ManagerGame() {
        bg = new ImageIcon(
                MyPanel.class.getResource("/imgs/bg.png")
        ).getImage();
        createBird();
        fruits = new ArrayList<>();
        fruits.add(createFruit("strawberry.png"));
        fruits.add(createFruit("orange.png"));
        fruits.add(createFruit("strawberry.png"));
        fruits.add(createFruit("orange.png"));
        fruits.add(createFruit("strawberry.png"));
        fruits.add(createFruit("strawberry.png"));
        fruits.add(createFruit("strawberry.png"));
        bombs = new ArrayList<>();
        bombs.add(createBomb());
        bombs.add(createBomb());
        bombs.add(createBomb());
        bombs.add(createBomb());
        bombs.add(createBomb());
    }

    private Bomb createBomb() {
        Bomb bomb = new Bomb();
        Random rd = new Random();
        int size = rd.nextInt(51) + 20;
        bomb.setX(rd.nextInt(800 - size));
        bomb.setY(0);
        bomb.setSize(size);
        bomb.setDelay(
                rd.nextInt(5)+2
        );
        bomb.setImage(
                new ImageIcon(
                        MyPanel.class.getResource(
                                "/imgs/bomb.png"
                        )
                ).getImage()
        );
        return bomb;
    }

    private void createBird() {
        bird = new Bird();
        bird.setX(10);
        bird.setY(100);
        bird.setSize(40);
        bird.setDelay(4);
        bird.setImage(new ImageIcon(
                MyPanel.class.getResource("/imgs/bird_left.png")
        ).getImage());
    }

    private Fruit createFruit(String fruitSource) {
        Fruit fruit = new Fruit();
        Random rd = new Random();
        int size = rd.nextInt(51) + 20;
        fruit.setX(rd.nextInt(800 - size));
        fruit.setY(rd.nextInt(600 - size));
        fruit.setSize(size);
        fruit.setImage(
                new ImageIcon(
                        MyPanel.class.getResource(
                                "/imgs/" + fruitSource
                        )
                ).getImage()
        );
        return fruit;
    }
    public void drawAll(Graphics2D g2d, int width, int height) {
        g2d.drawImage(bg, 0, 0, width, height,
                null);
        bird.draw(g2d);
        for (Fruit fruit : fruits) {
            fruit.draw(g2d);
        }
        for (Bomb bomb : bombs) {
            bomb.draw(g2d);
        }
    }
    public void keyPress(int code) {
        bird.movePress(code);
    }

    public boolean stepThread(long time) {
        bird.move(time);
        Fruit fruit = bird.interact(fruits);
        if (fruit != null) {
            fruits.remove(fruit);
            fruits.add(createFruit("orange.png"));
        }
        Bomb bomb = bird.interact(bombs);
        if ( bomb != null) {
            return true;
        }
        for (int i = 0; i < bombs.size(); i++) {
            Bomb b = bombs.get(i);
            b.move(time);
            if ( b.getY() >= Constants.HEIGHT_FRAME){
                bombs.remove(b);
                bombs.add(i, createBomb());
            }
        }
        return false;
    }
}
