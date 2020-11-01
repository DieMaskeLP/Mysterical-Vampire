package de.nexxus.vampire.commands;

//Created by MrKompetnz on 11/9/19

import de.nexxus.vampire.stats.SQLStats;
import de.nexxus.vampire.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

public class StatsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(label.equalsIgnoreCase("stats")) {
            if(sender instanceof Player) {
                Player player = (Player) sender;
                if(args.length == 0) {
                    if(SQLStats.playerExists(Data.getUUID(player))) {
                        String uuid = Data.getUUID(player);

                        float kd = SQLStats.getKills(uuid) / SQLStats.getDeaths(uuid);
                        float vampireKD = SQLStats.getVampireKills(uuid) / SQLStats.getVampireDeaths(uuid);
                        float survivorKD = SQLStats.getSurvivorKills(uuid) / SQLStats.getSurvivorDeaths(uuid);

                        float winchance = SQLStats.getWins(uuid) / SQLStats.getGames(uuid);

                        DecimalFormat formatter = new DecimalFormat(".00");

                        player.sendMessage("§7----------§6Stats§7----------");
                        player.sendMessage("§5Name: §7" + player.getName());
                        player.sendMessage("§5UUID: §7" + uuid);
                        player.sendMessage("§5Gespielte Spiele: §7" + SQLStats.getGames(uuid));
                        player.sendMessage("§5Gewonnene Spiele: §7" + SQLStats.getWins(uuid));
                        player.sendMessage("§5Allgemeine-Kills: §7" + SQLStats.getKills(uuid));
                        player.sendMessage("§5Allgemeine-Deaths: §7" + SQLStats.getDeaths(uuid));
                        player.sendMessage("§5Allgemeine-K/D: §7" + formatter.format(kd));
                        player.sendMessage("§5Vampirekills: §7" + SQLStats.getVampireKills(uuid));
                        player.sendMessage("§5Vampiredeaths: §7" + SQLStats.getVampireDeaths(uuid));
                        player.sendMessage("§5Vampire-K/D §7" + formatter.format(vampireKD));
                        player.sendMessage("§5Survivorkills: §7" + SQLStats.getSurvivorKills(uuid));
                        player.sendMessage("§5Survivordeaths: §7" + SQLStats.getSurvivorDeaths(uuid));
                        player.sendMessage("§5Survivor-K/D: §7" + formatter.format(survivorKD));
                        player.sendMessage("§5Winchance: §7" + formatter.format(winchance) + "%");
                    } else {
                        player.sendMessage("Für dich gibt es noch keine Stats!");
                    }
                } else if(args.length == 1) {
                    if(args[0].equalsIgnoreCase("reset")) {
                        if(player.hasPermission("vampire.resetstats")) {
                            SQLStats.resetStats(player.getUniqueId().toString());
                            player.sendMessage(Data.PREFIX + "§aDeine Stats wurden erfolgreich resettet!");
                        }
                    } else {
                        Player target = Bukkit.getPlayer(args[0]);
                        if(SQLStats.playerExists(target.getUniqueId().toString())) {
                            String uuid = Data.getUUID(target);

                            float kd = SQLStats.getKills(uuid) / SQLStats.getDeaths(uuid);
                            float vampireKD = SQLStats.getVampireKills(uuid) / SQLStats.getVampireDeaths(uuid);
                            float survivorKD = SQLStats.getSurvivorKills(uuid) / SQLStats.getSurvivorDeaths(uuid);

                            float winchance = SQLStats.getWins(uuid) / SQLStats.getGames(uuid);

                            DecimalFormat formatter = new DecimalFormat(".00");

                            player.sendMessage("§7----------§6Stats§7----------");
                            player.sendMessage("§5Name: §7" + target.getName());
                            player.sendMessage("§5UUID: §7" + uuid);
                            player.sendMessage("§5Gespielte Spiele: §7" + SQLStats.getGames(uuid));
                            player.sendMessage("§5Gewonnene Spiele: §7" + SQLStats.getWins(uuid));
                            player.sendMessage("§5Allgemeine-Kills: §7" + SQLStats.getKills(uuid));
                            player.sendMessage("§5Allgemeine-Deaths: §7" + SQLStats.getDeaths(uuid));
                            player.sendMessage("§5Allgemeine-K/D: §7" + formatter.format(kd));
                            player.sendMessage("§5Vampirekills: §7" + SQLStats.getVampireKills(uuid));
                            player.sendMessage("§5Vampiredeaths: §7" + SQLStats.getVampireDeaths(uuid));
                            player.sendMessage("§5Vampire-K/D §7" + formatter.format(vampireKD));
                            player.sendMessage("§5Survivorkills: §7" + SQLStats.getSurvivorKills(uuid));
                            player.sendMessage("§5Survivordeaths: §7" + SQLStats.getSurvivorDeaths(uuid));
                            player.sendMessage("§5Survivor-K/D: §7" + formatter.format(survivorKD));
                            player.sendMessage("§5Winchance: §7" + formatter.format(winchance) + "%");
                        } else {
                            player.sendMessage(Data.PREFIX + "§cDieser Spieler existiert nicht!");
                        }
                    }

                } else if(args.length == 2) {
                    if(args[0].equalsIgnoreCase("reset")) {
                        if (player.hasPermission("vampire.resetstats")) {
                            String targetUUID = Bukkit.getPlayer(args[1]).getUniqueId().toString();
                            if (SQLStats.playerExists(targetUUID)) {
                                SQLStats.resetStats(targetUUID);
                                player.sendMessage(Data.PREFIX + "§aDu hast die Stats von §6" + args[1] + "§a erfolgreich resettet!");
                            } else player.sendMessage(Data.PREFIX + "§cDieser Spieler existiert nicht!");
                        }
                    }
                }
            }
        }
        return false;
    }
}
