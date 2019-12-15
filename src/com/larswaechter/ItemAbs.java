package com.larswaechter;

abstract class ItemAbs {
    float x;
    float y;

    int color;
    float radius = 5;

    ItemAbs(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
