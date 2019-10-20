package de.nexxus.vampire.listener;

import de.nexxus.vampire.main.Main;
import de.nexxus.vampire.manager.GameStates;
import de.nexxus.vampire.manager.Manager;
import de.nexxus.vampire.manager.TeamManager;
import de.nexxus.vampire.manager.Teams;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventListener implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Manager manager = Main.getManager();
        if (manager.getGameStateManager().isCurrentGameState(GameStates.INGAME)){
            TeamManager teamManager = manager.getTeamManager(e.getPlayer());
            teamManager.setTeam(Teams.Spectator);
            e.getPlayer().setGameMode(GameMode.SPECTATOR);
            e.getPlayer().sendMessage("§cDu bist nun ein Zuschauer");
        } else {
            if (manager.getGameStateManager().isCurrentGameState(GameStates.END)){
                e.getPlayer().kickPlayer("§cDas Spiel ist gerade beim Beenden");
            } else {
                if (manager.getGameStateManager().isCurrentGameState(GameStates.LOBBY)){
                    //Teleport the player to the lobby spawn


                }
            }
        }
    }


}
