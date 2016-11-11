/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Marketplace;

import connection.*;
import item.*;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.jws.WebMethod;
import java.util.ArrayList;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vitra
 */
@Stateless
@WebService(serviceName = "Service")
public class service {

    /**
     * This is a sample web service operation
     * @param idus
     * @return
     */
    @WebMethod(operationName = "arrayProduct")
    public static ArrayList<item> arrayProduct(String idus) {
        ArrayList<item> list = new ArrayList();
        
        connectDB connDB = new connectDB();
        Connection conn = connDB.connectMP();
        
        PreparedStatement sql; 
        try {
            sql = conn.prepareStatement("SELECT * FROM product WHERE seller_id = ? ORDER BY date_added DESC, time_added DESC");
            sql.setString(1, idus);
            
            ResultSet result = sql.executeQuery();

            while (result.next()) {
                Boolean deleted = result.getBoolean("deleted");
                if (!deleted) {
                    int idpro = result.getInt("id_product");

                    Date phpdate = result.getDate("date_added");
                    SimpleDateFormat df = new SimpleDateFormat("d F Y");
                    String date = df.format(phpdate);

                    Time phptime = result.getTime("time_added");
                    SimpleDateFormat tf = new SimpleDateFormat("h");
                    String time = tf.format(phptime);

                    String photo = result.getString("photo");
                    String name = result.getString("name");
                    int price = result.getInt("price");
                    //$price = number_format($phpprice, 0, ',', '.');
                    String desc = result.getString("description");

                    PreparedStatement sql1 = conn.prepareStatement("SELECT count(id_product) FROM likes WHERE id_product = ?"); 
                    sql1.setString(1, Integer.toString(idpro));
                    ResultSet result1 = sql1.executeQuery();
                    int likes = 0; //result1.getInt("count(id_product)");

                    PreparedStatement sql2 = conn.prepareStatement("SELECT sum(quantity) FROM purchase WHERE id_product = ?"); 
                    sql2.setString(1, Integer.toString(idpro));
                    ResultSet result2 = sql2.executeQuery();
                    int purch = 0; //result2.getInt("sum(quantity)");

                    item x = new item(idpro, date, time, photo, name, price, desc, likes, purch);
                    list.add(x);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(service.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        connDB.closeConn();
        return list;
    }
}