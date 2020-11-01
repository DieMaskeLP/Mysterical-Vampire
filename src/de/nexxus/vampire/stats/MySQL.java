package de.nexxus.vampire.stats;

//Created by MrKompetnz on 11/3/19

import de.nexxus.vampire.manager.ConfigFileUtil;

import java.sql.*;

public class MySQL {

    private static final String HOST = ConfigFileUtil.getMySQLConfigString("MySQL.host");
    private static final String PORT = ConfigFileUtil.getMySQLConfigString("MySQL.port");
    private static final String DATABASE = ConfigFileUtil.getMySQLConfigString("MySQL.database");
    private static final String USERNAME = ConfigFileUtil.getMySQLConfigString("MySQL.username");
    private static final String PASSWORD = ConfigFileUtil.getMySQLConfigString("MySQL.password");

    private static Connection connection;

    public static void connect() {
        if(!isConnected()) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?autoReconnect=true", USERNAME, PASSWORD);
                System.out.println("[Vampire] Die Verbindung zur Datenbank wurde hergestellt!");
            } catch (SQLException e) {
                System.out.println("[Vampire] Die zur Datenbank ist fehlgeschlagen! Fehler: " + e.getMessage());
            }
        }
    }

    public static boolean isConnected() {
        return (connection == null ? false : true);
    }

    public static void disconnect() {
        try {
            if(isConnected()) {
                connection.close();
                System.out.println("[Vampire] MySQL-Verbindung getrennt!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void update(String query) {
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
            statement.close();
        } catch (SQLException e) {
            connect();
            System.err.println(e);
        }
    }

    public static ResultSet query(String query) {
        ResultSet resultSet = null;

        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            connect();
            System.err.println(e);
        }
        return resultSet;
    }

    public static void createTable() {
        update("CREATE TABLE IF NOT EXISTS Stats(UUID varchar(64), GAMESPLAYED int, KILLS int, VAMPIREKILLS int, SURVIVORKILLS int, DEATHS int, VAMPIREDEATHS int, SURVIVORDEATHS int, GAMESWON int);");
    }
}
