package de.nexxus.vampire.listener;

import de.nexxus.vampire.gamestate.GameState;
import de.nexxus.vampire.gamestate.IngameState;
import de.nexxus.vampire.main.Main;
import de.nexxus.vampire.manager.RoleManager;
import de.nexxus.vampire.manager.Roles;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

//Created by Frederic | DieMaskeLP at 05.11.2019, 17:25
public class ChatListener implements Listener {

    @EventHandler
    public void onChatMessage(PlayerChatEvent e){
        RoleManager roleManager = Main.getManager().getRoleManager();
        if (Main.getManager().getGameStateManager().isCurrentGameState(GameState.INGAME_STATE)){
            e.setCancelled(true);
            if (roleManager.getPlayerRole(e.getPlayer()) == Roles.SPECTATOR){
                for (Player target : Bukkit.getServer().getOnlinePlayers()){
                    if (roleManager.getPlayerRole(target) == Roles.SPECTATOR){
                        target.sendMessage(roleManager.getPlayerRole(e.getPlayer()).getChatColor() + "[" + roleManager.getPlayerRole(e.getPlayer()).getName() + "] " + e.getPlayer().getName() + "§7: " + e.getMessage());
                    }
                }
            } else {
                Bukkit.broadcastMessage(roleManager.getPlayerRole(e.getPlayer()).getChatColor() + "[" + roleManager.getPlayerRole(e.getPlayer()).getName() + "] " + e.getPlayer().getName() + "§7: " + e.getMessage());
            }
        } else {
            e.setCancelled(true);
            Bukkit.broadcastMessage("§7" + e.getPlayer().getName() + ": §8" + e.getMessage());
        }
    }

}
