package com.larswaechter.players;

import com.larswaechter.map.Block;
import com.larswaechter.items.Point;

import processing.core.PApplet;
import processing.core.PGraphics;

public abstract class AbstractPlayer extends PApplet {
    public static float frameCounter = 0;

    private float x;
    private float y;

    private Block currentBlock;

    // Higher value = slower speed
    private float speed = 5;

    private int color;

    private float radius = 15;

    AbstractPlayer() {
    }

    AbstractPlayer(Block spawnBlock) {
        this.moveToBlock(spawnBlock);
        this.getCurrentBlock().removeItem();
    }

    /**
     * Draw player
     *
     * @param g Processing graphic
     */
    public void draw(PGraphics g) {
        g.fill(this.color);
        g.ellipse(this.x, this.y, this.radius * 2, this.radius * 2);
    }

    /**
     * Move player to given block
     *
     * @param block Block to move to
     */
    void moveToBlock(Block block) {
        this.setCurrentBlock(block);

        // Update coordinates on map
        Point blockCenter = block.getCenter();
        this.x = blockCenter.getX();
        this.y = blockCenter.getY();
    }

    /**
     * Get current block player is positioned on
     *
     * @return Block
     */
    public Block getCurrentBlock() {
        return currentBlock;
    }

    /**
     * Set current block player is positioned on
     *
     * @param currentBlock Block
     */
    private void setCurrentBlock(Block currentBlock) {
        this.currentBlock = currentBlock;
    }

    float getSpeed() {
        return speed;
    }

    void setSpeed(float speed) {
        this.speed = speed;
    }

    void setColor(int color) {
        this.color = color;
    }
}
