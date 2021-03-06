package com.larswaechter.map;

import java.awt.*;
import java.util.Objects;

import com.larswaechter.DrawInterface;
import processing.core.PGraphics;

import com.larswaechter.items.*;

public abstract class AbstractBlock implements DrawInterface {
    static final int width = 40;
    static final int height = 40;

    private BlockTypes type;

    // Positions in array map [x][y]
    private int mapIdxX;
    private int mapIdxY;

    private float x;
    private float y;

    private int color;

    private Point center;

    private AbstractItem item;

    AbstractBlock(BlockTypes type, float x, float y, int mapIdxX, int mapIdxY) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.mapIdxX = mapIdxX;
        this.mapIdxY = mapIdxY;

        int xCenter = (int) (this.x + (Block.width / 2));
        int yCenter = (int) (this.y + (Block.height / 2));
        this.center = new Point(xCenter, yCenter);
    }

    /**
     * Get distance between two blocks with Manhattan-Metric
     *
     * @param a Block a
     * @param b Block b
     * @return Distance between two blocks
     */
    public static int manhattanDistance(AbstractBlock a, AbstractBlock b) {
        return Math.abs(a.mapIdxX - b.mapIdxX) + Math.abs(a.mapIdxY - b.mapIdxY);
    }

    /**
     * Generate block item based on type
     *
     * @param type Type of item to generate
     */
    public void generateItem(ItemTypes type) {
        Point center = this.getCenter();

        switch (type) {
            case Point:
                this.setItem(new PointItem((float) center.getX(), (float) center.getY()));
                break;
            case Shield:
                this.setItem(new ShieldItem((float) center.getX(), (float) center.getY()));
                break;
            case Multiplicator:
                this.setItem(new MultiplicatorItem((float) center.getX(), (float) center.getY(), 3));
                break;
        }
    }

    public AbstractItem getItem() {
        return this.item;
    }

    public void removeItem() {
        this.item = null;
    }

    public BlockTypes getType() {
        return this.type;
    }

    /**
     * Get coordinate of block center as Point
     *
     * @return Center Point
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * Draw map
     *
     * @param g Processing graphic
     */
    @Override
    public void draw(PGraphics g) {
        g.fill(this.color);
        g.rect(this.x, this.y, Block.width, Block.height);
        if (this.item != null) {
            this.item.draw(g);
        }
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

    int getMapIdxX() {
        return mapIdxX;
    }

    int getMapIdxY() {
        return mapIdxY;
    }

    void setColor(int color) {
        this.color = color;
    }

    void setItem(AbstractItem item) {
        this.item = item;
    }
}
