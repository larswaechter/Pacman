package com.larswaechter;

class PacMan extends PlayerAbs {
    int pointCounter = 0;

    PacMan(Block spawnBlock) {
        super(spawnBlock);
        this.color = 0xFFFFFF00;
    }

    /**
     * Move player 1 block into given direction
     *
     * @param direction Direction to move to
     */
    void move(int direction) {
        if (this.frameCounter % this.speed == 0) {
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
     * Move player 1 block up
     */
    private void moveUp() {
        this.moveToBlock(Map.getBlockTop(this.currentBlock));
    }

    /**
     * Move player 1 block down
     */
    private void moveDown() {
        this.moveToBlock(Map.getBlockDown(this.currentBlock));
    }

    /**
     * Move player 1 block left
     */
    private void moveLeft() {
        this.moveToBlock(Map.getBlockLeft(this.currentBlock));
    }

    /**
     * Move player 1 block right
     */
    private void moveRight() {
        this.moveToBlock(Map.getBlockRight(this.currentBlock));
    }
}
