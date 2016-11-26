/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package identityservice;

import connection.connectDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author vitra
 */
@WebServlet(name = "validateToken", urlPatterns = {"/validateToken"})
public class validateToken extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JSONObject obj = new JSONObject();
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String token = request.getParameter("token");
            
            connectDB connDB = new connectDB();
            Connection conn = connDB.connectIS();
            
            String query = "SELECT * FROM token WHERE token = '" + token + "'";
            try {
                PreparedStatement ps = conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    query = "SELECT * FROM token WHERE token = '" + token + "' AND expirydate > NOW()";
                    ps = conn.prepareStatement(query);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        int id_user = rs.getInt("id_user");
                        String newtoken = UUID.randomUUID().toString().replaceAll("-", "");
                        String newQuery = "UPDATE token SET token = '" +newtoken+ "', expirydate = NOW() + INTERVAL 10 MINUTE WHERE id_user = " + id_user;
                        PreparedStatement ps1 = conn.prepareStatement(newQuery);
                        int result = ps1.executeUpdate();
                        if (result > 0) {
                            obj.put("status", "valid");
                            obj.put("token", newtoken);
                        }
                    } else {
                        query = "SELECT * FROM token WHERE token = '" + token + "'";
                        ps = conn.prepareStatement(query);
                        rs = ps.executeQuery();
                        if (rs.next()) {
                            int id_user = rs.getInt("id_user");
                            String newQuery = "DELETE FROM token WHERE id_user = " + id_user;
                            PreparedStatement ps1 = conn.prepareStatement(newQuery);
                            int result = ps1.executeUpdate();
                            if (result > 0) {
                                obj.put("status", "invalid");
                                obj.put("token", token);
                            }
                        }
                    }
                } else {
                    obj.put("status", "invalid");
                    obj.put("token", token);
                }
            }
            catch (SQLException ex) {
                
            }
            
            connDB.closeConn();
            response.setContentType("application/json:charset=UTF-8");
            response.getWriter().write(obj.toString());
        } catch (JSONException ex) {
            Logger.getLogger(username.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
