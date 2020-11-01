package de.nexxus.vampire.stats;

//Created by MrKompetnz on 2/11/20

import java.sql.ResultSet;

public class Ranking {

    public static void sort() {
        ResultSet resultSet = MySQL.query("SELECT UUID FROM Stats");
    }
}
