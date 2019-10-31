package de.nexxus.vampire.commands;

import de.nexxus.vampire.main.Main;
import de.nexxus.vampire.utils.Data;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SetTimeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender.hasPermission("vampire.settime")){
            int time = Integer.parseInt(strings[0]);
            Main.getManager().getGameStateManager().getCurrentGameState().setSeconds(time);
            commandSender.sendMessage("§aDie Sekunden wurden erfolgreich gesetzt!");
        } else commandSender.sendMessage(Data.NO_PERMISSION);

        return false;
    }
}
