package com.larswaechter;

import processing.core.PApplet;
import processing.core.PShape;
import processing.data.JSONArray;

public class Game extends PApplet {
    static int width = 600;
    static int height = 600;

    private Menu menu;
    private Map map;
    private PacMan pacMan;
    private Ghost ghost1;

    private boolean showMenu = true;
    private boolean isRunning = false;

    void run() {
        this.runSketch(new String[]{""}, this);
    }

    @Override
    public void settings() {
        size(Game.width, Game.height);
    }

    @Override
    public void setup() {
        background(0);
        frameRate(15);

        this.menu = new Menu(this.loadPacManShape());

        // Generate map
        this.map = new Map(this.loadRandomMap());

        // Create player
        this.pacMan = new PacMan(this.map.getRandomBlock());
        this.ghost1 = new Ghost(this.map.getRandomBlock());
    }

    @Override
    public void draw() {
        this.clear();

        // Start Menu
        if (this.showMenu) {
            this.menu.draw(this.g);
            if (this.keyPressed && this.key == ' ') {
                this.showMenu = false;
                this.isRunning = true;
            }

            // Game
        } else if (this.isRunning) {
            this.loop();
            this.map.draw(this.g);
            this.pacMan.draw(this.g);
            this.ghost1.draw(this.g);

            this.fill(0xFFFFFFFF);
            this.textSize(14);
            this.text("Points: " + this.pacMan.pointCounter, 20, 30);

            if (this.pacMan.getCurrentBlock() == this.ghost1.getCurrentBlock()) {
                this.isRunning = false;
            }

            if (this.keyPressed) {
                this.pacMan.move(this.keyCode);
            }

            // Game Over
        } else {
            this.menu.drawGameOver(this.g);
            this.noLoop();
        }
    }

    /**
     * Load PacMan SVG shape
     *
     * @return SVG shape
     */
    private PShape loadPacManShape() {
        return this.loadShape("res/PacMan.svg");
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
