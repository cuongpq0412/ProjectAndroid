package com.t3h.tank;

import java.awt.*;

public class Bullet extends Object2D implements Constants {
    private int orientation;
    private int delay;
    private int stepMove = 1;

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public boolean moveToRemove(long time, Item[][] items) {
        if (time % delay != 0) {
            return false;
        }
        switch (orientation) {
            case LEFT:
                x -= stepMove;
                break;
            case UP:
                y -= stepMove;
                break;
            case RIGHT:
                x += stepMove;
                break;
            default:
                y += stepMove;
                break;

        }
        return interactWithItem(items);
    }

    private boolean interactWithItem(Item[][] items) {
        Rectangle rBullet = new Rectangle(x, y, w, h);
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLUMN; j++) {
                if (items[i][j] == null) {
                    continue;
                }
                if (items[i][j].getId() == TREE_ID || items[i][j].getId() == WATER_ID) {
                    continue;
                }
                Rectangle rItem = new Rectangle(items[i][j].getX(), items[i][j].getY(), items[i][j].getW(), items[i][j].getH());
                if (items[i][j].getId() == BRICK_ID) {
                    if (rItem.intersects(rBullet)) {
                        items[i][j] = null;
                        return true;
                    }
                } else {
                    if (items[i][j].getId() == ROCK_ID) {
                        if (rItem.intersects(rBullet)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getStepMove() {
        return stepMove;
    }

    public void setStepMove(int stepMove) {
        this.stepMove = stepMove;
    }
}
