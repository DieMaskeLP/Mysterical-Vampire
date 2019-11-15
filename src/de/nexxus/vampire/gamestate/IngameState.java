package de.nexxus.vampire.gamestate;

//Created by MrKompetnz on 10/21/19

import com.connorlinfoot.titleapi.TitleAPI;
import de.nexxus.vampire.countdown.LobbyCountdown;
import de.nexxus.vampire.listener.DeathListener;
import de.nexxus.vampire.main.Main;
import de.nexxus.vampire.manager.RoleManager;
import de.nexxus.vampire.manager.Roles;
import de.nexxus.vampire.utils.ItemBuilder;
import de.nexxus.vampire.utils.LocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class IngameState extends GameState {

    private int id, seconds = 20, ticks = 0;

    public void end(){
        Bukkit.getScheduler().cancelTask(id);
    }

    @Override
    public void start() {
        LobbyCountdown countdown = Main.getManager().getLobbyCountdown();
        if (countdown.isRunning()) countdown.stop();

        System.out.println("IngameState started!");
        Main.getManager().getRoleManager().calculateRoles();
        Main.getManager().getIngameCountdown().start();
        LocationUtil util = new LocationUtil();
        util.setPath("Vampire");
        if (util.loadLocation() != null){
            World world = Bukkit.getServer().getWorld(util.loadLocation().getWorld().getName());
            world.setMonsterSpawnLimit(0);
            for (LivingEntity entity : world.getLivingEntities()){
                if (entity.getType() != EntityType.PLAYER){
                    entity.setHealth(0);
                }
            }
        }
        for (Player t : Main.getManager().getRoleManager().players){
            PlayerInventory playerInventory = t.getInventory();
            playerInventory.setHeldItemSlot(0);
            t.setHealth(t.getMaxHealth());
            t.setFoodLevel(20000);
            t.setCanPickupItems(false);
            if (Main.getManager().getRoleManager().getPlayerRole(t) == Roles.VAMPIRE){
                t.setDisplayName("§4" + t.getDisplayName());
                t.setPlayerListName(t.getDisplayName());
                TitleAPI.sendTitle(t, 0, 5, 2, "§6Rolle: §4Vampire", "§cFinde die Survivor und saug deren §4Blut §caus!");
                ItemBuilder builder = new ItemBuilder("§4§lBlut Axt", Material.DIAMOND_AXE,1);
                builder.setEnchantment(Enchantment.DAMAGE_ALL, 1, true);
                builder.setLore("§cKöpfe hiermit die §aSurvivor!", "§cUnd bekomme mehr Fähigkeiten");
                t.setItemInHand(builder.build());
                util.setPath("Vampire");
                t.setMaxHealth(t.getMaxHealth()*2);
                t.setHealth(t.getMaxHealth());
                if (util.loadLocation() != null){
                    t.teleport(util.loadLocation());
                } else Bukkit.getConsoleSender().sendMessage("§4Der Vampire-Spawn wurde noch nicht gesetzt!");
                id = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
                    @Override
                    public void run() {
                        if (seconds==5){
                            for (Player t : Main.getManager().getRoleManager().players){
                                RoleManager manager = Main.getManager().getRoleManager();
                                if (manager.getPlayerRole(t)==Roles.SURVIVOR){
                                    PotionEffect potionEffect = new PotionEffect(PotionEffectType.NIGHT_VISION, 99999999, 5, true, false);
                                    PotionEffect potionEffect2 = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 99999999, 1, true, false);
                                    potionEffect2.apply(t);
                                    potionEffect.apply(t);
                                }
                            }
                        }

                        if (seconds==0){
                            end();
                        } else {
                            ticks++;
                            t.teleport(util.setPath("Vampire").loadLocation());
                            if (ticks == 20){
                                seconds--;
                                ticks = 0;
                                TitleAPI.sendTitle(t, 20, 30, 20, "", "§cDu kannst dich in §6" + seconds + " Sekunden §cwieder bewegen!");
                            }
                        }
                    }
                }, 0, 1);
            } else {
                if (Main.getManager().getRoleManager().getPlayerRole(t) == Roles.SURVIVOR){
                    t.setDisplayName("§a" + t.getDisplayName());
                    t.setPlayerListName(t.getDisplayName());
                    PotionEffect potionEffect = new PotionEffect(PotionEffectType.BLINDNESS, 99999999, 2, true, false);
                    PotionEffect potionEffect2 = new PotionEffect(PotionEffectType.SLOW, 99999999, 1, true, false);
                    potionEffect2.apply(t);
                    potionEffect.apply(t);
                    ItemBuilder builder = new ItemBuilder("Helm", Material.CHAINMAIL_HELMET, 1);
                    t.getInventory().setHelmet(builder.build());
                    builder.setItemBuilder("Brustplatte", Material.LEATHER_CHESTPLATE, 1);
                    t.getInventory().setChestplate(builder.build());
                    builder.setItemBuilder("Schuhe", Material.LEATHER_BOOTS, 1);
                    t.getInventory().setBoots(builder.build());
                    builder.setItemBuilder("Holz-Schwert", Material.WOOD_SWORD, 1);
                    t.setItemInHand(builder.build());
                    TitleAPI.sendTitle(t, 20, 100, 40, "§6Rolle: §aSurvivor", "§cFange den §4Vampire §cund nehme sein Leben!");
                    util.setPath("Survivor");
                    if (util.loadLocation() != null){
                        t.teleport(util.loadLocation());
                    } else Bukkit.getConsoleSender().sendMessage("§4Der Survivor-Spawn wurde noch nicht gesetzt!");
                }
            }
        }
    }

    @Override
    public void stop() {
        System.out.println("IngameState stopped!");
        Main.getManager().getIngameCountdown().stop();
        for (Player t : Bukkit.getServer().getOnlinePlayers()){
            if (Main.getManager().getRoleManager().getPlayerRole(t) == Roles.VAMPIRE){
                DeathListener.end(t);
            }
        }

    }

    @Override
    public void setSeconds(int seconds) {
        Main.getManager().getIngameCountdown().setSeconds(seconds);
    }
}
