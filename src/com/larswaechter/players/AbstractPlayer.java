package com.larswaechter.players;

import java.awt.*;

import processing.core.PApplet;
import processing.core.PGraphics;

import com.larswaechter.map.AbstractBlock;

public abstract class AbstractPlayer extends PApplet {
    public static int diameter = 30;

    private float x;
    private float y;

    private AbstractBlock currentBlock;

    // Higher value = slower speed
    private float speed = 5;

    private int color;

    AbstractPlayer() {
    }

    AbstractPlayer(AbstractBlock spawnBlock) {
        this.moveToBlock(spawnBlock);
    }

    /**
     * Draw player
     *
     * @param g Processing graphic
     */
    public void draw(PGraphics g) {
        g.fill(this.color);
        g.ellipse(this.x, this.y, AbstractPlayer.diameter, AbstractPlayer.diameter);
    }

    /**
     * Get current block player is positioned on
     *
     * @return Block
     */
    public AbstractBlock getCurrentBlock() {
        return currentBlock;
    }

    /**
     * Move player to given block
     *
     * @param block Block to move to
     */
    void moveToBlock(AbstractBlock block) {
        this.setCurrentBlock(block);

        // Update coordinates on map
        Point blockCenter = block.getCenter();
        this.x = (float) blockCenter.getX();
        this.y = (float) blockCenter.getY();
    }

    /**
     * TODO: Approach bloack with given frequency
     *
     * @param block Block to approach
     */
    void approachBlock(AbstractBlock block) {
    }

    /**
     * Set current block player is positioned on
     *
     * @param currentBlock Block
     */
    private void setCurrentBlock(AbstractBlock currentBlock) {
        this.currentBlock = currentBlock;
    }

    float getSpeed() {
        return speed;
    }

    void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getColor() {
        return color;
    }

    void setColor(int color) {
        this.color = color;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }


}
