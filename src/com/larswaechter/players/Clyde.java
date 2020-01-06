package com.larswaechter.players;

import com.larswaechter.Utility;

/**
 * Orange ghost
 */

public class Clyde extends AbstractGhost {
    public Clyde() {
        super();
        this.setColor(Utility.colorGhostOrange);
        this.setSpeed(2);
    }

    /**
     * Clyde will chase after Pac-Man in Blinky's manner,
     * but will wander off to his home corner when he gets too close
     */
    public void move() {
    }
}
