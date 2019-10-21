package de.nexxus.vampire.gamestate;

//Created by MrKompetnz on 10/21/19

import de.nexxus.vampire.manager.TeamManager;
import de.nexxus.vampire.manager.Teams;
import org.bukkit.entity.Player;

public class IngameState extends GameState {

    @Override
    public void start() {

        Player vampire = TeamManager.getPlayersByTeam(Teams.VAMPIRE).get(0);
        TeamManager.initScoreboards();
        vampire.sendTitle();

    }

    @Override
    public void stop() {

    }
}
