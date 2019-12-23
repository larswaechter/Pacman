package com.larswaechter.items;

import processing.core.PGraphics;

public abstract class AbstractItem {
    private float x;
    private float y;

    private int color;

    private ItemTypes type;

    AbstractItem(ItemTypes type, float x, float y) {
        this.type = type;
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

    public ItemTypes getType() {
        return this.type;
    }

    void setColor(int color) {
        this.color = color;
    }

}
