package com.larswaechter.players;

/**
 * Pink Ghost
 */

public class Pinky extends AbstractGhost {
    public Pinky() {
        super();
        this.setColor(AbstractGhost.colors[1]);
        this.setSpeed(8);
    }

    /**
     * Pinky tries to get in front of PacMan
     */
    public void move() {}
}
