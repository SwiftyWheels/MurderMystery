package com.patrickhogg.murdermystery.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Patrick Hogg
 */
public class Item implements Serializable {
    String itemName;

    public Item(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Item item)) {
            return false;
        }
        return Objects.equals(getItemName(), item.getItemName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItemName());
    }

    @Override
    public String toString() {
        return "Item{" + "itemName='" + itemName + '\'' + '}';
    }
}
