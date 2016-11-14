/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Marketplace;

import connection.connectDB;
import connection.request;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
 * @author User
 */
@WebService(serviceName = "Delete")
public class Delete {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "deleteproduct")
    public map deleteproduct(@WebParam(name = "token") String token, @WebParam(name = "id_product") String id_product) {
        request req = new request();
        ArrayList<String> vtoken = req.requestValidatedToken(token);
        connectDB connDB = new connectDB();
        Connection conn = connDB.connectMP();
        
        int i = 0;
        PreparedStatement sql; 
        try {
            sql = conn.prepareStatement("UPDATE product SET deleted = true WHERE id_product = ?");
            sql.setString(1, id_product);
            i = sql.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Delete.class.getName()).log(Level.SEVERE, null, ex);
        }
        map m = new map(vtoken.get(0), vtoken.get(1), i, null, null, null);
        connDB.closeConn();
        return m;
    }
}
