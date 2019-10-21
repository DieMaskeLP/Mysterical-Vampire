package de.nexxus.vampire.utils;

//Created by MrKompetnz on 10/20/19

import de.nexxus.vampire.manager.ConfigFileUtil;

public class Data {

    public static final String PREFIX = ConfigFileUtil.getConfigString("Messages.Prefix"), NO_PERMISSION = PREFIX + "§cKeine Rechte!";

    public static final int MIN_PLAYERS = 4, MAX_PLAYERS = 9;

}
