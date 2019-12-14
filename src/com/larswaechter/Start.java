package com.larswaechter;

import processing.core.PApplet;
import processing.data.JSONArray;

public class Start extends PApplet {
    private Map map;
    private PacMan pacMan;

    void run() {
        this.runSketch(new String[] { "" }, this);
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
        this.map = new Map(this.loadMap());

        // Create player
        this.pacMan = new PacMan(this.map.getRandomBlock());
    }

    @Override
    public void draw() {
        clear();

        this.map.draw(g);
        this.pacMan.draw(g);

        this.fill(0xFFFFFFFF);
        text("Points: " + this.pacMan.pointCounter, 20, 20);

        if (keyPressed) {
            this.pacMan.move(keyCode);
        }
    }

    /**
     * Load JSON map from file
     * @return JSON map
     */
    private JSONArray loadMap() {
        return this.loadJSONArray("res/map.json");
    }
}
