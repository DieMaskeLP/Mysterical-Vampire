package de.nexxus.vampire.listener;

import de.nexxus.vampire.utils.Data;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        e.setCancelled(!Data.builder.contains((Player) e.getWhoClicked()));
    }
}
