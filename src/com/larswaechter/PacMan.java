package com.larswaechter;

import processing.core.PGraphics;

import java.awt.*;

class PacMan {
    private float x;
    private float y;

    private Block currentBlock;

    int pointCounter = 0;

    PacMan(Block spawnBlock) {
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
        float radius = 15;

        int color = 0xFFFFFF00;
        g.fill(color);
        g.ellipse(this.x, this.y, radius * 2, radius * 2);
    }

    /**
     * Get current block player is positioned on
     * @return Block
     */
    Block getCurrentBlock() {
        return currentBlock;
    }

    /**
     * Set current block player is positioned on
     * @param currentBlock Block
     */
    void setCurrentBlock(Block currentBlock) {
        this.currentBlock = currentBlock;
    }

    /**
     * Move player 1 block into given direction
     *
     * @param direction Direction to move to
     */
    void move(int direction) {
        switch (direction) {
            // UP
            case 38:
                this.moveUp();
                this.movePostHandler();
                break;

            // DOWN
            case 40:
                this.moveDown();
                this.movePostHandler();
                break;

            // LEFT
            case 37:
                this.moveLeft();
                this.movePostHandler();
                break;

            // RIGHT
            case 39:
                this.moveRight();
                this.movePostHandler();
                break;
        }
    }

    private void movePostHandler() {
        if (this.getCurrentBlock().hasItem()) {
            this.pointCounter++;
            this.getCurrentBlock().removeItem();
        }
    }

    /**
     * Move player 1 block up
     */
    private void moveUp() {
        this.setCurrentBlock(Map.getBlockAbove(this.currentBlock));
        this.y = getCurrentBlock().getCenter().y;
    }

    /**
     * Move player 1 block down
     */
    private void moveDown() {
        this.setCurrentBlock(Map.getBlockDown(this.currentBlock));
        this.y = this.getCurrentBlock().getCenter().y;
    }

    /**
     * Move player 1 block left
     */
    private void moveLeft() {
        this.setCurrentBlock(Map.getBlockLeft(this.currentBlock));
        this.x = this.getCurrentBlock().getCenter().x;
    }

    /**
     * Move player 1 block right
     */
    private void moveRight() {
        this.setCurrentBlock(Map.getBlockRight(this.currentBlock));
        this.x = this.getCurrentBlock().getCenter().x;
    }
}
