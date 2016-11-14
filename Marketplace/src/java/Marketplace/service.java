/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Marketplace;

import connection.connectDB;
import item.item;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author vitra
 */
@WebService(serviceName = "service")
public class service {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "getitem")
    public item getItem(@WebParam(name = "id_product") String idpro) {
        connectDB connDB = new connectDB();
        Connection conn = connDB.connectMP();
        int i = 0;
        item x = new item();
        PreparedStatement sql; 
        try {
            String query = "SELECT * FROM product WHERE id_product = " + idpro;
            sql = conn.prepareStatement(query);
            
            ResultSet result = sql.executeQuery();
            if (result.next()) {
                Boolean deleted = result.getBoolean("deleted");
                if (!deleted) {
                    Date phpdate = result.getDate("date_added");
                    SimpleDateFormat df = new SimpleDateFormat("EEEE, dd MMMM yyyy");
                    String date = df.format(phpdate);

                    Time phptime = result.getTime("time_added");
                    SimpleDateFormat tf = new SimpleDateFormat("hh.mm a");
                    String time = tf.format(phptime);

                    String photo = result.getString("photo");
                    String name = result.getString("name");
                    int price = result.getInt("price");
                    String desc = result.getString("description");

                    PreparedStatement sql1 = conn.prepareStatement("SELECT count(id_product) FROM likes WHERE id_product = ?"); 
                    sql1.setString(1, idpro);
                    ResultSet result1 = sql1.executeQuery();
                    int likes = 0;
                    if (result1.getRow() != 0) {
                        likes = result1.getInt("count(id_product)");
                    }
                    
                    PreparedStatement sql2 = conn.prepareStatement("SELECT sum(quantity) FROM purchase WHERE id_product = ?"); 
                    sql2.setString(1, idpro);
                    ResultSet result2 = sql2.executeQuery();
                    int purch = 0;
                    if (result2.getRow() != 0) {
                        purch = result2.getInt("sum(quantity)");
                    }

                    x = new item(Integer.parseInt(idpro), "", "", date, time, photo, name, price, desc, likes, purch, "");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(addProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        connDB.closeConn();
        return x;
    }
}
