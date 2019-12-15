package com.larswaechter.players;

/**
 * https://en.wikipedia.org/wiki/Ghosts_(Pac-Man)
 */

public abstract class AbstractGhost extends AbstractPlayer {
    // Red, Pink, Cyan, Orange
    static final int[] colors = {0xFFFF0000, 0xFFFFB8FF, 0xFF00FFFF, 0xFFFFB852};
}