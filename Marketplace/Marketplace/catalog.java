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
    public ArrayList<item> viewCatalog(@WebParam(name = "id_user") String idus) {
        ArrayList<item> list = new ArrayList();
        
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
                    request req = new request();
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
                        like = "<button name=\"likes\" value=\"like" + idpro + "\" class=\"liked\" type=\"submit\"> LIKED </button>";
                    } else {
                        like = "<button name=\"likes\" value=\"like" + idpro + "\" class=\"likes\" type=\"submit\"> LIKE </button>";
                    }
                    item x = new item(idpro, iditem, username, date, time, photo, name, price, desc, likes, purch, like);
                    list.add(x);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(request.class.getName()).log(Level.SEVERE, null, ex);
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
                        request req = new request();
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
                            like = "<button name=\"likes\" class=\"liked\" onclick=\"return alterLikes(" + idus + ", " + idpro + ", 0, " + purch + ")\"> LIKED </button>";
                        } else {
                            like = "<button name=\"likes\" class=\"likes\" onclick=\"return alterLikes(" + idus + ", " + idpro + ", 0, " + purch + ")\"> LIKE </button>";
                        }
                        item x = new item(idpro, iditem, username, date, time, photo, name, price, desc, likes, purch, like);
                        list.add(x);
                    }
                }
            } else {
                request req = new request();
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
                                like = "<button name=\"likes\" class=\"liked\" onclick=\"return alterLikes(" + idus + ", " + idpro + ", 0, " + purch + ")\"> LIKED </button>";
                            } else {
                                like = "<button name=\"likes\" class=\"likes\" onclick=\"return alterLikes(" + idus + ", " + idpro + ", 0, " + purch + ")\"> LIKE </button>";
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
        connDB.closeConn();
        return list;
    }
}
