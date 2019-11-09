package com.t3h.birdgohome;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class Bird extends Item {
    public static final int LEFT = 100;
    public static final int UP = 101;
    public static final int RIGHT = 234;
    public static final int DOWN = 100;
    private int orientation;
    private int score;
    private int delay;
    private boolean isLeft, isRight, isUp, isDown;

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    @Override
    public void draw(Graphics2D g2d) {
        switch (orientation) {
            case LEFT:
                image = new ImageIcon(
                        Bird.class.getResource("/imgs/bird_left.png")
                ).getImage();
                break;
            case RIGHT:
                image = new ImageIcon(
                        Bird.class.getResource("/imgs/bird_right.png")
                ).getImage();
                break;
            default:

                break;
        }
        super.draw(g2d);
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public <T extends Item> T interact(List<T> items) {
        Rectangle recBird = new Rectangle(
                x+5, y+5, size-10, size-10
        );
        //va cham voi tung fruit
        for (T fruit : items) {
            Rectangle recF = new Rectangle(
                    fruit.getX()+5, fruit.getY()+5,
                    fruit.getSize()-10,
                    fruit.getSize()-10
            );
            //kiem tra va cham
            boolean isInter =
                    recBird.intersects(recF);
            if (isInter) {
                score += 5;
                return fruit;
            }
        }
        return null;
    }

    public void movePress(int code) {
        switch (code) {
            case KeyEvent.VK_A:
                isLeft = true;
                isRight = isUp = isDown = false;
                break;
            case KeyEvent.VK_D:
                isRight = true;
                isLeft = isUp = isDown = false;
                break;
            case KeyEvent.VK_W:
                isUp = true;
                isLeft = isRight = isDown = false;
                break;
            case KeyEvent.VK_S:
                isDown = true;
                isLeft = isUp = isRight = false;
                break;
        }
    }

    public void move(long time) {
        if ( time % delay != 0){
            return;
        }
        if (isLeft) {
            orientation = Bird.LEFT;
            x = x - 1;
        } else if (isUp) {
            orientation = Bird.UP;
            y = y - 1;
        } else if (isRight) {
            orientation = Bird.RIGHT;
            x = x + 1;
        } else if (isDown) {
            orientation = Bird.DOWN;
            y = y + 1;
        }
    }
}
