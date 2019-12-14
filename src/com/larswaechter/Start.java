package com.larswaechter;

import processing.core.PApplet;
import processing.data.JSONArray;

public class Start extends PApplet {
    private Map map;
    private PacMan pacMan;
    private Ghost ghost1;
    private boolean isRunning = true;

    void run() {
        this.runSketch(new String[]{""}, this);
    }

    @Override
    public void settings() {
        size(600, 600);
    }

    @Override
    public void setup() {
        background(0);
        frameRate(15);

        // Generate map
        this.map = new Map(this.loadRandomMap());

        // Create player
        this.pacMan = new PacMan(this.map.getRandomBlock());
        this.ghost1 = new Ghost(this.map.getRandomBlock());
    }

    @Override
    public void draw() {
        clear();

        if (this.isRunning) {
            this.map.draw(g);
            this.pacMan.draw(g);
            this.ghost1.draw(g);

            this.fill(0xFFFFFFFF);
            text("Points: " + this.pacMan.pointCounter, 20, 20);

            if (pacMan.getCurrentBlock() == ghost1.getCurrentBlock()) {
                this.isRunning = false;
            }

            if (keyPressed) {
                this.pacMan.move(keyCode);
            }
        } else {
            text("GAME OVER", 260, 300);
        }
    }

    /**
     * Load random JSON map from file
     *
     * @return JSON map
     */
    private JSONArray loadRandomMap() {
        return this.loadJSONArray(Map.maps[(int) random(0, Map.maps.length)]);
    }
}
