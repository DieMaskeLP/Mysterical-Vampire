package de.nexxus.vampire.listener;

import de.nexxus.vampire.main.Main;
import de.nexxus.vampire.manager.Manager;
import de.nexxus.vampire.manager.RoleManager;
import de.nexxus.vampire.manager.Roles;
import de.nexxus.vampire.utils.Data;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

//Created by Frederic | DieMaskeLP at 31.10.2019, 15:28
public class DamageListener implements Listener {

    @EventHandler
    public void onDmg(EntityDamageByEntityEvent e){
        if (e.getDamager().getType()== EntityType.PLAYER){
            if (e.getEntity().getType()== EntityType.PLAYER){
                Player p = (Player) e.getEntity();
                Player damager = (Player) e.getDamager();
                RoleManager manager = Main.getManager().getRoleManager();
                if (manager.getPlayerRole(p) != manager.getPlayerRole(damager)){
                    if (manager.getPlayerRole(damager) == Roles.VAMPIRE){
                        if (Manager.disguiseAPI.isDisguised(damager)){
                            e.setCancelled(true);
                            damager.sendMessage(Data.PREFIX + "§cDu kannst als Fledermaus niemanden Verletzen!");
                        }
                    }
                } else {
                    e.setCancelled(true);
                }
            }
        }
    }

}
