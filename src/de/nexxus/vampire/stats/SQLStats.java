package de.nexxus.vampire.stats;

//Created by MrKompetnz on 11/3/19

import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLStats {

    public static boolean playerExists(String uuid) {
        try {
            ResultSet resultSet = MySQL.query("SELECT * FROM Stats WHERE UUID= '" + uuid + "'");
            if(resultSet.next()) {
                return resultSet.getString("UUID") != null;
            }
            return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static void createPlayer(String uuid) {
        if(!playerExists(uuid)) {
            MySQL.update("INSERT INTO Stats(UUID, GAMESPLAYED, KILLS, VAMPIREKILLS, SURVIVORKILLS, DEATHS, VAMPIREDEATHS, SURVIVORDEATHS, GAMESWON) VALUES ('" + uuid + "', '0', '0', '0','0', '0', '0', '0', '0');");
        }
    }

    public static int getKills(String uuid) {
        int i = 0;
        if(playerExists(uuid)) {

            try {
                ResultSet resultSet = MySQL.query("SELECT * FROM Stats WHERE UUID= '" + uuid + "'");
                if((!resultSet.next()) || (Integer.valueOf(resultSet.getInt("KILLS")) == null));
                i = resultSet.getInt("KILLS");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getKills(uuid);
        }
        return i;
    }

    public static int getVampireKills(String uuid) {
        int i = 0;
        if(playerExists(uuid)) {

            try {
                ResultSet resultSet = MySQL.query("SELECT * FROM Stats WHERE UUID= '" + uuid + "'");
                if((!resultSet.next()) || (Integer.valueOf(resultSet.getInt("VAMPIREKILLS")) == null));
                i = resultSet.getInt("VAMPIREKILLS");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getVampireKills(uuid);
        }
        return i;
    }

    public static int getSurvivorKills(String uuid) {
        int i = 0;
        if(playerExists(uuid)) {

            try {
                ResultSet resultSet = MySQL.query("SELECT * FROM Stats WHERE UUID= '" + uuid + "'");
                if((!resultSet.next()) || (Integer.valueOf(resultSet.getInt("SURVIVORKILLS")) == null));
                i = resultSet.getInt("SURVIVORKILLS");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getSurvivorKills(uuid);
        }
        return i;
    }

    public static int getDeaths(String uuid) {
        int i = 0;
        if(playerExists(uuid)) {

            try {
                ResultSet resultSet = MySQL.query("SELECT * FROM Stats WHERE UUID= '" + uuid + "'");
                if((!resultSet.next()) || (Integer.valueOf(resultSet.getInt("DEATHS")) == null));
                i = resultSet.getInt("DEATHS");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getDeaths(uuid);
        }
        return i;
    }

    public static int getVampireDeaths(String uuid) {
        int i = 0;
        if(playerExists(uuid)) {

            try {
                ResultSet resultSet = MySQL.query("SELECT * FROM Stats WHERE UUID= '" + uuid + "'");
                if((!resultSet.next()) || (Integer.valueOf(resultSet.getInt("VAMPIREDEATHS")) == null));
                i = resultSet.getInt("VAMPIREDEATHS");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getVampireDeaths(uuid);
        }
        return i;
    }

    public static int getSurvivorDeaths(String uuid) {
        int i = 0;
        if(playerExists(uuid)) {

            try {
                ResultSet resultSet = MySQL.query("SELECT * FROM Stats WHERE UUID= '" + uuid + "'");
                if((!resultSet.next()) || (Integer.valueOf(resultSet.getInt("SURVIVORDEATHS")) == null));
                i = resultSet.getInt("SURVIVORDEATHS");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getSurvivorDeaths(uuid);
        }
        return i;
    }

    public static int getGames(String uuid) {
        int i = 0;
        if(playerExists(uuid)) {

            try {
                ResultSet resultSet = MySQL.query("SELECT * FROM Stats WHERE UUID= '" + uuid + "'");
                if((!resultSet.next()) || (Integer.valueOf(resultSet.getInt("GAMESPLAYED")) == null));
                i = resultSet.getInt("GAMESPLAYED");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getGames(uuid);
        }
        return i;
    }

    public static int getWins(String uuid) {
        int i = 0;
        if(playerExists(uuid)) {

            try {
                ResultSet resultSet = MySQL.query("SELECT * FROM Stats WHERE UUID= '" + uuid + "'");
                if((!resultSet.next()) || (Integer.valueOf(resultSet.getInt("GAMESWON")) == null));
                i = resultSet.getInt("GAMESWON");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getWins(uuid);
        }
        return i;
    }

    public static void setKills(String uuid, int kills) {
        if(playerExists(uuid)) {
            MySQL.update("UPDATE Stats SET KILLS= '" + kills + "' WHERE UUID= '" + uuid + "';");
        } else {
            createPlayer(uuid);
            setKills(uuid, kills);
        }
    }

    public static void setVampireKills(String uuid, int kills) {
        if(playerExists(uuid)) {
            MySQL.update("UPDATE Stats SET VAMPIREKILLS= '" + kills + "' WHERE UUID= '" + uuid + "';");
        } else {
            createPlayer(uuid);
            setVampireKills(uuid, kills);
            addKill(uuid);
        }
    }

    public static void setSurvivorKills(String uuid, int kills) {
        if(playerExists(uuid)) {
            MySQL.update("UPDATE Stats SET SURVIVORKILLS= '" + kills + "' WHERE UUID= '" + uuid + "';");
        } else {
            createPlayer(uuid);
            setSurvivorKills(uuid, kills);
            addKill(uuid);
        }
    }

    public static void setDeaths(String uuid, int deaths) {
        if(playerExists(uuid)) {
            MySQL.update("UPDATE Stats SET DEATHS= '" + deaths + "' WHERE UUID= '" + uuid + "';");
        } else {
            createPlayer(uuid);
            setDeaths(uuid, deaths);
        }
    }

    public static void setVampireDeaths(String uuid, int deaths) {
        if(playerExists(uuid)) {
            MySQL.update("UPDATE Stats SET VAMPIREDEATHS= '" + deaths + "' WHERE UUID= '" + uuid + "';");
        } else {
            createPlayer(uuid);
            setVampireDeaths(uuid, deaths);
            addDeath(uuid);
        }
    }

    public static void setSurvivorDeaths(String uuid, int deaths) {
        if(playerExists(uuid)) {
            MySQL.update("UPDATE Stats SET SURVIVORDEATHS= '" + deaths + "' WHERE UUID= '" + uuid + "';");
        } else {
            createPlayer(uuid);
            setSurvivorDeaths(uuid, deaths);
            addDeath(uuid);
        }
    }

    public static void setGames(String uuid, int games) {
        if(playerExists(uuid)) {
            MySQL.update("UPDATE Stats SET GAMESPLAYED= '" + games + "' WHERE UUID= '" + uuid + "';");
        } else {
            createPlayer(uuid);
            setGames(uuid, games);
        }
    }

    public static void setWins(String uuid, int won) {
        if(playerExists(uuid)) {
            MySQL.update("UPDATE Stats SET GAMESWON= '" + won + "' WHERE UUID= '" + uuid + "';");
        } else {
            createPlayer(uuid);
            setWins(uuid, won);
        }
    }

    public static void addKill(String uuid) {
        setKills(uuid, getKills(uuid) + 1);
    }

    public static void addVampireKill(String uuid) {
        setVampireKills(uuid, getVampireKills(uuid) + 1);
    }

    public static void addSurvivorKill(String uuid) {
        setSurvivorKills(uuid, getSurvivorKills(uuid) + 1);
    }

    public static void addDeath(String uuid) {
        setDeaths(uuid, getDeaths(uuid) + 1);
    }

    public static void addVampireDeath(String uuid) {
        setVampireDeaths(uuid, getVampireDeaths(uuid) + 1);
    }

    public static void addSurvivorDeath(String uuid) {
        setSurvivorDeaths(uuid, getSurvivorDeaths(uuid) + 1);
    }

    public static void addGame(String uuid) {
        setGames(uuid, getGames(uuid) + 1);
    }

    public static void addWin(String uuid) {
        setWins(uuid, getWins(uuid) + 1);
    }

    public static void removeKill(String uuid) {
        setKills(uuid, getKills(uuid) - 1);
    }

    public static void removeVampireKill(String uuid) {
        setVampireKills(uuid, getVampireKills(uuid) - 1);
    }

    public static void removeSurvivorKill(String uuid) {
        setSurvivorKills(uuid, getSurvivorKills(uuid) - 1);
    }

    public static void removeDeath(String uuid) {
        setDeaths(uuid, getDeaths(uuid) - 1);
    }

    public static void removeVampireDeath(String uuid) {
        setVampireDeaths(uuid, getVampireDeaths(uuid) - 1);
    }

    public static void removeSurvivorDeath(String uuid) {
        setSurvivorDeaths(uuid, getSurvivorDeaths(uuid) - 1);
    }


    public static void removeGame(String uuid) {
        setGames(uuid, getGames(uuid) - 1);
    }

    public static void removeWin(String uuid) {
        setWins(uuid, getWins(uuid) - 1);
    }

    public static void resetStats(String uuid) {
        setSurvivorDeaths(uuid, 0);
        setVampireDeaths(uuid, 0);
        setSurvivorKills(uuid, 0);
        setVampireKills(uuid, 0);
        setDeaths(uuid, 0);
        setKills(uuid, 0);
        setGames(uuid, 0);
        setWins(uuid, 0);
    }
}
