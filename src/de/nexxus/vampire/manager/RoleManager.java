package de.nexxus.vampire.manager;

import com.google.common.collect.Lists;
import de.nexxus.vampire.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class RoleManager {

    private HashMap<String, Roles> playerRoles;
    public ArrayList<Player> players;

    private int vampires, survivors;

    public RoleManager() {
        playerRoles = new HashMap<>();
        players = new ArrayList<>();
    }

    public void calculateRoles() {
        Random random = new Random();
        Player vampire = players.get(random.nextInt(players.size()));
        playerRoles.put(vampire.getName(), Roles.VAMPIRE);
        for (Player t : Bukkit.getOnlinePlayers()){
            if (!playerRoles.containsKey(t.getName())){
                playerRoles.put(t.getName(), Roles.SURVIVOR);
            }
        }
    }

    public Roles getPlayerRole(Player player) {
        return playerRoles.get(player.getName());
    }
}
