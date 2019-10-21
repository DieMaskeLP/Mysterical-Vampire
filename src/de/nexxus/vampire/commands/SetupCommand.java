package de.nexxus.vampire.commands;

//Created by MrKompetnz on 10/21/19

import de.nexxus.vampire.main.Main;
import de.nexxus.vampire.utils.Data;
import de.nexxus.vampire.utils.LocationUtil;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetupCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("vampire.setup")) {
                if(args.length == 0) {
                    player.sendMessage(Data.PREFIX + "§cBitte nutze §6/setup <Lobby>");
                } else {
                    if(args[0].equalsIgnoreCase("lobby")) {
                        if(args.length == 1) {
                            new LocationUtil(player.getLocation(), "Lobby").saveLocation();
                            player.sendMessage(Data.PREFIX + "§aDie Lobby wurde (neu) gesetzt!");
                        } else
                            player.sendMessage(Data.PREFIX + "§cBitte nutze §6/setup lobby");
                    }
                }
            } else
                player.sendMessage(Data.NO_PERMISSION);
        }
        return false;
    }
}
