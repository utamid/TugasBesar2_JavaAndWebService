/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class connectDB {
    private Connection conn;
    
    public connectDB() {
        conn = null;
    }
    
    public Connection connectIS() {
        String url = "jdbc:mysql://localhost:3306/"; 
        String dbname = "identityservice"; 
        String driver = "com.mysql.jdbc.Driver"; 
        String username = "wbd"; 
        String password = "6696"; 
        try { 
            Class.forName(driver); 
            conn = DriverManager.getConnection(url + dbname, username, password); 
        } catch (Exception e) { 
            System.out.println(e);
        }
        return conn;
    }
    
    public Connection connectMP() {
        String url = "jdbc:mysql://localhost:3306/"; 
        String dbname = "marketplace"; 
        String driver = "com.mysql.jdbc.Driver"; 
        String username = "wbd"; 
        String password = "6696"; 
        try {
            Class.forName(driver); 
            conn = DriverManager.getConnection(url + dbname, username, password);
        } catch(Exception e) {
        }
        return conn;
    }
    
    public void closeConn() {
        if (conn != null) {
            try {
                 conn.close();
            } catch (SQLException e) {
            }
        }
    }  
}
