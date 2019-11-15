package de.nexxus.vampire.listener;

import de.nexxus.vampire.utils.Data;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        e.setCancelled(!Data.builder.contains(e.getWhoClicked()));
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent e){
        e.setCancelled(!Data.builder.contains(e.getPlayer()));
    }
}
