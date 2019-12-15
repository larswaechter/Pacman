package com.larswaechter;

import processing.core.PGraphics;

import java.awt.*;

class Block {
    static final int width = 40;
    static final int height = 40;

    // Positions in array map [x][y]
    int mapIdxX;
    int mapIdxY;

    private float x;
    private float y;

    private Item item;

    Block(float x, float y, int mapIdxX, int mapIdxY) {
        this.x = x;
        this.y = y;
        this.mapIdxX = mapIdxX;
        this.mapIdxY = mapIdxY;
        this.placeItem();
    }

    Item getItem() {
        return this.item;
    }

    /**
     * Get distance between two blocks with Manhattan-Metric
     *
     * @param a Block a
     * @param b Block b
     * @return Distance between two blocks
     */
    static int getBlockDistance(Block a, Block b) {
        return Math.abs(a.mapIdxX - b.mapIdxX) + Math.abs(a.mapIdxY - b.mapIdxY);
    }

    /**
     * Place new item on block
     */
    void placeItem() {
        Point center = this.getCenter();
        this.item = new Item((float) center.x, (float) center.y);
    }

    /**
     * Remove item from block
     */
    void removeItem() {
        this.item = null;
    }

    /**
     * Check if block has an item
     *
     * @return If block has an item
     */
    boolean hasItem() {
        return this.item != null;
    }

    /**
     * Draw map
     *
     * @param g Processing graphic
     */
    void draw(PGraphics g) {
        int color = 0xFFFFFFFF;
        g.fill(color);
        g.rect(this.x, this.y, Block.width, Block.height);
        if (this.item != null) {
            this.item.draw(g);
        }
    }

    /**
     * Get coordinate of block center as Point
     *
     * @return Center Point
     */
    Point getCenter() {
        int xCenter = (int) (this.x + (Block.width / 2));
        int yCenter = (int) (this.y + (Block.height / 2));

        return new Point(xCenter, yCenter);
    }
}
