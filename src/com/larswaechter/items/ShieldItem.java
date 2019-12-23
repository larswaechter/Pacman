package com.larswaechter.items;

import com.larswaechter.Utility;

public class ShieldItem extends AbstractItem {
    // Time to live in seconds
    public static final int ttl = 5;

    public ShieldItem(float x, float y) {
        super(ItemTypes.Shield, x, y);
        this.setColor(Utility.colorCyan);
    }

    public int getTtl() {
        return ttl;
    }
}
