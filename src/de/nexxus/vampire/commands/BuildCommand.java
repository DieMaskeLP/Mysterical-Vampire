package de.nexxus.vampire.commands;
import de.nexxus.vampire.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class BuildCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player){
            Player p = (Player) commandSender;
            if (strings.length==1){
                if (p.hasPermission("game.build.other")){
                    Player t = Bukkit.getPlayer(strings[0]);
                    if (t != null){
                        if (Data.builder.contains(t)){
                            Data.builder.remove(t);
                        } else Data.builder.add(t);
                        p.sendMessage(Data.PREFIX + "§eBuildmode ist nun für §e " + t.getName() + (Data.builder.contains(p) ? "§aaktiviert!" : "§cdeaktiviert!"));
                        p.sendMessage(Data.PREFIX + "§eBuildmode ist nun " + (Data.builder.contains(p) ? "§aacktiviert!" : "§cdeaktiviert!"));
                    } else p.sendMessage(Data.PREFIX + "§cDer Spieler ist nicht online!");
                } else p.sendMessage(Data.NO_PERMISSION);
            } else {
                if (p.hasPermission("game.build.own")){
                    if (Data.builder.contains(p)){
                        Data.builder.remove(p);
                    } else Data.builder.add(p);
                    p.sendMessage(Data.PREFIX + "§eBuildmode ist nun " + (Data.builder.contains(p) ? "§aaktiviert!" : "§cdeaktiviert!"));
                } else p.sendMessage(Data.NO_PERMISSION);
            }
        }
        return false;
    }
}
