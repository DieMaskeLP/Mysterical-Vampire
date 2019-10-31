package de.nexxus.vampire.gamestate;

//Created by MrKompetnz on 10/21/19

import de.nexxus.vampire.countdown.Countdown;
import de.nexxus.vampire.main.Main;
import de.nexxus.vampire.manager.Manager;
import de.nexxus.vampire.utils.LocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.io.File;

public class EndingState extends GameState {

    @Override
    public void start() {
        System.out.println("EndingState started!");


        for (Player player : Bukkit.getServer().getOnlinePlayers()){
            player.setMaxHealth(20);
            player.setHealth(player.getMaxHealth());
            player.setGameMode(GameMode.SURVIVAL);
            player.setPlayerListName(player.getName());
            player.setAllowFlight(false);
            if (Manager.disguiseAPI.isDisguised(player)) Manager.disguiseAPI.undisguise(player);
            for (PotionEffect potionEffect : player.getActivePotionEffects()){
                player.removePotionEffect(potionEffect.getType());
            }
            LocationUtil locationUtil = new LocationUtil("End");
            if(locationUtil.loadLocation() != null) {
                player.teleport(locationUtil.loadLocation());
            } else
                Bukkit.getConsoleSender().sendMessage("§cDie End-Location wurde noch nicht gesetzt!");
        }
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
            @Override
            public void run() {
                Main.getManager().getEndingCountdown().start();
            }
        }, 80);

    }

    @Override
    public void stop() {
        for (Player t : Bukkit.getServer().getOnlinePlayers()){
            t.kickPlayer("§cServer is shutting down!");
        }
        Main.getManager().getEndingCountdown().stop();
        Bukkit.getServer().reload();
    }

    @Override
    public void setSeconds(int seconds) {
        Main.getManager().getEndingCountdown().setSeconds(seconds);
    }



}
