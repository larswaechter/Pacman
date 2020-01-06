package com.larswaechter.items;

import com.larswaechter.Utility;

public class MultiplicatorItem extends AbstractItem {
    // Time to live in seconds
    public static final int ttl = 5;

    private int multiplicator;

    public MultiplicatorItem(float x, float y, int multiplicator) {
        super(ItemTypes.Multiplicator, x, y);
        this.setColor(Utility.colorOrange);
        this.multiplicator = multiplicator;
    }

    public int getMultiplicator() {
        return this.multiplicator;
    }
}
