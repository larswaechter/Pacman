package com.larswaechter.players;

import com.larswaechter.Utility;

/**
 * Pink Ghost
 */

public class Pinky extends AbstractGhost {
    public Pinky() {
        super();
        this.setColor(Utility.colorGhostPink);
        this.setSpeed(6);
    }

    /**
     * Pinky tries to get in front of PacMan
     */
    public void move() {}
}
