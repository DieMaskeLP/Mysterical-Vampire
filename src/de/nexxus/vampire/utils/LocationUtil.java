package de.nexxus.vampire.utils;

//Created by MrKompetnz on 10/20/19

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class LocationUtil {

    private File file = new File("plugins/Vampire/", "locations.yml");
    private FileConfiguration config = YamlConfiguration.loadConfiguration(file);

    private Location location;
    private String path;

    public LocationUtil(Location location, String path) {
        this.location = location;
        this.path = path;
    }

    public LocationUtil setLocation(Location location){
        this.location = location;
        return this;
    }

    public LocationUtil setPath(String path){
        this.path = path;
        return this;
    }

    public LocationUtil(String root) {
        this(null, root);
    }

    public LocationUtil() {
        this(null, null);
    }

    public void saveLocation() {
        config.set(path + ".World", location.getWorld().getName());
        config.set(path + ".X", location.getX());
        config.set(path + ".Y", location.getY());
        config.set(path + ".Z", location.getZ());
        config.set(path + ".Yaw", location.getYaw());
        config.set(path + ".Pitch", location.getPitch());
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Location loadLocation() {
        if (config.contains(path)) {
            World world = Bukkit.getWorld(config.getString(path + ".World"));
            double x = config.getDouble(path + ".X");
            double y = config.getDouble(path + ".Y");
            double z = config.getDouble(path + ".Z");
            float yaw = (float) config.getDouble(path + ".Yaw");
            float pitch = (float) config.getDouble(path + ".Pitch");
            return new Location(world, x, y, z, yaw, pitch);
        } else
            return null;
    }
}
