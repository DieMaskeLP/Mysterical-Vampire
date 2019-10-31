package de.nexxus.vampire.commands;

import de.nexxus.vampire.main.Main;
import de.nexxus.vampire.utils.Data;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

//Created by Frederic | DieMaskeLP at 31.10.2019, 18:51
public class StopCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player){
            Player p = (Player) commandSender;
            if (p.hasPermission("vampire.stop")){
                Main.getManager().getGameStateManager().stopCurrentGameState();
                p.sendMessage("§aDer Spielmodus wurde gestoppt!");
            } else p.sendMessage(Data.NO_PERMISSION);
        }


        return false;
    }
}
