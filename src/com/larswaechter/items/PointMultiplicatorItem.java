package com.larswaechter.items;

import com.larswaechter.Utility;

public class PointMultiplicatorItem extends AbstractItem {
    // Time to live in seconds
    public static final int ttl = 5;

    private int multiplicator;

    public PointMultiplicatorItem(float x, float y, int multiplicator) {
        super(ItemTypes.PointMultiplicator, x, y);
        this.setColor(Utility.colorGreen);
        this.multiplicator = multiplicator;
    }

    public int getMultiplicator() {
        return this.multiplicator;
    }
}
