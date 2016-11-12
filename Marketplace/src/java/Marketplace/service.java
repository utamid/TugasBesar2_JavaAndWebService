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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author vitra
 */
@WebService(serviceName = "service")
@Stateless()
public class service {

    /**
     * This is a sample web service operation
     * @param idus
     * @return 
     */
    @WebMethod(operationName = "yourProduct")
    public ArrayList<item> yourProduct(@WebParam(name = "id_user") String idus) {
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
    
    /**
     * This is a sample web service operation
     * @param idus
     * @return 
     */
    @WebMethod(operationName = "viewCatalog")
    public ArrayList<item> viewCatalog(@WebParam(name = "id_user") String idus) {
        ArrayList<item> list = new ArrayList();
        
        connectDB connDB = new connectDB();
        Connection conn = connDB.connectMP();
        
        PreparedStatement sql;
        try {
            sql = conn.prepareStatement("SELECT id_product, name, price, description, date_added, time_added, "
                                        + "seller_id, photo, username, deleted FROM product, user "
                                        + "WHERE id_user = seller_id "
                                        + "ORDER BY date_added DESC, time_added DESC");
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
    
    /**
     * This is a sample web service operation
     * @return 
     */
    @WebMethod(operationName = "viewCatalogSearch")
    public ArrayList<item> viewCatalogSearch(@WebParam(name = "id_user") String idus, @WebParam(name = "search") String search, @WebParam(name = "search_by") String search_by) {
        ArrayList<item> list = new ArrayList();
        
        connectDB connDB = new connectDB();
        Connection conn = connDB.connectMP();
        
        PreparedStatement sql;
        try {
            if (search_by == "product") {
                sql = conn.prepareStatement("SELECT id_product, name, price, description, date_added, time_added, "
                                        + "seller_id, photo, username, deleted FROM product, user "
                                        + "WHERE id_user = seller_id AND name like '%?%' "
                                        + "ORDER BY date_added DESC, time_added DESC");
            } else {
                sql = conn.prepareStatement("SELECT id_product, name, price, description, date_added, time_added, "
                                        + "seller_id, photo, username, deleted FROM product, user "
                                        + "WHERE id_user = seller_id AND username like '%?%' "
                                        + "ORDER BY date_added DESC, time_added DESC");
            }
            sql.setString(1, search);
            
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
