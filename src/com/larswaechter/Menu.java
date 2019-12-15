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
     * @param g Processing graphic
     */
    void draw(PGraphics g) {
        g.shape(this.img, (Game.width - this.imgWidth) / 2, 150, this.imgWidth, this.imgHeight);
        g.textSize(16);
        g.text("Press space to start!", 230, 400);
    }

    /**
     * Draw Menu
     * @param g Processing graphic
     */
    void drawGameOver(PGraphics g) {
        g.textSize(16);
        g.text("GAME OVER", 260, 300);
    }
}
