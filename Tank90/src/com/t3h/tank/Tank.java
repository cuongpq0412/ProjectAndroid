package com.t3h.tank;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Tank extends Object2D implements Constants {
    protected int step = 1;
    protected int delay;
    protected int orientation;
    protected Image[] images;
    private long currentMove;
    private long currentFireBullet;
    protected boolean isLeft, isUp, isRight, isDown, isSpace;
    protected int delayFireBullet = 100;
    private List<Bullet> bullets;

    public Tank() {
        bullets = new ArrayList<>();
        images = new Image[4];
    }

    public void fireBullet(long time) {
        if (time - currentFireBullet < delayFireBullet) {
            return;
        }
        currentFireBullet = time;
        Bullet bullet = new Bullet();
        bullet.setH(SIZE_BULLET);
        bullet.setW(SIZE_BULLET);
        bullet.setOrientation(orientation);
        bullet.setImage(new ImageIcon(Tank.class.getResource("/imgs/bullet.png")).getImage());
        switch (orientation) {
            case LEFT:
                bullet.setX(x - SIZE_BULLET);
                bullet.setY(y + (h - SIZE_BULLET) / 2);
                break;
            case UP:
                bullet.setX(x + (h - SIZE_BULLET) / 2);
                bullet.setY(y - SIZE_BULLET);
                break;
            case RIGHT:
                bullet.setX(x + w);
                bullet.setY(y + (h - SIZE_BULLET) / 2);
                break;
            default:
                bullet.setX(x + (h - SIZE_BULLET) / 2);
                bullet.setY(y + h);
                break;
        }
        bullet.setDelay(delay/2);
        bullet.setStepMove(step);
        bullets.add(bullet);
    }

    public void moveAllBullet(long time, Item[][] items){
        for ( int i = 0; i < bullets.size(); i++){
            boolean isRemove = bullets.get(i).moveToRemove(time, items);
            if (isRemove){
                bullets.remove(i);
                i--;
            }
        }
    }


    public int getDelayFireBullet() {
        return delayFireBullet;
    }

    public void setDelayFireBullet(int delayFireBullet) {
        this.delayFireBullet = delayFireBullet;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public void move(long time, Item[][] items) {
        if (isLeft || isRight || isDown || isUp) {
            if (time - currentMove < delay) {
                return;
            }
            currentMove = time;
            image = images[orientation];
            switch (orientation) {
                case LEFT:
                    x -= step;
                    if (interactItem(items)) {
                        x += step;
                    }
                    break;
                case UP:
                    y -= step;
                    if (interactItem(items)) {
                        y += step;
                    }
                    break;
                case RIGHT:
                    x += step;
                    if (interactItem(items)) {
                        x -= step;
                    }
                    break;
                default:
                    y += step;
                    if (interactItem(items)) {
                        y -= step;
                    }
                    break;
            }
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);
        for ( int i = 0; i < bullets.size(); i++){
            bullets.get(i).draw(g2d);
        }
    }



    private boolean interactItem(Item[][] items) {
        Rectangle rTan = new Rectangle(x, y, w, h);
        for (int i = 0; i < Constants.ROW; i++) {
            for (int j = 0; j < Constants.COLUMN; j++) {
                if (items[i][j] == null) {
                    continue;
                }
                if (
                        items[i][j].getId() == BRICK_ID ||
                                items[i][j].getId() == ROCK_ID ||
                                items[i][j].getId() == WATER_ID
                ) {
                    Rectangle re = new Rectangle(
                            items[i][j].getX(),
                            items[i][j].getY(),
                            items[i][j].getW(),
                            items[i][j].getH()
                    );
                    if (re.intersects(rTan)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Image[] getImages() {
        return images;
    }
}
