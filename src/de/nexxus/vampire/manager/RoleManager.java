package de.nexxus.vampire.manager;

import com.google.common.collect.Lists;
import de.nexxus.vampire.main.Main;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class RoleManager {

    private HashMap<String, Roles> playerRoles;
    private ArrayList<Player> players;

    private int vampires, survivors;

    public RoleManager() {
        playerRoles = new HashMap<>();
        players = Main.players;
    }

    public void calculateRoles(int playerSize) {
        vampires = (int) Math.round(Math.log(playerSize) * 1.2);
        survivors = playerSize - vampires;

        Collections.shuffle(Main.players);

        int counter = 0;
        for (int i = counter; i < vampires; i++) {
            playerRoles.put(players.get(i).getName(), Roles.VAMPIRE);
        }
        counter += vampires;

        for (int i = counter; i < survivors; i++) {
            playerRoles.put(players.get(i).getName(), Roles.SURVIVOR);
        }
    }

    public Roles getPlayerRole(Player player) {
        return playerRoles.get(player.getName());
    }
}
