/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package identityservice;

import connection.connectDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author User
 */
public class loginDB {
    public static int validate(String name, String pass) throws SQLException {        
        boolean status = false;
        ResultSet rs = null;
        int id_user = 0;
        connectDB connDB = new connectDB();
        Connection conn = connDB.connectIS();
        PreparedStatement pst = null;
        try {
            pst = conn
                    .prepareStatement("SELECT * FROM user WHERE (username = ? OR email = ?) AND password = ?");
            pst.setString(1, name);
            pst.setString(2, name);
            pst.setString(3, pass);

            rs = pst.executeQuery();
            status = rs.next();
            id_user = rs.getInt("id_user");
            
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
        }
        return id_user;
    }
    
    public static int insertToken(int id_user, String token) {
        int status = 0;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "identityservice";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "1234";
        
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager
                    .getConnection(url + dbName, userName, password);
            Calendar date = Calendar.getInstance();
            long time = date.getTimeInMillis();
            Date exp = new Date(time + 1*10*1000);
            DateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            
            pst = conn.prepareStatement("INSERT INTO token values(?,?,?)");
            pst.setInt(1, id_user);
            pst.setString(2, token);
            pst.setString(3, format.format(exp));
            status=pst.executeUpdate();
                

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            System.out.println(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }

        }
        return status;        
    }
}
