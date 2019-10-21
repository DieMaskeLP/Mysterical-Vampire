package de.nexxus.vampire.utils;

//Created by MrKompetnz on 10/20/19

import de.nexxus.vampire.manager.ConfigFileUtil;
import org.bukkit.entity.Player;

import javax.xml.bind.Marshaller;
import java.util.ArrayList;
import java.util.List;

public class Data {

    public static final String PREFIX = ConfigFileUtil.getConfigString("Messages.Prefix"), NO_PERMISSION = PREFIX + "§cKeine Rechte!";

    public static final int MIN_PLAYERS = 1, MAX_PLAYERS = 9;
    public static List<Player> builder = new ArrayList<>();

}
