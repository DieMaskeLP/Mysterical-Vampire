package de.nexxus.vampire.gamestate;

//Created by MrKompetnz on 10/21/19

import de.nexxus.vampire.countdown.LobbyCountdown;
import de.nexxus.vampire.manager.Manager;
import de.nexxus.vampire.manager.RoleManager;
import de.nexxus.vampire.manager.Roles;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class IngameState extends GameState {

    @Override
    public void start() {
        LobbyCountdown countdown = Manager.getManager().getLobbyCountdown();
        if (countdown.isRunning()) countdown.stop();
        System.out.println("IngameState started!");
        Manager.getManager().getRoleManager().calculateRoles(Bukkit.getServer().getOnlinePlayers().size());
        for (Player t : Bukkit.getOnlinePlayers()){
            if (Manager.getManager().getRoleManager().getPlayerRole(t) == Roles.VAMPIRE){
                t.setDisplayName("§4" + t.getName());
                t.setPlayerListName(t.getDisplayName());
                PotionEffect potionEffect = new PotionEffect(PotionEffectType.NIGHT_VISION, 9999, 5, true, false);
                PotionEffect potionEffect2 = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 9999, 2, true, false);
                potionEffect2.apply(t);
                potionEffect.apply(t);
            } else {
                if (Manager.getManager().getRoleManager().getPlayerRole(t) == Roles.SURVIVOR){
                    t.setDisplayName("§a" + t.getName());
                    t.setPlayerListName(t.getDisplayName());
                    PotionEffect potionEffect = new PotionEffect(PotionEffectType.BLINDNESS, 9999, 2, true, false);
                    PotionEffect potionEffect2 = new PotionEffect(PotionEffectType.SLOW, 9999, 2, true, false);
                    potionEffect2.apply(t);
                    potionEffect.apply(t);
                }
            }
        }
    }

    @Override
    public void stop() {
        System.out.println("IngameState stopped!");
    }
}
