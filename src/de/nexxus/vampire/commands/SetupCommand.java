package de.nexxus.vampire.commands;

//Created by MrKompetnz on 10/21/19

import de.nexxus.vampire.utils.Data;
import de.nexxus.vampire.utils.LocationUtil;
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
                if(args.length != 0) {
                    LocationUtil util = new LocationUtil();
                    switch (args[0]){
                        case "lobby":
                            util.setLocation(player.getLocation()).setPath("Lobby");
                            util.saveLocation();
                            player.sendMessage(Data.PREFIX + "§aDie Lobby wurde erfolgreich gesetzt!");
                            break;
                        case "vampire":
                            util.setLocation(player.getLocation()).setPath("Vampire");
                            util.saveLocation();
                            player.sendMessage(Data.PREFIX + "§aDer Vampire-Spawn wurde erfolgreich gesetzt!");
                            break;
                        case "survivor":
                            util.setLocation(player.getLocation()).setPath("Survivor");
                            util.saveLocation();
                            player.sendMessage(Data.PREFIX + "§aDer Survivor-Spawn wurde erfolgreich gesetzt!");
                            break;
                        case "end":
                            util.setLocation(player.getLocation()).setPath("End");
                            util.saveLocation();
                            player.sendMessage(Data.PREFIX + "§aDer End-Spawn wurde erfolgreich gesetzt!");
                            break;
                        case "death":
                            util.setLocation(player.getLocation()).setPath("Death");
                            util.saveLocation();
                            player.sendMessage(Data.PREFIX + "§aDer Death-Spawn wurde erfolgreich gesetzt!");
                            break;

                        default:
                            player.sendMessage(Data.PREFIX + "§cBitte nutze §6/setup <Lobby>");
                            break;

                    }
                } else player.sendMessage(Data.PREFIX + "§cBitte nutze §6/setup <Lobby / Vampire / Survivor / End>");
            } else player.sendMessage(Data.NO_PERMISSION);
        }
        return false;
    }
}
