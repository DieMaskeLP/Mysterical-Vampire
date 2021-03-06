package de.nexxus.vampire.commands;

//Created by MrKompetnz on 10/25/19

import de.nexxus.vampire.countdown.LobbyCountdown;
import de.nexxus.vampire.gamestate.GameState;
import de.nexxus.vampire.main.Main;
import de.nexxus.vampire.utils.Data;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartCommand implements CommandExecutor {

    private static final int START_SECONDS = 20;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("vampire.start")) {
                if(args.length == 0) {
                    if(Main.getManager().getGameStateManager().isCurrentGameState(GameState.LOBBY_STATE) || Main.getManager().getGameStateManager().getCurrentGameState() == null) {
                        LobbyCountdown getLobbyCountdown = Main.getManager().getLobbyCountdown();
                        if(getLobbyCountdown.isRunning() && getLobbyCountdown.getSeconds() > START_SECONDS) {
                            getLobbyCountdown.setSeconds(START_SECONDS);
                            player.sendMessage(Data.PREFIX + "§aDer Spielstart wurde beschleunigt!");
                        } else player.sendMessage(Data.PREFIX + "§cStarten nicht möglich.");
                    } else player.sendMessage(Data.PREFIX + "§cDas Spiel ist bereits gestartet.");
                } else player.sendMessage(Data.PREFIX + "§cBitte benutze §6/start§c!");
            } else player.sendMessage(Data.NO_PERMISSION);
        }
        return false;
    }
}
