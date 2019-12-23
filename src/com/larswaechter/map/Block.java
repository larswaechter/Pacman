package com.larswaechter.map;

import com.larswaechter.Utility;

public class Block extends AbstractBlock {
    Block(float x, float y, int mapIdxX, int mapIdxY) {
        super(BlockTypes.Block, x, y, mapIdxX, mapIdxY);
        this.setColor(Utility.colorWhite);
    }
}
