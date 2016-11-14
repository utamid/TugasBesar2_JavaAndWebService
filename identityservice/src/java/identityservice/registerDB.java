/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package identityservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import connection.*;
import user.*;

/**
 *
 * @author User
 */
public class registerDB {
   
    public static boolean validate(String name, String email) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {        
        boolean status = false;
        connectDB connDB = new connectDB();
        Connection conn = connDB.connectIS();
        try {
               String url = "jdbc:mysql://localhost:3306/identityservice";
               Class.forName("com.mysql.jdbc.Driver");
               try {
                   
                   String query = "SELECT * FROM user WHERE username = '"+name+"' OR email = '"+email+"'";
                    PreparedStatement pst = conn.prepareStatement(query);
                    ResultSet rs;

                    rs = pst.executeQuery();
                    status = rs.next();
               }
               catch (SQLException ex) {
               }
           }
           catch (ClassNotFoundException e) {
           }
        connDB.closeConn();
        return status;
    }
    
    public static user insert(user u, String h, String d) {
        int status = 0;
        connectDB connDB = new connectDB();
        Connection conn = connDB.connectIS();
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        
        try {
        
            pst = conn.prepareStatement("INSERT INTO user(full_name, username, password, full_address, postal_code, phone_number, email) values(?,?,?,?,?,?,?)");
            pst.setString(1, u.getFName());
            pst.setString(2, u.getUsername());
            pst.setString(3, h);
            pst.setString(4, d);
            pst.setString(5, u.getPostalcode());
            pst.setString(6, u.getPhonenumber());
            pst.setString(7, u.getEmail());
            status=pst.executeUpdate();
            if (status > 0) {
                PreparedStatement ps = conn.prepareStatement("SELECT id_user FROM user WHERE username = ?");
                ps.setString(1, u.getUsername());
                ResultSet rs2 = ps.executeQuery();
                boolean status2 = rs2.next();
                if (status2) {
                    int id = rs2.getInt("id_user");
                    u.setId(id);
                }
            }
                

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
    return u;        
    }
}
    

