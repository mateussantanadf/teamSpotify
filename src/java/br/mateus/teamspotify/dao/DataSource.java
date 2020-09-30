/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mateus.teamspotify.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author One For All
 */
public class DataSource {
    private String hostname;
    private String username;
    private String password;
    private String database;
    private Connection connection;
    
    public DataSource(){
        try {
            hostname = "localhost";
            database = "teamspotify";
            username = "root";
            password = "3216540";
            String URL = "jdbc:mysql://"+hostname+":3306/"+database;
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            connection = DriverManager.getConnection(URL+"?useTimeZone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false", username, password);
            System.out.println("DataSource - Connected");    
        } 
        catch (SQLException ex) {
            System.out.println("ERRO ao Conectar - "+ex.getMessage());
        }
    }
    public Connection getConnection(){
        return this.connection;
    } 
}
