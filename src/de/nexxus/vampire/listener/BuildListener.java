package de.nexxus.vampire.listener;

import de.nexxus.vampire.utils.Data;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BuildListener implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        e.setCancelled(!Data.builder.contains(e.getPlayer()));
    }


    @EventHandler
    public void onBreak(BlockBreakEvent e){
        e.setCancelled(!Data.builder.contains(e.getPlayer()));
    }


}
