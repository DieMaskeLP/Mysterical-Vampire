package de.nexxus.vampire.manager;

//Created by MrKompetnz on 10/20/19

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigFileUtil {

    public static File folder = new File("plugins/Vampire/");
    public static File file = new File("plugins/Vampire/setup.yml");
    public static YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

    public static File mysqlFile = new File("plugins/Vampire/mysql.yml");
    public static YamlConfiguration mysqlConfig = YamlConfiguration.loadConfiguration(mysqlFile);

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

    public static void saveSQLFile() {
        try {
            mysqlConfig.save(mysqlFile);
        } catch (IOException ex) {
            System.out.println("[Vampire] Die Datei " + mysqlFile.getName() + " konnte nicht gespeichert werden!");
        }
    }

    public static void setupMySQLFile() {
        if(!folder.exists()) folder.mkdir();

        if(!mysqlFile.exists()) {
            try {
                mysqlFile.createNewFile();
            } catch (IOException e) {
                System.out.println("[Vampire] Die Datei " + mysqlFile.getName() + " konnte nicht erstellt werden!");
            }
            mysqlConfig.addDefault("MySQL.host", "134.255.253.139");
            mysqlConfig.addDefault("MySQL.port", "3306");
            mysqlConfig.addDefault("MySQL.database", "NexxusPlayZz-Trololo");
            mysqlConfig.addDefault("MySQL.username", "NexxusPlayZz");
            mysqlConfig.addDefault("MySQL.password", "D3XHcmJzI2I40Ild4nC0");
            mysqlConfig.options().copyDefaults(true);
            saveSQLFile();
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

    public static String getMySQLConfigString(String path) {
        return mysqlConfig.getString(path);
    }
}
