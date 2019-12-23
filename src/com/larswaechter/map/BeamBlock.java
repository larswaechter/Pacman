package com.larswaechter.map;

import com.larswaechter.Utility;

public class BeamBlock extends AbstractBlock {
    BeamBlock(float x, float y, int mapIdxX, int mapIdxY) {
        super(BlockTypes.Beam, x, y, mapIdxX, mapIdxY);
        this.setColor(Utility.colorBlue);
    }
}
