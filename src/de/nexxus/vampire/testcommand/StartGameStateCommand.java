package de.nexxus.vampire.testcommand;

import de.nexxus.vampire.gamestate.GameState;
import de.nexxus.vampire.main.Main;
import de.nexxus.vampire.manager.Manager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartGameStateCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player){
            Player p = (Player) commandSender;
            Manager manager = Main.getManager();
            if (strings.length>0){
                switch (strings[0]){
                    case "ingame":
                        manager.getGameStateManager().setGameState(GameState.INGAME_STATE);
                        break;
                    case "lobby":
                        manager.getGameStateManager().setGameState(GameState.LOBBY_STATE);
                        break;

                    case "end":
                        manager.getGameStateManager().setGameState(GameState.ENDING_STATE);
                        break;
                }
            } else {
                p.sendMessage("Current state: " + manager.getGameStateManager().getCurrentGameState());
            }
        }


        return false;
    }
}
