package de.nexxus.vampire.listener;

import de.nexxus.vampire.gamestate.GameState;
import de.nexxus.vampire.main.Main;
import de.nexxus.vampire.manager.Manager;
import de.nexxus.vampire.manager.TeamManager;
import de.nexxus.vampire.manager.Teams;
import de.nexxus.vampire.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    @EventHandler
    public void onKill(PlayerDeathEvent e){
        if (Main.getManager().getGameStateManager().isCurrentGameState(GameState.INGAME_STATE)){
            Manager manager = Manager.getManager();
            Player p = e.getEntity();
            Player killer = e.getEntity().getKiller();
            if (TeamManager.getPlayerTeam(killer) == Teams.VAMPIRE){
                Bukkit.broadcastMessage(Data.PREFIX + "§c Der §4Vampire §chat §a" + p.getName() + " §cgetötet!");
                if (TeamManager.getPlayerTeam(e.getEntity()) == Teams.VAMPIRE){
                    Bukkit.broadcastMessage(Data.PREFIX + "§cDer §4Vampire §cwurde von §a" + killer.getName() +" §cgetötet! §8Das Spiel ist vorbei.");
                    Main.getManager().getGameStateManager().setGameState(GameState.ENDING_STATE);
                }

                if (TeamManager.getPlayerTeam(e.getEntity()) == Teams.SURVIVOR){
                    TeamManager.survivors.remove(e.getEntity());
                    if (TeamManager.getPlayersByTeam(Teams.SURVIVOR).size()==0){
                        Bukkit.broadcastMessage(Data.PREFIX + "§cEs gibt keine §aSurvivor §cmehr! §8Das Spiel ist vorbei.");
                        Main.getManager().getGameStateManager().setGameState(GameState.ENDING_STATE);
                    }
                }
            }
        }

    }


}
