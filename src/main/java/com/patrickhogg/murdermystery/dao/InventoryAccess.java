package com.patrickhogg.murdermystery.dao;

import com.patrickhogg.murdermystery.model.Inventory;
import com.patrickhogg.murdermystery.model.Item;

/**
 * @author Patrick Hogg
 */
public interface InventoryAccess {

    Inventory getInventory();

    Item getInventoryItem(Item item);

    Item getInventoryItemByName(String itemName);

    void addItem(Item item);

    void addItem(String itemName);

    void removeItem(Item item);

    void removeItem(String itemName);

    boolean hasItem(Item item);

    boolean hasItem(String itemName);
}
