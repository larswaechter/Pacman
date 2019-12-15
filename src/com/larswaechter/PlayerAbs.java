package com.larswaechter;

import processing.core.PApplet;
import processing.core.PGraphics;

import java.awt.*;

class PlayerAbs extends PApplet {
    float x;
    float y;

    int color;

    Block currentBlock;

    private float radius = 15;

    PlayerAbs(Block spawnBlock) {
        this.setCurrentBlock(spawnBlock);
        this.getCurrentBlock().removeItem();
        Point blockCenter = this.getCurrentBlock().getCenter();
        this.x = blockCenter.x;
        this.y = blockCenter.y;
    }

    /**
     * Draw map
     * @param g Processing graphic
     */
    void draw(PGraphics g) {
        g.fill(this.color);
        g.ellipse(this.x, this.y, this.radius * 2, this.radius * 2);
    }

    /**
     * Get current block player is positioned on
     *
     * @return Block
     */
    Block getCurrentBlock() {
        return currentBlock;
    }

    /**
     * Set current block player is positioned on
     *
     * @param currentBlock Block
     */
    void setCurrentBlock(Block currentBlock) {
        this.currentBlock = currentBlock;
    }
}
