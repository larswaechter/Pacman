package com.larswaechter.items;

import processing.core.PGraphics;

public abstract class AbstractItem {
    private float x;
    private float y;

    private int color;

    AbstractItem(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Draw Point
     *
     * @param g Processing graphic
     */
    public void draw(PGraphics g) {
        g.fill(this.color);
        g.ellipse(this.x, this.y, 10, 10);
    }

    void setColor(int color) {
        this.color = color;
    }

}
