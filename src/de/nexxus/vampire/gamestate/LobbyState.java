package de.nexxus.vampire.gamestate;

//Created by MrKompetnz on 10/21/19

import de.nexxus.vampire.countdown.LobbyCountdown;
import de.nexxus.vampire.main.Main;
import de.nexxus.vampire.manager.Manager;
import de.nexxus.vampire.utils.LocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

public class LobbyState extends GameState {

    @Override
    public void start() {
        System.out.println("LobbyState started!");
        LocationUtil util = new LocationUtil();
        util.setPath("Lobby");
        if (util.loadLocation() != null){
            World world = Bukkit.getServer().getWorld(util.loadLocation().getWorld().getName());
            world.setMonsterSpawnLimit(0);
            for (LivingEntity entity : world.getLivingEntities()){
                if (entity.getType() != EntityType.PLAYER){
                    entity.setHealth(0);
                }
            }
        }
    }

    @Override
    public void stop() {
        Main.getManager().getLobbyCountdown().stop();
        Main.getManager().getLobbyCountdown().stopIdle();
    }

    @Override
    public void setSeconds(int seconds) {
        Main.getManager().getLobbyCountdown().setSeconds(seconds);
    }
}


