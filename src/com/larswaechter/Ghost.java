package com.larswaechter;

class Ghost extends PlayerAbs {
    static int[] colors = {0xFFFF0000, 0xFFFFB8FF, 0xFF00FFFF, 0xFFFFB852};

    Ghost(Block spawnBlock) {
        super(spawnBlock);
        this.color = this.getRandomColor();
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
