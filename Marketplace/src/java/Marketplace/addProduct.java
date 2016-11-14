/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Marketplace;

import connection.connectDB;
import connection.request;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import map.map;

/**
 *
 * @author utamidias
 */
@WebService(serviceName = "addProduct")
public class addProduct {
    
    @WebMethod(operationName = "insertnewproduct")
    public map insertaddprod(@WebParam(name = "token") String token, @WebParam(name = "prodname") String prodname, @WebParam(name = "price") int price, @WebParam(name = "description") String description, @WebParam(name = "photo") String photo, @WebParam(name = "seller_id") int seller_id) {
        request req = new request();
        ArrayList<String> vtoken = req.requestValidatedToken(token);
        connectDB connDB = new connectDB();
        Connection conn = connDB.connectMP();
        
        int i = 0;
        PreparedStatement sql; 
        try {
            String query = "INSERT INTO product(name, price, description, date_added, time_added, photo, seller_id, deleted) VALUES ('" + prodname + "','" + price + "','" + description + "',curDate(),curTime(),'" + photo + "','" + seller_id + "',0)";
            sql = conn.prepareStatement(query);
            
            i = sql.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(addProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        map m = new map(vtoken.get(0), vtoken.get(1),i, null, null, null);
        connDB.closeConn();
        return m;
    }
}

