/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Marketplace;

import connection.request;
import connection.connectDB;
import item.item;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import map.map;

/**
 *
 * @author vitra
 */
@WebService(serviceName = "product")
public class product {

     /**
     * This is a sample web service operation
     * @param idus
     * @return 
     */
    @WebMethod(operationName = "yourProduct")
    public map yourProduct(@WebParam(name = "token") String token, @WebParam(name = "id_user") String idus) {
        ArrayList<item> list = new ArrayList();
        request req = new request();
        ArrayList<String> vtoken = req.requestValidatedToken(token);
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
                    sql1.setString(1, Integer.toString(idpro));
                    ResultSet result1 = sql1.executeQuery();
                    int likes = 0;
                    if (result1.getRow() != 0) {
                        likes = result1.getInt("count(id_product)");
                    }
                    
                    PreparedStatement sql2 = conn.prepareStatement("SELECT sum(quantity) FROM purchase WHERE id_product = ?"); 
                    sql2.setString(1, Integer.toString(idpro));
                    ResultSet result2 = sql2.executeQuery();
                    int purch = 0;
                    if (result2.getRow() != 0) {
                        purch = result2.getInt("sum(quantity)");
                    }

                    item x = new item(idpro, "", "", date, time, photo, name, price, desc, likes, purch, "");
                    list.add(x);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(request.class.getName()).log(Level.SEVERE, null, ex);
        }
        map m = new map(vtoken.get(0), vtoken.get(1),0,list,null,null);
        connDB.closeConn();
        return m;
    }
}
