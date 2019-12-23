package com.larswaechter;

import processing.core.*;

class Menu extends PApplet {
    private PShape img;

    private int imgWidth = 150;
    private int imgHeight = 150;

    Menu(PShape pacManImg) {
        this.img = pacManImg;
    }

    /**
     * Draw Menu
     *
     * @param g Processing graphic
     */
    void draw(PGraphics g) {
        g.shape(this.img, (float) (Game.width - this.imgWidth) / 2, 150, this.imgWidth, this.imgHeight);
        g.textSize(16);
        g.text("Press space to start!", 230, 400);
    }

    /**
     * Draw Game over
     *
     * @param g Processing graphic
     */
    void drawGameOver(PGraphics g, int pointCounter) {
        g.fill(Utility.colorGhostRed);
        g.textSize(16);
        g.text("GAME OVER! Points: " + pointCounter, 225, 300);
    }

    /**
     * Draw Game won
     *
     * @param g Processing graphic
     */
    void drawGameWon(PGraphics g, int pointCounter) {
        g.fill(Utility.colorGreen);
        g.textSize(16);
        g.text("GAME WON! Points: " + pointCounter, 225, 300);
    }
}
