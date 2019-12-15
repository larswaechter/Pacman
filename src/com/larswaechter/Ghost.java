package com.larswaechter;

class Ghost extends PlayerAbs {
    private static final int[] colors = {0xFFFF0000, 0xFFFFB8FF, 0xFF00FFFF, 0xFFFFB852};

    Ghost() {
        super();
        this.color = this.getRandomColor();
        this.speed = 8;
    }

    /**
     * Spawn on a random block with a distance of at least 10 blocks
     *
     * @param pacManPosition Current PacMan position
     */
    void spawn(Block pacManPosition) {
        Block randomBlock;

        do {
            randomBlock = Map.getRandomBlock();
        } while (Block.getBlockDistance(randomBlock, pacManPosition) < 10);

        this.moveToBlock(randomBlock);
    }

    /**
     * Move to position of PacMan
     *
     * @param pacManPosition Position of PacMan
     */
    void moveToPacMan(Block pacManPosition) {
        if (this.frameCounter % this.speed == 0) {
            this.moveToBlock(Map.getNextBlockToTakeToReachTarget(this.currentBlock, pacManPosition));
        }

    }

    /**
     * Get random ghost color
     *
     * @return Random color
     */
    private int getRandomColor() {
        return Ghost.colors[(int) random(0, Ghost.colors.length)];
    }
}
