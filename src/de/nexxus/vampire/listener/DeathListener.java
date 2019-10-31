package de.nexxus.vampire.listener;

import de.nexxus.vampire.gamestate.GameState;
import de.nexxus.vampire.main.Main;
import de.nexxus.vampire.manager.Manager;
import de.nexxus.vampire.manager.RoleManager;
import de.nexxus.vampire.manager.Roles;
import de.nexxus.vampire.utils.Data;
import de.nexxus.vampire.utils.LocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import javax.swing.*;

public class DeathListener implements Listener {

    @EventHandler
    public void onKill(PlayerDeathEvent e){
        e.setDeathMessage("");
        if (Main.getManager().getGameStateManager().isCurrentGameState(GameState.INGAME_STATE)){
            Manager manager = Main.getManager();
            Player p = e.getEntity();
            Player killer = e.getEntity().getKiller();
            LocationUtil util = new LocationUtil("Death");
            if (util.loadLocation() != null){
                p.teleport(util.loadLocation());
            } else Bukkit.getConsoleSender().sendMessage("§cDer Death-Spawn wurde noch nicht gesetzt!");
            p.setGameMode(GameMode.SPECTATOR);
            RoleManager roleManager = manager.getRoleManager();
            if (roleManager.getPlayerRole(killer) == Roles.VAMPIRE){
                if (roleManager.getPlayerRole(p) == Roles.SURVIVOR){
                    roleManager.setPlayerRole(p, Roles.SPECTATOR);
                    p.setPlayerListName("");
                    Bukkit.broadcastMessage(Data.PREFIX + "§cDer §4Vampire §chat §a" + p.getName() + " §cgetötet!");
                    boolean allSurvivorsDead = true;
                    for (Player t : Bukkit.getServer().getOnlinePlayers()){
                        if (Main.getManager().getRoleManager().getPlayerRole(t) == Roles.SURVIVOR){
                            allSurvivorsDead = false;
                        }
                    }
                    if (allSurvivorsDead){
                        Bukkit.broadcastMessage(Data.PREFIX + "§aDer §4Vampire §ahat gewonnen!");
                        Main.getManager().getGameStateManager().setGameState(GameState.ENDING_STATE);
                    }
                }
            }
            if (roleManager.getPlayerRole(killer) == Roles.SURVIVOR){
                if (roleManager.getPlayerRole(p) == Roles.VAMPIRE){
                    if (killer.getItemInHand().getType() == Material.DIAMOND_SWORD){
                        Bukkit.broadcastMessage(Data.PREFIX + "§cDer §aSurvivor §6" + killer.getName() +" §chat den Vampire §4" + p.getName() + " §cgetötet!");
                        Bukkit.broadcastMessage(Data.PREFIX + "§aDie Survivor haben gewonnen!");
                        Main.getManager().getGameStateManager().setGameState(GameState.ENDING_STATE);
                    } else {
                        p.teleport(util.setPath("Vampire").loadLocation());
                    }
                }
            }

        }

    }


}
