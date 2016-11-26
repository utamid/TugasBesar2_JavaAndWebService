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
@WebService(serviceName = "catalog")
public class catalog {

    /**
     * This is a sample web service operation
     * @param idus
     * @return 
     */
    @WebMethod(operationName = "viewCatalog")
    public map viewCatalog(@WebParam(name = "token") String token, @WebParam(name = "id_user") String idus) {
        ArrayList<item> list = new ArrayList();
        request req = new request();
        ArrayList<String> vtoken = req.requestValidatedToken(token);
        connectDB connDB = new connectDB();
        Connection conn = connDB.connectMP();
        PreparedStatement sql;
        try {
            sql = conn.prepareStatement("SELECT id_product, name, price, description, date_added, time_added, "
                    + "seller_id, photo, deleted FROM product "
                    + "ORDER BY date_added DESC, time_added DESC");
            ResultSet result = sql.executeQuery();
            while (result.next()) {
                Boolean deleted = result.getBoolean("deleted");
                if (!deleted) {
                    int idpro = result.getInt("id_product");
                    String iditem = "item" + idpro;
                    
                    int seller_id = result.getInt("seller_id");
                    String username = req.requestName(Integer.toString(seller_id));
                    
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
                    if (result1.next()) {
                        likes = result1.getInt("count(id_product)");
                    }
                    
                    PreparedStatement sql2 = conn.prepareStatement("SELECT sum(quantity) FROM purchase WHERE id_product = ?"); 
                    sql2.setString(1, Integer.toString(idpro));
                    ResultSet result2 = sql2.executeQuery();
                    int purch = 0;
                    if (result2.next()) {
                        purch = result2.getInt("sum(quantity)");
                    }
                    
                    PreparedStatement sql3 = conn.prepareStatement("SELECT * FROM likes WHERE id_product = ? AND id_user = ?");
                    sql3.setString(1, Integer.toString(idpro));
                    sql3.setString(2, idus);
                    ResultSet result3 = sql3.executeQuery();
                    String like;
                    if (result3.next()) {
                        like = "<button name=\"likes\" class=\"liked\" type=\"submit\"> <a href=\"like.jsp?id_product=" + idpro + "&id_like=1\"> LIKED </a> </button>";
                    } else {
                        like = "<button name=\"likes\" class=\"likes\" type=\"submit\"> <a href=\"like.jsp?id_product=" + idpro + "&id_like=0\"> LIKE </a> </button>";
                    }
                    item x = new item(idpro, iditem, username, date, time, photo, name, price, desc, likes, purch, like);
                    list.add(x);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(request.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        map m = new map(vtoken.get(0), vtoken.get(1), 0, list, null, null);
        connDB.closeConn();
        System.out.println("f");
        return m;
    }
    
    /**
     * This is a sample web service operation
     * @return 
     */
    @WebMethod(operationName = "viewCatalogSearch")
    public map viewCatalogSearch(@WebParam(name = "token") String token, @WebParam(name = "id_user") String idus, @WebParam(name = "search") String search, @WebParam(name = "search_by") String search_by) {
        ArrayList<item> list = new ArrayList();
        request req = new request();
        ArrayList<String> vtoken = req.requestValidatedToken(token);
        connectDB connDB = new connectDB();
        Connection conn = connDB.connectMP();
        
        PreparedStatement sql;
        try {
            if (search_by.equals("product")) {
                String query = "SELECT id_product, name, price, description, date_added, time_added, "
                                        + "seller_id, photo, deleted FROM product WHERE name like '%" + search + "%' "
                                        + "ORDER BY date_added DESC, time_added DESC";
                sql = conn.prepareStatement(query);
                ResultSet result = sql.executeQuery();
                while (result.next()) {
                    Boolean deleted = result.getBoolean("deleted");
                    if (!deleted) {
                        int idpro = result.getInt("id_product");
                        String iditem = "item" + idpro;

                        int seller_id = result.getInt("seller_id");
                        String username = req.requestName(Integer.toString(seller_id));

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
                        if (result1.next()) {
                            likes = result1.getInt("count(id_product)");
                        }

                        PreparedStatement sql2 = conn.prepareStatement("SELECT sum(quantity) FROM purchase WHERE id_product = ?"); 
                        sql2.setString(1, Integer.toString(idpro));
                        ResultSet result2 = sql2.executeQuery();
                        int purch = 0;
                        if (result2.next()) {
                            purch = result2.getInt("sum(quantity)");
                        }
                        
                        PreparedStatement sql3 = conn.prepareStatement("SELECT * FROM likes WHERE id_product = ? AND id_user = ?");
                        sql3.setString(1, Integer.toString(idpro));
                        sql3.setString(2, idus);
                        ResultSet result3 = sql3.executeQuery();
                        String like;
                        if (result3.next()) {
                            like = "<button name=\"likes\" class=\"liked\" type=\"submit\"> <a href=\"like.jsp?id_product=" + idpro + "&id_like=1\"> LIKED </button>";
                        } else {
                            like = "<button name=\"likes\" class=\"likes\" type=\"submit\"> <a href=\"like.jsp?id_product=" + idpro + "&id_like=0\"> LIKE </button>";
                        }
                        item x = new item(idpro, iditem, username, date, time, photo, name, price, desc, likes, purch, like);
                        list.add(x);
                    }
                }
            } else {
                ArrayList<String> id_user = req.requestIduser(search);
                for (int i = 0; i < id_user.size(); i++) {
                    System.out.println("ini = " + id_user.get(i));
                    String query = "SELECT id_product, name, price, description, date_added, time_added, "
                                            + "seller_id, photo, deleted FROM product WHERE seller_id = " + id_user.get(i)
                                            + " ORDER BY date_added DESC, time_added DESC";
                    sql = conn.prepareStatement(query);
                    ResultSet result = sql.executeQuery();
                    while (result.next()) {
                        Boolean deleted = result.getBoolean("deleted");
                        if (!deleted) {
                            int idpro = result.getInt("id_product");
                            String iditem = "item" + idpro;

                            int seller_id = result.getInt("seller_id");
                            request req1 = new request();
                            String username = req1.requestName(Integer.toString(seller_id));

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

                            PreparedStatement sql3 = conn.prepareStatement("SELECT * FROM likes WHERE id_product = ? AND id_user = ?");
                            sql3.setString(1, Integer.toString(idpro));
                            sql3.setString(2, idus);
                            ResultSet result3 = sql3.executeQuery();
                            String like;
                            if (result3.next()) {
                                like = "<button name=\"likes\" class=\"liked\" type=\"submit\"> <a href=\"like.jsp?id_product=" + idpro + "&id_like=1\"> LIKED </button>";
                            } else {
                                like = "<button name=\"likes\" class=\"likes\" type=\"submit\"> <a href=\"like.jsp?id_product=" + idpro + "&id_like=0\"> LIKE </button>";
                            }
                            item x = new item(idpro, iditem, username, date, time, photo, name, price, desc, likes, purch, like);
                            list.add(x);
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(request.class.getName()).log(Level.SEVERE, null, ex);
        }
        map m = new map(vtoken.get(0), vtoken.get(1), 0, list, null, null);
        connDB.closeConn();
        return m;
    }
}
