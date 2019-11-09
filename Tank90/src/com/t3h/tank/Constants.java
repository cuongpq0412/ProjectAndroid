package com.t3h.tank;

public interface Constants {
    int COLUMN = 28;
    int ROW = COLUMN;
    int SIZE_ITEM = 20;
    int WIDTH_FRAME = COLUMN * SIZE_ITEM;
    int HEIGHT_FRAME = ROW * SIZE_ITEM;
    int SIZE_BULLET = SIZE_ITEM/5;

    int ROCK_ID = 5;
    int BRICK_ID = 1;
    int TREE_ID = 4;
    int WATER_ID = 2;
    int HOME_ID = 9;
    int NONE_ID = 0;


    int LEFT = 0;
    int UP = 1;
    int RIGHT = 2;
    int DOWN = 3;
}
