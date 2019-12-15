package com.larswaechter.map;

import com.larswaechter.items.AbstractItem;
import com.larswaechter.items.Point;

import processing.core.PGraphics;

public class Block {
    static final int width = 40;
    static final int height = 40;

    // Positions in array map [x][y]
    private int mapIdxX;
    private int mapIdxY;

    private float x;
    private float y;

    private AbstractItem item;

    Block(float x, float y, int mapIdxX, int mapIdxY) {
        this.x = x;
        this.y = y;
        this.mapIdxX = mapIdxX;
        this.mapIdxY = mapIdxY;
        this.placeItem();
    }

    int getMapIdxX() {
        return mapIdxX;
    }

    int getMapIdxY() {
        return mapIdxY;
    }

    AbstractItem getItem() {
        return this.item;
    }

    /**
     * Get distance between two blocks with Manhattan-Metric
     *
     * @param a Block a
     * @param b Block b
     * @return Distance between two blocks
     */
    public static int getBlockDistance(Block a, Block b) {
        return Math.abs(a.mapIdxX - b.mapIdxX) + Math.abs(a.mapIdxY - b.mapIdxY);
    }

    /**
     * Place new item on block
     */
    private void placeItem() {
        Point center = this.getCenter();
        this.item = new Point((float) center.getX(), (float) center.getY());
    }

    /**
     * Remove item from block
     */
    public void removeItem() {
        this.item = null;
    }

    /**
     * Check if block has an item
     *
     * @return If block has an item
     */
    public boolean hasItem() {
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
    public Point getCenter() {
        int xCenter = (int) (this.x + (Block.width / 2));
        int yCenter = (int) (this.y + (Block.height / 2));

        return new Point(xCenter, yCenter);
    }
}
