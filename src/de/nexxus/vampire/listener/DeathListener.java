package de.nexxus.vampire.listener;

import de.nexxus.vampire.gamestate.GameState;
import de.nexxus.vampire.main.Main;
import de.nexxus.vampire.manager.Manager;
import de.nexxus.vampire.manager.RoleManager;
import de.nexxus.vampire.manager.Roles;
import de.nexxus.vampire.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    @EventHandler
    public void onKill(PlayerDeathEvent e){
        e.setDeathMessage("");
        if (Main.getManager().getGameStateManager().isCurrentGameState(GameState.INGAME_STATE)){
            Manager manager = Main.getManager();
            Player p = e.getEntity();
            Player killer = e.getEntity().getKiller();
            RoleManager roleManager = manager.getRoleManager();
            if (roleManager.getPlayerRole(killer) == Roles.VAMPIRE){
                if (roleManager.getPlayerRole(p) == Roles.SURVIVOR){
                    Bukkit.broadcastMessage(Data.PREFIX + "§cDer §4Vampire §chat §a" + p.getName() + " §cgetötet!");
                }
            }
            if (roleManager.getPlayerRole(killer) == Roles.SURVIVOR){
                if (roleManager.getPlayerRole(p) == Roles.VAMPIRE){
                    Bukkit.broadcastMessage(Data.PREFIX + "§cDer §aSurvivor §6" + killer.getName() +" §chat §c" + p.getName() + " §cgetötet!");
                }
            }

        }

    }


}
