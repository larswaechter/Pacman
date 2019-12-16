package com.larswaechter.map;

public class Block extends AbstractBlock {
    Block(float x, float y, int mapIdxX, int mapIdxY) {
        super(x, y, mapIdxX, mapIdxY);
        this.setPointItem();
        this.setColor(0xFFFFFFFF);
    }
}
