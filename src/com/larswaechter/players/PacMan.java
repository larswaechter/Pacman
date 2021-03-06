package com.larswaechter.players;

import java.util.HashMap;

import com.larswaechter.Game;
import processing.core.PGraphics;

import com.larswaechter.Timer;
import com.larswaechter.Utility;
import com.larswaechter.items.*;
import com.larswaechter.map.*;

public class PacMan extends AbstractPlayer {
    private int appleCounter = 0;
    private int pointCounter = 0;
    private int pointMultiplicator = 1;

    private boolean hasShield = false;

    private HashMap<ItemTypes, Timer> timers = new HashMap<ItemTypes, Timer>();

    public PacMan(AbstractBlock spawnBlock) {
        super(spawnBlock);
        this.setColor(Utility.colorYellow);
    }

    public int getPointCounter() {
        return this.pointCounter;
    }

    public int getAppleCounter() {
        return this.appleCounter;
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
        if (Game.frameCount % this.getSpeed() == 0) {
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
    public void checkItemTimers(PGraphics g) {
        Timer shieldTimer = this.timers.get(ItemTypes.Shield);
        Timer multiplicatorTimer = this.timers.get(ItemTypes.Multiplicator);

        // Shield timer
        if (shieldTimer != null) {
            shieldTimer.draw(g, 250, 30);

            if (shieldTimer.tickAndVerify()) {
                this.hasShield = false;
                this.timers.remove(ItemTypes.Shield);
            }
        }

        // PointMultiplicator timer
        if (multiplicatorTimer != null) {
            multiplicatorTimer.draw(g, 320, 30);

            if (multiplicatorTimer.tickAndVerify()) {
                this.pointMultiplicator = 1;
                this.timers.remove(ItemTypes.Multiplicator);
            }
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
                    this.appleCounter++;
                    this.pointCounter += this.pointMultiplicator;
                    break;
                case Multiplicator:
                    this.pointMultiplicator = ((MultiplicatorItem) this.getCurrentBlock().getItem()).getMultiplicator();
                    this.setItemTimer(ItemTypes.Multiplicator);
                    break;
                case Shield:
                    this.hasShield = true;
                    this.setItemTimer(ItemTypes.Shield);
                    break;
            }
            this.getCurrentBlock().removeItem();
        }

        // Beam
        if (this.getCurrentBlock().getType() == BlockTypes.Beam) {
            this.moveToBlock(Map.getRandomBlockExcludingBlock(this.getCurrentBlock()));
        }
    }

    private void setItemTimer(ItemTypes type) {
        switch (type) {
            case Multiplicator:
                this.timers.put(ItemTypes.Multiplicator, new Timer(MultiplicatorItem.ttl));
                break;
            case Shield:
                this.timers.put(ItemTypes.Shield, new Timer(ShieldItem.ttl));
                break;
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
