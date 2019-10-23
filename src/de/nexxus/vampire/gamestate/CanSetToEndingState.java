package de.nexxus.vampire.gamestate;

import de.nexxus.vampire.manager.TeamManager;
import de.nexxus.vampire.manager.Teams;
import org.bukkit.entity.Player;

public class CanSetToEndingState {

  public static boolean canGameEnd(Player playerLeftOrDied) {
    // wenn der Player Vampir ist, ist das Spiel zu beenden
    if (TeamManager.getPlayerTeam(playerLeftOrDied) == Teams.VAMPIRE) {
      System.out.println("Vampire has left");
      return true;
    }
    // wenn der Player der letzte Survivor ist, ist das Spiel zu beenden
    if (TeamManager.getPlayerTeam(playerLeftOrDied) == Teams.SURVIVOR) {
      if (TeamManager.survivors.size() == 1) {
        System.out.println("Last survivor has left");
        return true;
      }
    }
    return false;
  }
}
