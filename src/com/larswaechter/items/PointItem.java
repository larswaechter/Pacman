package com.larswaechter.items;

import com.larswaechter.Utility;

public class PointItem extends AbstractItem {
    public PointItem(float x, float y) {
        super(ItemTypes.Point, x, y);
        this.setColor(Utility.colorOrange);
    }
}
