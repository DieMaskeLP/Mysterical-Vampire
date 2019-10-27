package de.nexxus.vampire.listener;

import de.nexxus.vampire.countdown.LobbyCountdown;
import de.nexxus.vampire.gamestate.GameState;
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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Manager manager = Main.getManager();
        Player player = e.getPlayer();
        for (PotionEffect potionEffect : player.getActivePotionEffects()){
            player.removePotionEffect(potionEffect.getType());
        }

        if (manager.getGameStateManager().isCurrentGameState(GameState.INGAME_STATE)){
            player.setGameMode(GameMode.SPECTATOR);
            player.sendMessage(Data.PREFIX + "§7Du bist nun ein Zuschauer");
        } else {
            if (manager.getGameStateManager().isCurrentGameState(GameState.ENDING_STATE)){
                player.kickPlayer("§cDas Spiel endet gerade!");
            } else {
                if (manager.getGameStateManager().isCurrentGameState(GameState.LOBBY_STATE)){
                    e.setJoinMessage(Data.PREFIX + "§6" + e.getPlayer().getName() + " §8ist dem Spiel beigetreten.");

                    /* Teleport the player to the lobby spawn
                    *  Edit: DONE
                    */
                    Main.getManager().getRoleManager().players.add(e.getPlayer());
                    LocationUtil locationUtil = new LocationUtil("Lobby");
                    if(locationUtil.loadLocation() != null) {
                        player.teleport(locationUtil.loadLocation());
                    } else
                        Bukkit.getConsoleSender().sendMessage("§cDie Lobby-Location wurde noch nicht gesetzt!");
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
                         if (!countdown.isRunning()){
                             countdown.start();
                         }
                        }
                    }
                }
            }
        }
    }
}
