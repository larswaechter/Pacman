package com.larswaechter.map;

import java.awt.*;
import java.util.Objects;

import processing.core.PGraphics;

import com.larswaechter.items.AbstractItem;
import com.larswaechter.items.PointItem;

public class AbstractBlock {
    static final int width = 40;
    static final int height = 40;

    // Positions in array map [x][y]
    private int mapIdxX;
    private int mapIdxY;

    private float x;
    private float y;

    private int color;

    private AbstractItem item;

    AbstractBlock(float x, float y, int mapIdxX, int mapIdxY) {
        this.x = x;
        this.y = y;
        this.mapIdxX = mapIdxX;
        this.mapIdxY = mapIdxY;
    }

    int getMapIdxX() {
        return mapIdxX;
    }

    int getMapIdxY() {
        return mapIdxY;
    }

    void setColor(int color) {
        this.color = color;
    }

    /**
     * Get distance between two blocks with Manhattan-Metric
     *
     * @param a Block a
     * @param b Block b
     * @return Distance between two blocks
     */
    public static int getBlockDistance(AbstractBlock a, AbstractBlock b) {
        return Math.abs(a.mapIdxX - b.mapIdxX) + Math.abs(a.mapIdxY - b.mapIdxY);
    }

    AbstractItem getItem() {
        return this.item;
    }

    void setItem(AbstractItem item) {
        this.item = item;
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
     * Place new PointItem on block
     */
    void setPointItem() {
        Point center = this.getCenter();
        this.setItem(new PointItem((float) center.getX(), (float) center.getY()));
    }

    public boolean hasPointItem() {
        return this.item != null && this.item.getClass().equals(PointItem.class);
    }

    /**
     * Draw map
     *
     * @param g Processing graphic
     */
    void draw(PGraphics g) {
        g.fill(this.color);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractBlock that = (AbstractBlock) o;
        return mapIdxX == that.mapIdxX &&
                mapIdxY == that.mapIdxY &&
                Float.compare(that.x, x) == 0 &&
                Float.compare(that.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mapIdxX, mapIdxY, x, y);
    }


}
