package com.patrickhogg.murdermystery.controllers;

import com.patrickhogg.murdermystery.model.Inventory;
import com.patrickhogg.murdermystery.model.Player;
import com.patrickhogg.murdermystery.service.InventoryAccessServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author Patrick Hogg
 */
@RestController
@RequestMapping("/api/inventory")
public class RESTInventoryAccessController {

    @GetMapping("/getInventoryItems")
    public Inventory getInventoryItems(HttpSession session) {
        Player player = (Player) session.getAttribute("player");
        InventoryAccessServiceImpl inventoryAccessService
                = player.getInventoryAccessService();
        return inventoryAccessService.getInventory();
    }

    @GetMapping("/addItem/{itemName}")
    public void addItem(@PathVariable String itemName, HttpSession session) {
        Player player = (Player) session.getAttribute("player");
        InventoryAccessServiceImpl inventoryAccessService
                = player.getInventoryAccessService();
        inventoryAccessService.addItem(itemName);
    }

    @GetMapping("/removeItem/{itemName}")
    public void removeItem(@PathVariable String itemName, HttpSession session) {
        Player player = (Player) session.getAttribute("player");
        InventoryAccessServiceImpl inventoryAccessService
                = player.getInventoryAccessService();
        System.out.println("Removing item: " + itemName);
        inventoryAccessService.removeItem(itemName);
    }

    @GetMapping("/hasItem/{itemName}")
    @ResponseBody
    public boolean hasItem(@PathVariable String itemName, HttpSession session) {
        Player player = (Player) session.getAttribute("player");
        InventoryAccessServiceImpl inventoryAccessService
                = player.getInventoryAccessService();
        return inventoryAccessService.hasItem(itemName);
    }

}
