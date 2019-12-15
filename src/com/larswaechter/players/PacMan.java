package com.larswaechter.players;

import com.larswaechter.map.*;

public class PacMan extends AbstractPlayer {
    private int pointCounter = 0;

    public PacMan(Block spawnBlock) {
        super(spawnBlock);
        this.setColor(0xFFFFFF00);
    }

    public int getPointCounter() {
        return pointCounter;
    }

    /**
     * Move PacMan 1 block to given direction
     *
     * @param direction Direction to move to
     */
    public void move(int direction) {
        if (this.frameCounter % this.getSpeed() == 0) {
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
    }

    /**
     * Executed after move event
     */
    private void movePostHandler() {
        if (this.getCurrentBlock().hasItem()) {
            this.pointCounter++;
            this.getCurrentBlock().removeItem();
        }
    }

    /**
     * Move PacMan 1 block up
     */
    private void moveUp() {
        this.moveToBlock(Map.getBlockTop(this.getCurrentBlock()));
    }

    /**
     * Move PacMan 1 block down
     */
    private void moveDown() {
        this.moveToBlock(Map.getBlockDown(this.getCurrentBlock()));
    }

    /**
     * Move PacMan 1 block left
     */
    private void moveLeft() {
        this.moveToBlock(Map.getBlockLeft(this.getCurrentBlock()));
    }

    /**
     * Move PacMan 1 block right
     */
    private void moveRight() {
        this.moveToBlock(Map.getBlockRight(this.getCurrentBlock()));
    }
}
