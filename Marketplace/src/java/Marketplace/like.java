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
 * @author vitra
 */
@WebService(serviceName = "like")
public class like {

    @WebMethod(operationName = "like")
    public map alterlike(@WebParam(name = "token") String token, @WebParam(name = "id_user") String idus, @WebParam(name = "id_product") String idpro, @WebParam(name = "id_like") int id_like) {
        request req = new request();
        ArrayList<String> vtoken = req.requestValidatedToken(token);
        connectDB connDB = new connectDB();
        Connection conn = connDB.connectMP();
        int result = 0;
        PreparedStatement sql;
        try {
            if (id_like == 0) {
                String query = "INSERT INTO likes(id_user, id_product) VALUES ('" + idus + "', '" + idpro + "')";
                sql = conn.prepareStatement(query);
            } else {
                String query = "DELETE FROM likes WHERE id_user = " + idus + " AND id_product = " + idpro;
                sql = conn.prepareStatement(query);
            }
            result = sql.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(like.class.getName()).log(Level.SEVERE, null, ex);
        }
        map m = new map(vtoken.get(0), vtoken.get(1),result, null, null, null);
        connDB.closeConn();
        return m;
    }
}
