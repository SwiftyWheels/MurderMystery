package com.patrickhogg.murdermystery.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author Patrick Hogg
 */
public class Inventory implements Serializable {
    private List<Item> itemList = new LinkedList<>();

    public Inventory() {
        // empty no args constructor
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Inventory inventory)) {
            return false;
        }
        return Objects.equals(getItemList(), inventory.getItemList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItemList());
    }

    @Override
    public String toString() {
        return "Inventory{" + "itemList=" + itemList + '}';
    }
}
