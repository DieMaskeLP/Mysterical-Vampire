package de.nexxus.vampire.listener;

import de.nexxus.vampire.gamestate.GameState;
import de.nexxus.vampire.gamestate.IngameState;
import de.nexxus.vampire.main.Main;
import de.nexxus.vampire.manager.RoleManager;
import de.nexxus.vampire.manager.Roles;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

//Created by Frederic | DieMaskeLP at 05.11.2019, 17:25
public class ChatListener implements Listener {

    @EventHandler
    public void onChatMessage(PlayerChatEvent e){
        RoleManager roleManager = Main.getManager().getRoleManager();
        if (Main.getManager().getGameStateManager().isCurrentGameState(GameState.INGAME_STATE)){
            e.setMessage(roleManager.getPlayerRole(e.getPlayer()).getChatColor() + "[" + roleManager.getPlayerRole(e.getPlayer()).getName() + "] " + e.getPlayer().getName() + "§8: " + e.getMessage());
        } else {
            e.setMessage("§7" + e.getPlayer().getName() + "§8: " + e.getMessage());
        }
    }

}
