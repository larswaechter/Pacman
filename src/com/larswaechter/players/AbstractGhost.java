package com.larswaechter.players;

import com.larswaechter.map.AbstractBlock;
import com.larswaechter.map.Map;

/**
 * https://en.wikipedia.org/wiki/Ghosts_(Pac-Man)
 */

public abstract class AbstractGhost extends AbstractPlayer {
    public boolean hasCaught(AbstractBlock block) {
        return this.getCurrentBlock().equals(block);
    }

    /**
     * Spawn on a random block with a distance of at least 10 blocks
     *
     * @param pacManPosition Current PacMan position
     */
    public void spawn(AbstractBlock pacManPosition) {
        this.moveToBlock(Map.getRandomBlockExcludingBlock(pacManPosition));
    }
}