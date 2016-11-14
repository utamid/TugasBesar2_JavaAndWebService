/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Marketplace;

import connection.connectDB;
import connection.request;
import item.item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
@WebService(serviceName = "editProduct")
public class editProduct {
    @WebMethod(operationName = "edittodb")
    public map editProductDB(@WebParam(name = "token") String token, @WebParam(name = "prod_id") String prod_id, @WebParam(name = "prodname") String prodname, @WebParam(name = "description") String description, @WebParam(name = "price") int price) {
        request req = new request();
        ArrayList<String> vtoken = req.requestValidatedToken(token);
        connectDB connDB = new connectDB();
        Connection conn = connDB.connectMP();

        int i = 0;
        PreparedStatement sql; 
        try {
            sql = conn.prepareStatement("UPDATE product SET name = ?, description = ?, price = ? WHERE id_product = ?");
            sql.setString(1, prodname);
            sql.setString(2, description);
            sql.setInt(3, price);
            sql.setString(4, prod_id);
            i = sql.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(editProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        map m = new map(vtoken.get(0), vtoken.get(1),i,null, null, null);
        connDB.closeConn();
        return m;
    }
        /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "showproduct")
        public map showproductinfo(@WebParam(name = "token") String token, @WebParam(name = "id_product") String id_product) {
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
                String desc = result.getString("description");
                String photo = result.getString("photo");
                int price = result.getInt("price");
                int idpro = result.getInt("id_product");
                x.setIdpro(idpro);
                x.setName(name);
                x.setPrice(price);
                x.setDesc(desc);
                x.setPhoto(photo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(editProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<item> list = new ArrayList();
        list.add(x);
        map m = new map(vtoken.get(0), vtoken.get(1), 0, list, null, null);
        connDB.closeConn();
        return m;
    }
}
