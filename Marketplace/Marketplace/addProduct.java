/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Marketplace;

import connection.connectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author utamidias
 */
@WebService(serviceName = "addProduct")
public class addProduct {
    
    @WebMethod(operationName = "insertnewproduct")
    public int insertaddprod(@WebParam(name = "prodname") String prodname, @WebParam(name = "price") int price, @WebParam(name = "description") String description, @WebParam(name = "photo") String photo, @WebParam(name = "seller_id") int seller_id) {
        //TODO write your implementation code here:
        connectDB connDB = new connectDB();
        Connection conn = connDB.connectMP();
        
        int i = 0;
        System.out.println("database");
        PreparedStatement sql; 
        try {
            sql = conn.prepareStatement("INSERT INTO product(name, price, description, date_added, time_added, photo, seller_id, deleted) VALUES (?,?,?,curDate(),curTime(),?,?,false)");
            sql.setString(1, prodname);
            sql.setInt(2, price);
            sql.setString(3, description);
            sql.setInt(4, seller_id);
            i = sql.executeUpdate();
            
           
            
        } catch (SQLException ex) {
            Logger.getLogger(service.class.getName()).log(Level.SEVERE, null, ex);
        }
        connDB.closeConn();
        return i;
    }
}

