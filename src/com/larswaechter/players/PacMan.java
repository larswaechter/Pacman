package com.larswaechter.players;

import com.larswaechter.Timer;
import com.larswaechter.Utility;
import com.larswaechter.items.*;
import com.larswaechter.map.*;
import processing.core.PGraphics;

import java.util.HashMap;

public class PacMan extends AbstractPlayer {
    private int pointCounter = 0;
    private int pointMultiplicator = 1;
    private boolean hasShield = false;

    private HashMap<ItemTypes, Timer> timers = new HashMap<ItemTypes, Timer>();

    public PacMan(AbstractBlock spawnBlock) {
        super(spawnBlock);
        this.setColor(Utility.colorYellow);
    }

    public int getPointCounter() {
        return pointCounter;
    }

    public boolean getHasShield() {
        return this.hasShield;
    }

    /**
     * Move PacMan 1 block to given direction
     *
     * @param direction Direction to move to
     */
    public void move(int direction) {
        switch (direction) {
            // UP
            case 38:
                this.moveUp();
                this.movePostHandler();
                break;

            // DOWN
            case 40:
                this.moveDown();
                this.movePostHandler();
                break;

            // LEFT
            case 37:
                this.moveLeft();
                this.movePostHandler();
                break;

            // RIGHT
            case 39:
                this.moveRight();
                this.movePostHandler();
                break;
        }
    }

    /**
     * Draw PacMan
     *
     * @param g Processing graphic
     */
    @Override
    public void draw(PGraphics g) {
        g.fill(this.hasShield ? Utility.colorCyan : Utility.colorYellow);
        g.ellipse(this.getX(), this.getY(), AbstractPlayer.diameter, AbstractPlayer.diameter);
    }

    /**
     * Remove expired timers from PacMan
     */
    public void checkTimers() {
        // Shield timer
        if (this.timers.get(ItemTypes.Shield) != null && this.timers.get(ItemTypes.Shield).tickAndVerify()) {
            this.hasShield = false;
            this.timers.remove(ItemTypes.Shield);
        }

        // PointMultiplicator timer
        if (this.timers.get(ItemTypes.PointMultiplicator) != null && this.timers.get(ItemTypes.PointMultiplicator).tickAndVerify()) {
            this.pointMultiplicator = 1;
            this.timers.remove(ItemTypes.PointMultiplicator);
        }
    }

    /**
     * Called after move event
     */
    private void movePostHandler() {
        // Block has an item
        if (this.getCurrentBlock().getItem() != null) {
            switch (this.getCurrentBlock().getItem().getType()) {
                case Point:
                    this.pointCounter += this.pointMultiplicator;
                    break;
                case PointMultiplicator:
                    this.pointMultiplicator = ((PointMultiplicatorItem) this.getCurrentBlock().getItem()).getMultiplicator();
                    this.timers.put(ItemTypes.PointMultiplicator, new Timer(PointMultiplicatorItem.ttl));
                    break;
                case Shield:
                    this.hasShield = true;
                    this.timers.put(ItemTypes.Shield, new Timer(ShieldItem.ttl));
                    break;
            }
            this.getCurrentBlock().removeItem();
        }

        // Beam
        if (this.getCurrentBlock().getType() == BlockTypes.Beam) {
            this.moveToBlock(Map.getRandomBlock());
        }
    }

    /**
     * Move PacMan 1 block up
     */
    private void moveUp() {
        this.moveToBlock(Map.getBlockTop(this.getCurrentBlock()));
    }

    /**
     * Move PacMan 1 block down
     */
    private void moveDown() {
        this.moveToBlock(Map.getBlockDown(this.getCurrentBlock()));
    }

    /**
     * Move PacMan 1 block left
     */
    private void moveLeft() {
        this.moveToBlock(Map.getBlockLeft(this.getCurrentBlock()));
    }

    /**
     * Move PacMan 1 block right
     */
    private void moveRight() {
        this.moveToBlock(Map.getBlockRight(this.getCurrentBlock()));
    }
}
