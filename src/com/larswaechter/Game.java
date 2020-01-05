package com.larswaechter;

import processing.core.PApplet;
import processing.core.PShape;
import processing.data.JSONArray;

import com.larswaechter.map.Map;
import com.larswaechter.players.*;

enum GameStates {
    Menu, Play, GameOver, GameWon
}

public class Game extends PApplet {
    public static int frameCount = 0;
    public static int framesPerSecond = 8;

    static int width = 600;
    static int height = 600;

    private Menu menu;
    private Map map;
    private PacMan pacMan;
    private Blinky blinky;

    private GameStates state;

    void run() {
        Game.runSketch(new String[]{""}, this);
    }

    @Override
    public void settings() {
        size(Game.width, Game.height);
    }

    @Override
    public void setup() {
        this.background(0);
        this.frameRate(Game.framesPerSecond);

        this.menu = new Menu(this.loadPacManShape());
        this.state = GameStates.Menu;
    }

    @Override
    public void draw() {
        this.clear();

        switch (this.state) {
            case Menu:
                this.drawMenu();
                break;
            case Play:
                this.drawGame();
                break;
            case GameOver:
                this.drawGameOver();
                break;
            case GameWon:
                this.drawGameWon();
                break;
        }
    }

    /**
     * Draw Menu (start) screen
     */
    private void drawMenu() {
        this.menu.draw(this.g);
        if (this.keyPressed && this.key == ' ') {
            this.initGame();
        }
    }

    /**
     * Init Game
     */
    private void initGame() {
        this.state = GameStates.Play;

        // Generate map
        this.map = new Map(this.loadRandomMap());

        // Create player
        this.pacMan = new PacMan(Map.getRandomBlock());

        // Create ghosts
        this.blinky = new Blinky();
        this.blinky.spawn(this.pacMan.getCurrentBlock());
    }

    /**
     * Draw Game screen
     */
    private void drawGame() {
        Game.frameCount++;

        this.drawPointStats();
        this.checkForFinish();

        Map.generateRandomItems(Game.frameCount);
        this.map.draw(this.g);

        this.pacMan.checkItemTimers();


        if (this.keyPressed) {
            this.pacMan.move(this.keyCode);
        }

        this.blinky.move(this.pacMan.getCurrentBlock());

        this.pacMan.draw(this.g);
        this.blinky.draw(this.g);
    }

    /**
     * Draw Game PointStats
     */
    private void drawPointStats() {
        this.fill(Utility.colorWhite);
        this.textSize(14);
        this.text("Points: " + this.pacMan.getPointCounter() + " / " + Map.pointsToCollect, 20, 30);
    }

    /*
     * Finishing scenarios:
     *  - Get caught
     *  - Collected all points
     */
    private void checkForFinish() {
        if (!this.pacMan.getHasShield() && this.blinky.hasCaught(this.pacMan.getCurrentBlock())) {
            this.state = GameStates.GameOver;
        } else if (this.pacMan.getPointCounter() == Map.pointsToCollect) {
            this.state = GameStates.GameWon;
        }
    }

    /**
     * Draw Game Over screen
     */
    private void drawGameOver() {
        this.menu.drawGameOver(this.g, this.pacMan.getPointCounter());
        if (this.keyPressed && this.key == ' ') {
            this.initGame();
        }
    }

    /**
     * Draw Game won screen
     */
    private void drawGameWon() {
        this.menu.drawGameWon(this.g, this.pacMan.getPointCounter());
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
