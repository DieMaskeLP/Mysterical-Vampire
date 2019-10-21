package de.nexxus.vampire.testcommand;

import de.nexxus.vampire.main.Main;
import de.nexxus.vampire.manager.Manager;
import de.nexxus.vampire.manager.Teams;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetPlayerTeamCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player){
            Player p = (Player) commandSender;
            if (strings.length>0){
                Manager manager = Main.getManager();
                switch (strings[0]){
                    case "survivor":
                        manager.getTeamManager(p).setTeam(Teams.SURVIVOR);
                        break;

                    case "vampire":
                        manager.getTeamManager(p).setTeam(Teams.VAMPIRE);
                        break;

                    case "spec": case "spectator":
                        manager.getTeamManager(p).setTeam(Teams.SPECTATOR);
                        break;
                }
            }
        }


        return false;
    }
}
