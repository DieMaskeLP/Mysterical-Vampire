package de.nexxus.vampire.listener;

//Created by MrKompetnz on 10/21/19

import de.nexxus.vampire.countdown.LobbyCountdown;
import de.nexxus.vampire.main.Main;
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

    }
}
