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
import javax.jws.WebParam;
import user.user;

/**
 *
 * @author vitra
 */
@Stateless()
@WebService(serviceName = "Service")
public class service {

    /**
     * This is a sample web service operation
     * @param idus
     * @return
     */
    @WebMethod(operationName = "array")
    public ArrayList<item> arrayProduct(@WebParam(name = "idus")String idus) {
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
    @WebMethod(operationName = "menunjukkan")
    public item showproductinfo(@WebParam(name = "id_product") String id_product) {
        //TODO write your implementation code here:
        
        connectDB connDB = new connectDB();
        Connection conn = connDB.connectMP();
        
        item x = new item(0,"","","","",0,"",0,0);
        
        PreparedStatement sql; 
        try {
            sql = conn.prepareStatement("SELECT * FROM product WHERE id_product = ?");
            sql.setString(1, id_product);
            
            ResultSet result = sql.executeQuery();
            System.out.println("result.next() " + result.next());
            String name = result.getString("name");
            System.out.println("name  = "+ name);
            int price = result.getInt("price");
            int idpro = result.getInt("id_product");
            x.setIdpro(idpro);
            x.setName(name);
            x.setPrice(price);
            System.out.println("id = "+ x.getIdpro());
            System.out.println("name = "+ x.getName());
            System.out.println("price = "+ x.getPrice());
        } catch (SQLException ex) {
            Logger.getLogger(service.class.getName()).log(Level.SEVERE, null, ex);
        }
        connDB.closeConn();
        return x;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "showbuyer")
    public user showbuyerinfo(@WebParam(name = "id_user") int id_user) {
        //TODO write your implementation code here:
                
        connectDB connDB = new connectDB();
        Connection conn = connDB.connectIS();
        
        user x = new user();
        
        PreparedStatement sql; 
        try {
            sql = conn.prepareStatement("SELECT * FROM user WHERE id_user = ?");
            sql.setInt(1, id_user);
            
            ResultSet result = sql.executeQuery();
            System.out.println("result.next() " + result.next());
            String name = result.getString("full_name");
            int idus = result.getInt("id_user");
            String address = result.getString("full_address");
            String postcode = result.getString("postal_code");       
            String phone = result.getString("phone_number");
            
            x.setId(idus);
            x.setFName(name);
            x.setAddress(address);
            x.setPostalcode(postcode);
            x.setPhonenumber(phone);
            
        } catch (SQLException ex) {
            Logger.getLogger(service.class.getName()).log(Level.SEVERE, null, ex);
        }
        connDB.closeConn();
        return x;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "purchase")
    public int insertpurchase(@WebParam(name = "id_product") int id_product, @WebParam(name = "id_buyer") int id_buyer, @WebParam(name = "prodname") String prodname, @WebParam(name = "price") int price, @WebParam(name = "quantity") int quantity, @WebParam(name = "total_price") int total_price, @WebParam(name = "consignee") String consignee, @WebParam(name = "full_address") String full_address, @WebParam(name = "postal_code") String postal_code, @WebParam(name = "phone_number") String phone_number, @WebParam(name = "ccn") String ccn, @WebParam(name = "cvv") String cvv) {
        //TODO write your implementation code here:
        connectDB connDB = new connectDB();
        Connection conn = connDB.connectMP();
        
        int i = 0;
        System.out.println("database");
        PreparedStatement sql; 
        try {
            sql = conn.prepareStatement("INSERT INTO PURCHASE (id_product, id_buyer, product_name, price, quantity, total_price, consignee, full_address, postal_code, phone_number, credit_card_number, card_verification_value, date_purchased, time_purchased) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,CURDATE(),CURTIME())");
            sql.setInt(1, id_product);
            sql.setInt(2, id_buyer);
            sql.setString(3, prodname);
            sql.setInt(4, price);
            sql.setInt(5, quantity);
            sql.setInt(6, total_price);
            sql.setString(7, consignee);
            sql.setString(8, full_address);
            sql.setString(9, postal_code);
            sql.setString(10, phone_number);
            sql.setString(11, ccn);
            sql.setString(12, cvv);


            i = sql.executeUpdate();
            
           
            
        } catch (SQLException ex) {
            Logger.getLogger(service.class.getName()).log(Level.SEVERE, null, ex);
        }
        connDB.closeConn();
        return i;
    }
    
}