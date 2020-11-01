package de.nexxus.vampire.utils;

//Created by MrKompetnz on 10/20/19

import de.nexxus.vampire.manager.ConfigFileUtil;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Data {

    public static final String PREFIX = ConfigFileUtil.getConfigString("Messages.Prefix"), NO_PERMISSION = PREFIX + "§cKeine Rechte!";

    public static final int MIN_PLAYERS = 1, MAX_PLAYERS = 9;
    public static List<Player> builder = new ArrayList<>();

    // S:/Minecraft Server (Spigot 1.8)/plugins

    public static String getUUID(Player player) {
        return player.getUniqueId().toString();
    }
}
