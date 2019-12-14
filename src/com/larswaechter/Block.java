package com.larswaechter;

import processing.core.PGraphics;

import java.awt.*;

class Block {
    static final int width = 40;
    static final int height = 40;

    int mapPosX;
    int mapPosY;

    private float x;
    private float y;

    private Item item;

    Block(float x, float y, int mapPosX, int mapPosY) {
        this.x = x;
        this.y = y;
        this.mapPosX = mapPosX;
        this.mapPosY = mapPosY;
        this.placeItem();
    }

    Item getItem() {
        return this.item;
    }

    void placeItem() {
        Point center = this.getCenter();
        this.item = new Item((float) center.x, (float) center.y);
    }

    void removeItem() {
        this.item = null;
    }

    boolean hasItem() {
        return this.item != null;
    }

    /**
     * Draw map
     * @param g Processing graphic
     */
    void draw(PGraphics g) {
        int color = 0xFFFFFFFF;
        g.fill(color);
        g.rect(this.x, this.y, Block.width, Block.height);
        if(this.item != null) {
            this.item.draw(g);
        }
    }

    Point getCenter() {
        int xCenter = (int) (this.x + (Block.width / 2));
        int yCenter = (int) (this.y + (Block.height / 2));

        return new Point(xCenter, yCenter);
    }
}
