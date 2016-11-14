/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Marketplace;

import connection.*;
import item.*;
import javax.jws.WebService;
import javax.jws.WebMethod;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebParam;
import map.map;
import user.user;

/**
 *
 * @author vitra
 */
@WebService(serviceName = "confirmPurchase")
public class confirmPurchase {

    /**
     *
     * @param id_product
     * @return
     */
    @WebMethod(operationName = "showProductInfo")
    public map showProductInfo(@WebParam(name = "token") String token, @WebParam(name = "id_product") String id_product) {
        request req = new request();
        ArrayList<String> vtoken = req.requestValidatedToken(token);
        connectDB connDB = new connectDB();
        Connection conn = connDB.connectMP();
        
        item x = new item(0,"","","","","","",0,"",0,0,"");
        
        PreparedStatement sql; 
        try {
            sql = conn.prepareStatement("SELECT * FROM product WHERE id_product = ?");
            sql.setString(1, id_product);
            
            ResultSet result = sql.executeQuery();
            if (result.next()) {
                String name = result.getString("name");
                int price = result.getInt("price");
                int idpro = result.getInt("id_product");
                x.setIdpro(idpro);
                x.setName(name);
                x.setPrice(price);
            }
        } catch (SQLException ex) {
            Logger.getLogger(confirmPurchase.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<item> list = new ArrayList();
        list.add(x);
        map m = new map(vtoken.get(0), vtoken.get(1), 0, list, null, null);
        connDB.closeConn();
        return m;
    }

    /**
     * Web confirmPurchase operation
     * @param id_user
     * @return 
     */
    @WebMethod(operationName = "showBuyerInfo")
    public map showBuyerInfo(@WebParam(name = "token") String token, @WebParam(name = "id_user") String id_user) {
        int x = Integer.parseInt(id_user);
        request req = new request();
        user userinfo = req.requestUser(x);
        ArrayList<user> list = new ArrayList();
        list.add(userinfo);
        ArrayList<String> vtoken = req.requestValidatedToken(token);
        System.out.println("ini" +vtoken.get(1));
        map m = new map(vtoken.get(0), vtoken.get(1), 0, null, null, list);
        return m;
    }

    /**
     * Web confirmPurchase operation
     */
    @WebMethod(operationName = "insertPurchase")
    public map insertPurchase(@WebParam(name = "token") String token, 
            @WebParam(name = "id_product") String id_product, 
            @WebParam(name = "id_buyer") String id_buyer, 
            @WebParam(name = "prodname") String prodname, 
            @WebParam(name = "price") int price, 
            @WebParam(name = "quantity") int quantity, 
            @WebParam(name = "total_price") int total_price, 
            @WebParam(name = "consignee") String consignee, 
            @WebParam(name = "full_address") String full_address, 
            @WebParam(name = "postal_code") String postal_code, 
            @WebParam(name = "phone_number") String phone_number, 
            @WebParam(name = "ccn") String ccn, 
            @WebParam(name = "cvv") String cvv) {
        connectDB connDB = new connectDB();
        Connection conn = connDB.connectMP();
        request req = new request();
        ArrayList<String> vtoken = req.requestValidatedToken(token);
        
        int i = 0;
        PreparedStatement sql; 
        try {
            sql = conn.prepareStatement("INSERT INTO PURCHASE (id_product, id_buyer, product_name, price, quantity, total_price, consignee, full_address, postal_code, phone_number, credit_card_number, card_verification_value, date_purchased, time_purchased) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,CURDATE(),CURTIME())");
            sql.setString(1, id_product);
            sql.setString(2, id_buyer);
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
            Logger.getLogger(confirmPurchase.class.getName()).log(Level.SEVERE, null, ex);
        }
        map m = new map(vtoken.get(0), vtoken.get(1),i, null, null, null);
        connDB.closeConn();
        return m;
    }
}