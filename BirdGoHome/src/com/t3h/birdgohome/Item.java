package com.t3h.birdgohome;

import java.awt.*;

public class Item {
    protected int x, y, size;
    protected Image image;


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(image, x, y, size, size, null);
    }
}
