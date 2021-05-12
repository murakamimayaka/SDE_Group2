package org.example;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.Connection;

public class DatabaseConnecton {
    public Connection databaseLink;

    public Connection getConnection(){
        String databaseName = "demo_db";
        String databaseUser = "root";
        String databasePassword = "yourDbPassword";
        String url = "jdb:mysql://localhost/" + databaseName;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

        return databaseLink;

    }
}
