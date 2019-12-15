package com.larswaechter.items;

import processing.core.PGraphics;

public abstract class AbstractItem {
    private float x;
    private float y;

    private int color;

    private float radius = 5;

    AbstractItem(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    void setColor(int color) {
        this.color = color;
    }

    /**
     * Draw Point
     *
     * @param g Processing graphic
     */
    public void draw(PGraphics g) {
        g.fill(this.color);
        g.ellipse(this.x, this.y, radius * 2, radius * 2);
    }
}
