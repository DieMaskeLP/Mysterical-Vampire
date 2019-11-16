package de.nexxus.vampire.listener;

//Created by MrKompetnz on 10/21/19

import de.nexxus.vampire.countdown.LobbyCountdown;
import de.nexxus.vampire.gamestate.GameState;
import de.nexxus.vampire.gamestate.IngameState;
import de.nexxus.vampire.main.Main;
import de.nexxus.vampire.manager.Roles;
import de.nexxus.vampire.stats.SQLStats;
import de.nexxus.vampire.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void handlePlayerQuit(PlayerQuitEvent event) {
    // Set quitmessage etc.
        Player player = event.getPlayer();
        Main.players.remove(player);
        event.setQuitMessage(Data.PREFIX + "§6" + player.getName() + " §8hat das Spiel verlassen.");
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
            if (countdown.isIdling()){
                countdown.stopIdle();
            }
            if (!countdown.isRunning()){
                countdown.start();
            }
        }

        if(Main.getManager().getGameStateManager().getCurrentGameState() instanceof IngameState) {
            if(Main.getManager().getRoleManager().getPlayerRole(player) == Roles.VAMPIRE) {
                SQLStats.addVampireDeath(Data.getUUID(player));
                Main.players.remove(player);
                Bukkit.broadcastMessage(Data.PREFIX + "§7Der §4Vampire §7hat das Spiel verlassen. Die §aSurvivor §7haben gewonnen!");
                Main.getManager().getGameStateManager().setGameState(GameState.ENDING_STATE);
                for(Player current : Main.players) {
                    SQLStats.addWin(Data.getUUID(current));
                }
            } else if(Main.getManager().getRoleManager().getPlayerRole(player) == Roles.SURVIVOR) {
                for(Player current : Main.players) {
                    if(Main.getManager().getRoleManager().getPlayerRole(current) != Roles.SURVIVOR) {
                        Bukkit.broadcastMessage(Data.PREFIX + "§7Ein §aSurvivor §7hat das Spiel verlassen. Der §4Vampire §7hat gewonnen!");
                        Main.getManager().getGameStateManager().setGameState(GameState.ENDING_STATE);
                        SQLStats.addWin(Data.getUUID(current));
                    }
                }
            }
        }

    }
}
