package com.patrickhogg.murdermystery.service;

import com.patrickhogg.murdermystery.dao.InventoryAccess;
import com.patrickhogg.murdermystery.dao.InventoryAccessImpl;
import com.patrickhogg.murdermystery.model.Inventory;
import com.patrickhogg.murdermystery.model.Item;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Patrick Hogg
 */
public class InventoryAccessServiceImpl
        implements InventoryAccess, Serializable {

    private final InventoryAccessImpl inventoryAccess = new InventoryAccessImpl();

    @Override
    public Inventory getInventory() {
        return inventoryAccess.getInventory();
    }

    @Override
    public Item getInventoryItem(Item item) {
        return inventoryAccess.getInventoryItem(item);
    }

    @Override
    public Item getInventoryItemByName(String itemName) {
        return inventoryAccess.getInventoryItemByName(itemName);
    }

    @Override
    public void addItem(Item item) {
        inventoryAccess.addItem(item);
    }

    @Override
    public void addItem(String itemName) {
        inventoryAccess.addItem(itemName);
    }

    @Override
    public void removeItem(Item item) {
        inventoryAccess.removeItem(item);
    }

    @Override
    public void removeItem(String itemName) {
        inventoryAccess.removeItem(itemName);
    }

    @Override
    public boolean hasItem(Item item) {
        return inventoryAccess.hasItem(item);
    }

    @Override
    public boolean hasItem(String itemName) {
        return inventoryAccess.hasItem(itemName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InventoryAccessServiceImpl that)) {
            return false;
        }
        return Objects.equals(inventoryAccess, that.inventoryAccess);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventoryAccess);
    }

    @Override
    public String toString() {
        return "InventoryAccessServiceImpl{" + "inventoryAccess="
               + inventoryAccess + '}';
    }
}
