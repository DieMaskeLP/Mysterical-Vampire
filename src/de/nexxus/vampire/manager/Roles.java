package de.nexxus.vampire.manager;

import org.bukkit.ChatColor;

public enum Roles {

    VAMPIRE("Vampire", ChatColor.DARK_RED),
    SURVIVOR("Survivor", ChatColor.GREEN),
    SPECTATOR("Spectator", ChatColor.DARK_GRAY);


    private String name;
    private ChatColor chatColor;

    Roles(String name, ChatColor chatColor) {
        this.name = name;
        this.chatColor = chatColor;
    }

    public String getName() {
        return name;
    }

    public ChatColor getChatColor() {
        return chatColor;
    }

    public String getTeamNameWithColor() {
        return getChatColor() + getName();
    }
}
