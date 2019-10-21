package de.nexxus.vampire.listener;

import de.nexxus.vampire.countdown.LobbyCountdown;
import de.nexxus.vampire.gamestate.GameState;
import de.nexxus.vampire.gamestate.GameStateManager;
import de.nexxus.vampire.main.Main;
import de.nexxus.vampire.manager.*;
import de.nexxus.vampire.utils.Data;
import de.nexxus.vampire.utils.LocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Manager manager = Main.getManager();
        Player player = e.getPlayer();
        if (manager.getGameStateManager().isCurrentGameState(GameState.INGAME_STATE)){
            TeamManager teamManager = manager.getTeamManager(e.getPlayer());
            teamManager.setTeam(Teams.SPECTATOR);
            player.setGameMode(GameMode.SPECTATOR);
            player.sendMessage("§7Du bist nun ein Zuschauer");
        } else {
            if (manager.getGameStateManager().isCurrentGameState(GameState.ENDING_STATE)){
                player.kickPlayer("§cDas Spiel endet gerade!");
            } else {
                if (manager.getGameStateManager().isCurrentGameState(GameState.LOBBY_STATE)){
                    /* Teleport the player to the lobby spawn
                    *  Edit: DONE
                    */
                    LocationUtil locationUtil = new LocationUtil("Lobby");
                    if(locationUtil.loadLocation() != null) {
                        player.teleport(locationUtil.loadLocation());
                    } else
                        Bukkit.getConsoleSender().sendMessage("§cDie Lobby-Location wurde noch nicht gesetzt!");
                }
            }
        }

        LobbyCountdown countdown = new LobbyCountdown(manager.getGameStateManager());

        if(Bukkit.getOnlinePlayers().size() >= Data.MIN_PLAYERS) {
            if(!countdown.isRunning()) {
                countdown.stopIdle();
                countdown.start();
            }
        }
    }
}
