package com.larswaechter.players;

import com.larswaechter.map.AbstractBlock;

/**
 * https://en.wikipedia.org/wiki/Ghosts_(Pac-Man)
 */

public abstract class AbstractGhost extends AbstractPlayer {
    public boolean hasCaught(AbstractBlock block) {
        return this.getCurrentBlock().equals(block);
    }
}