package com.larswaechter.players;

import com.larswaechter.Utility;

/**
 * Cyan Ghost
 */

public class Inky extends AbstractGhost {
    public Inky() {
        super();
        this.setColor(Utility.colorGhostCyan);
        this.setSpeed(2);
    }

    /**
     * Inky moves randomly
     */
    public void move() {
    }
}
