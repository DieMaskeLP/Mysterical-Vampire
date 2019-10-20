package de.nexxus.vampire.manager;

import de.nexxus.vampire.main.Main;
import org.bukkit.configuration.Configuration;
import org.bukkit.scoreboard.Objective;

public class ConfigManager {

    private Configuration config = Main.getPlugin().getConfig();

    public Object getOrSet(String path, Object value){
        if (config.contains(path)){
            return config.get(path);
        } else {
            config.set(path, value);
            Main.getPlugin().saveConfig();
            return value;
        }
    }

    public Configuration getConfig(){
        return config;
    }

    public boolean existConfig(String path){
        return config.contains(path);
    }

    public void setConfig(String path, Object value){
        config.set(path, value);
    }

}
