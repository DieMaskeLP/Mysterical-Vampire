package de.nexxus.vampire.listener;

//Created by MrKompetnz on 10/21/19

import de.nexxus.vampire.countdown.LobbyCountdown;
import de.nexxus.vampire.gamestate.CanSetToEndingState;
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
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Team;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void handlePlayerQuit(PlayerQuitEvent event) {
    // Set quitmessage etc.
        Player player = event.getPlayer();
        event.setQuitMessage(Data.PREFIX + "§6" + player.getName() + " §8hat das Spiel verlassen.");
        TeamManager.allPlayers.remove(player);


        LobbyCountdown countdown = Main.getManager().getLobbyCountdown();
        if(Bukkit.getOnlinePlayers().size() < Data.MIN_PLAYERS) {
            if(countdown.isRunning()) {
                countdown.stop();
            }
            if (!countdown.isIdling()){
                countdown.startIdle();
            }
        }

        if (Bukkit.getOnlinePlayers().size() >= Data.MIN_PLAYERS){
            if (!countdown.isRunning()){
                if (countdown.isIdling()){
                    countdown.stopIdle();
                }
                countdown.start();
            }
        }

    }
}
