package com.larswaechter.players;

import com.larswaechter.Game;
import com.larswaechter.Utility;
import com.larswaechter.map.*;

/**
 * Red Ghost
 */

public class Blinky extends AbstractGhost {

    public Blinky() {
        super();
        this.setColor(Utility.colorGhostRed);
        this.setSpeed(2);
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
