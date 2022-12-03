package com.patrickhogg.murdermystery.dao;

import com.patrickhogg.murdermystery.model.Inventory;
import com.patrickhogg.murdermystery.model.Item;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Patrick Hogg
 */
public class InventoryAccessImpl implements InventoryAccess, Serializable {

    private final Inventory inventory = new Inventory();

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public Item getInventoryItem(Item item) {
        if (item != null && getInventory().getItemList().contains(item)) {
            for (Item currentItem : getInventory().getItemList()) {
                if (currentItem.equals(item)) {
                    return currentItem;
                }
            }
        }
        return null;
    }

    @Override
    public Item getInventoryItemByName(String itemName) {
        for (Item currentItem : getInventory().getItemList()) {
            if (currentItem.getItemName().equalsIgnoreCase(itemName)) {
                return currentItem;
            }
        }
        return null;
    }

    @Override
    public void addItem(Item item) {
        if (item != null && !getInventory().getItemList().contains(item)) {
            getInventory().getItemList().add(item);
        }
    }

    @Override
    public void addItem(String itemName) {
        Item item = getInventoryItemByName(itemName);
        if (item == null) {
            getInventory().getItemList().add(new Item(itemName));
        }
    }

    @Override
    public void removeItem(Item item) {
        if (item != null) {
            getInventory().getItemList().remove(item);
        }
    }

    @Override
    public void removeItem(String itemName) {
        Item itemToRemove = getInventoryItemByName(itemName);
        if (itemToRemove != null) {
            getInventory().getItemList().remove(itemToRemove);
        }
    }

    @Override
    public boolean hasItem(Item item) {
        if (item != null) {
            return getInventory().getItemList().contains(item);
        }
        return false;
    }

    @Override
    public boolean hasItem(String itemName) {
        Item item = getInventoryItemByName(itemName);
        return item != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InventoryAccessImpl that)) {
            return false;
        }
        return Objects.equals(getInventory(), that.getInventory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInventory());
    }

    @Override
    public String toString() {
        return "InventoryAccessImpl{" + "inventory=" + inventory + '}';
    }
}
