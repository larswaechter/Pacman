package com.larswaechter.players;

import com.larswaechter.Game;
import com.larswaechter.map.*;

/**
 * Red Ghost
 */

public class Blinky extends AbstractGhost {

    public Blinky() {
        super();
        this.setColor(AbstractGhost.colors[0]);
        this.setSpeed(2);
    }

    /**
     * Spawn on a random block with a distance of at least 10 blocks
     *
     * @param pacManPosition Current PacMan position
     */
    public void spawn(AbstractBlock pacManPosition) {
        AbstractBlock randomBlock;

        do {
            randomBlock = Map.getRandomBlock();
        } while (Block.getBlockDistance(randomBlock, pacManPosition) < 10);

        this.moveToBlock(randomBlock);
    }

    /**
     * Blinky tries to get closer to given block with each call
     *
     * @param block Block to hunt
     */
    public void move(AbstractBlock block) {
        if (Game.frameCount % this.getSpeed() == 0) {
            this.moveToBlock(Map.getNextBlockToTakeToReachTarget(this.getCurrentBlock(), block));
        }

    }
}
