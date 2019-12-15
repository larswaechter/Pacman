package com.larswaechter;

import com.larswaechter.map.Map;
import com.larswaechter.players.*;

import processing.core.PApplet;
import processing.core.PShape;
import processing.data.JSONArray;

public class Game extends PApplet {
    static int width = 600;
    static int height = 600;

    private Menu menu;
    private Map map;
    private PacMan pacMan;
    private Blinky blinky;

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
        frameRate(20);

        this.menu = new Menu(this.loadPacManShape());

        // Generate map
        this.map = new Map(this.loadRandomMap());

        // Create player
        this.pacMan = new PacMan(Map.getRandomBlock());
        this.blinky = new Blinky();
        this.blinky.spawn(this.pacMan.getCurrentBlock());
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

            AbstractPlayer.frameCounter++;

            this.pacMan.draw(this.g);

            this.blinky.move(this.pacMan.getCurrentBlock());
            this.blinky.draw(this.g);

            this.fill(0xFFFFFFFF);
            this.textSize(14);
            this.text("Points: " + this.pacMan.getPointCounter(), 20, 30);

            if (this.pacMan.getCurrentBlock() == this.blinky.getCurrentBlock()) {
                this.isRunning = false;
            }

            if (this.keyPressed) {
                this.pacMan.move(this.keyCode);
            }

            // Game Over
        } else {
            this.menu.drawGameOver(this.g, this.pacMan.getPointCounter());
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
        return this.loadJSONArray(Map.maps[(int) Utility.getRandomNumber(0, Map.maps.length)]);
    }
}
