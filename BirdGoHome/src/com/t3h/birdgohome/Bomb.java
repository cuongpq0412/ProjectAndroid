package com.t3h.birdgohome;

public class Bomb extends Item {
    private int delay;

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public void move(long time){
        if (time % delay != 0){
            return;
        }
        y++;
    }
}
