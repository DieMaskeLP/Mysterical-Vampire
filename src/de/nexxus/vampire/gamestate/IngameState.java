package de.nexxus.vampire.gamestate;

//Created by MrKompetnz on 10/21/19

import de.nexxus.vampire.manager.TeamManager;
import de.nexxus.vampire.manager.Teams;
import org.bukkit.entity.Player;

public class IngameState extends GameState {

    @Override
    public void start() {
        System.out.println("IngameState started!");
        Player vampire = TeamManager.getVampire();
        TeamManager.initScoreboards();
        vampire.sendTitle("§eDu bist der §4§lVampir", "");
    }

    @Override
    public void stop() {
        System.out.println("IngameState stopped!");
    }
}
