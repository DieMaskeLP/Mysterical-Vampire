package de.nexxus.vampire.listener;

import com.connorlinfoot.titleapi.TitleAPI;
import de.nexxus.vampire.gamestate.GameState;
import de.nexxus.vampire.main.Main;
import de.nexxus.vampire.manager.Manager;
import de.nexxus.vampire.manager.RoleManager;
import de.nexxus.vampire.manager.Roles;
import de.nexxus.vampire.utils.Data;
import de.nexxus.vampire.utils.LocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.swing.*;

public class DeathListener implements Listener {

    private static int id;

    private static boolean isRunning = false;
    public static void end(Player target){
        if (isRunning){
            isRunning = false;
            Bukkit.getScheduler().cancelTask(id);
            target.removePotionEffect(PotionEffectType.BLINDNESS);
        }
    }

    @EventHandler
    public void onKill(PlayerDeathEvent e){
        e.setDeathMessage("");
        if (Main.getManager().getGameStateManager().isCurrentGameState(GameState.INGAME_STATE)){
            Manager manager = Main.getManager();
            Player p = e.getEntity();
            Player killer = e.getEntity().getKiller();
            LocationUtil util = new LocationUtil("Death");
            if (util.loadLocation() != null){
                p.teleport(util.loadLocation());
            } else Bukkit.getConsoleSender().sendMessage("§cDer Death-Spawn wurde noch nicht gesetzt!");
            p.setGameMode(GameMode.SPECTATOR);
            RoleManager roleManager = manager.getRoleManager();
            if (roleManager.getPlayerRole(killer) == Roles.VAMPIRE){
                if (roleManager.getPlayerRole(p) == Roles.SURVIVOR){
                    roleManager.setPlayerRole(p, Roles.SPECTATOR);
                    p.setPlayerListName("");
                    Bukkit.broadcastMessage(Data.PREFIX + "§cDer §4Vampire §chat §a" + p.getName() + " §cgetötet!");
                    boolean allSurvivorsDead = true;
                    for (Player t : Bukkit.getServer().getOnlinePlayers()){
                        if (Main.getManager().getRoleManager().getPlayerRole(t) == Roles.SURVIVOR){
                            allSurvivorsDead = false;
                        }
                    }
                    if (allSurvivorsDead){
                        Bukkit.broadcastMessage(Data.PREFIX + "§aDer §4Vampire §ahat gewonnen!");
                        Main.getManager().getGameStateManager().setGameState(GameState.ENDING_STATE);
                    }
                }
            }
            if (roleManager.getPlayerRole(killer) == Roles.SURVIVOR){
                if (roleManager.getPlayerRole(p) == Roles.VAMPIRE){
                    if (killer.getItemInHand().getType() == Material.DIAMOND_SWORD){
                        Bukkit.broadcastMessage(Data.PREFIX + "§cDer §aSurvivor §6" + killer.getName() +" §chat den Vampire §4" + p.getName() + " §cgetötet!");
                        Bukkit.broadcastMessage(Data.PREFIX + "§aDie Survivor haben gewonnen!");
                        Main.getManager().getGameStateManager().setGameState(GameState.ENDING_STATE);
                    } else {
                        util.setPath("Vampire");
                        p.teleport(util.loadLocation());
                        p.setGameMode(GameMode.SURVIVAL);
                        PotionEffectType type = PotionEffectType.BLINDNESS;
                        PotionEffect potionEffect = new PotionEffect(type, 5, 99999);
                        e.getEntity().addPotionEffect(potionEffect);
                        id = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
                            private int seconds = 10, ticks = 0;
                            @Override
                            public void run() {
                                isRunning = true;
                                ticks++;
                                if (ticks==20){
                                    ticks = 0;
                                    seconds--;
                                }
                                if (seconds == 0){
                                    end(e.getEntity());
                                }
                                TitleAPI.sendTitle(e.getEntity(), 20, 100, 20, "", "§cDu kannst dich in §6" + seconds + " Sekunden §cwieder bewegen!");
                                LocationUtil util = new LocationUtil("Vampire");
                                if (util.loadLocation() != null){
                                    e.getEntity().teleport(util.loadLocation());
                                } else System.err.println("Der Vampire-Spawn wurde nochnicht gesetzt!");
                            }
                        }, 0, 1);
                    }
                }
            }

        }

    }


}
