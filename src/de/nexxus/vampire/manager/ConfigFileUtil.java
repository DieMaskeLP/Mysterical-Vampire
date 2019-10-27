package de.nexxus.vampire.manager;

//Created by MrKompetnz on 10/20/19

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigFileUtil {

    public static File folder = new File("plugins/Vampire/");
    public static File file = new File("plugins/Vampire/config.yml");
    public static YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

    public static void saveFiles() {
        try {
            config.save(file);
        } catch (IOException e) {
            System.out.println("[Vampire] Die Datei " + file.getName() + " konnte nicht gespeichert werden!");
        }
    }

    public static void setupFiles() {
        if(!folder.exists()) folder.mkdir();

        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("[Vampire] " + file.getName() + " konnte nicht erstellt werden!");
            }
            config.addDefault("Messages.Prefix", "&8[&5Mysterical&8-&4Vampire&8] ");
            config.addDefault("Game.StartInstant", true);
            config.options().copyDefaults(true);
            saveFiles();
        }
    }

    public static boolean existConfig(String path){
        return config.contains(path);
    }

    public static Object getConfigValue(String path) {
        return config.get(path);
    }

    public static String getConfigString(String path) {
        return config.getString(path).replace('&', '§');
    }
}
