/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Marketplace;

import connection.request;
import connection.connectDB;
import item.itempurchase;
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
@WebService(serviceName = "sales")
public class sales {
    
    @WebMethod(operationName = "viewSales")
    public map viewSales(@WebParam(name = "token") String token, @WebParam(name = "id_user") String idus) {
        ArrayList<itempurchase> list = new ArrayList();
        request req = new request();
        ArrayList<String> vtoken = req.requestValidatedToken(token);
        connectDB connDB = new connectDB();
        Connection conn = connDB.connectMP();
        
        PreparedStatement sql;
        try {
            sql = conn.prepareStatement("SELECT date_purchased, time_purchased, id_buyer, photo, product_name, purchase.price, "
                    + "quantity, total_price, consignee, purchase.full_address as f_add, "
                    + "purchase.postal_code as p_code, purchase.phone_number as p_num "
                    + "FROM purchase, product "
                    + "WHERE seller_id = ? "
                    + "AND purchase.id_product = product.id_product "
                    + "ORDER BY date_purchased DESC, time_purchased DESC");
            sql.setString(1, idus);
            
            ResultSet result = sql.executeQuery();
            while (result.next()) {
                Date phpdate = result.getDate("date_purchased");
                SimpleDateFormat df = new SimpleDateFormat("EEEE, dd MMMM yyyy");
                String date = df.format(phpdate);

                Time phptime = result.getTime("time_purchased");
                SimpleDateFormat tf = new SimpleDateFormat("hh.mm a");
                String time = tf.format(phptime);

                String photo = result.getString("photo");
                String prodname = result.getString("product_name");
                int price = result.getInt("price");
                int quantity = result.getInt("quantity");
                int total = result.getInt("total_price");
                
                String username = req.requestName(result.getString("id_buyer"));
                
                String consignee = result.getString("consignee");
                String add = result.getString("f_add");
                String postal_code = result.getString("p_code");
                String phone_number = result.getString("p_num");
                                
                itempurchase x = new itempurchase(date, time, photo, prodname, price, quantity, total, username, consignee, add, postal_code, phone_number);
                list.add(x);
            }
        } catch (SQLException ex) {
            Logger.getLogger(request.class.getName()).log(Level.SEVERE, null, ex);
        }
        map m = new map(vtoken.get(0), vtoken.get(1),0,null,list,null);
        connDB.closeConn();
        return m;
    }
}
