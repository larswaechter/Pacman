package com.larswaechter;

import processing.core.PGraphics;

class Item extends ItemAbs {
    Item(float x, float y) {
        super(x, y);
        this.color = 0xFFff7142;
    }

    void draw(PGraphics g) {
        g.fill(this.color);
        g.ellipse(this.x, this.y, radius * 2, radius * 2);
    }

}
