package com.larswaechter.players;

import com.larswaechter.map.AbstractBlock;

/**
 * https://en.wikipedia.org/wiki/Ghosts_(Pac-Man)
 */

public abstract class AbstractGhost extends AbstractPlayer {
    // Red, Pink, Cyan, Orange
    static final int[] colors = {0xFFFF0000, 0xFFFFB8FF, 0xFF00FFFF, 0xFFFFB852};

    public boolean hasCaught(AbstractBlock block) {
        return this.getCurrentBlock().equals(block);
    }
}