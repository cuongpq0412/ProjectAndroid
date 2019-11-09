package com.t3h.tank;

import java.awt.event.KeyEvent;

public class MyTank extends Tank {
    //    private boolean isLeft, isUp, isRight, isDown;
    public void keyPress(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                isLeft = true;
                orientation = LEFT;
                image = images[orientation];
                break;
            case KeyEvent.VK_UP:
                isUp = true;
                orientation = UP;
                image = images[orientation];
                break;
            case KeyEvent.VK_RIGHT:
                isRight = true;
                orientation = RIGHT;
                image = images[orientation];
                break;
            case KeyEvent.VK_DOWN:
                isDown = true;
                orientation = DOWN;
                image = images[orientation];
                break;
            case KeyEvent.VK_SPACE:
                isSpace = true;
                break;
        }
    }

    public void releaseKey(int keyCode, long time) {
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                isLeft = false;
                break;
            case KeyEvent.VK_UP:
                isUp = false;
                break;
            case KeyEvent.VK_RIGHT:
                isRight = false;
                break;
            case KeyEvent.VK_DOWN:
                isDown = false;
                break;
            case KeyEvent.VK_SPACE:
                isSpace = false;
                break;
        }
    }

    @Override
    public void fireBullet(long time) {
        if ( !isSpace ){
            return;
        }
        super.fireBullet(time);
    }
}
