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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import org.json.JSONObject;

/**
 *
 * @author User
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class login extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Login</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        JSONObject JSObj = new JSONObject();
        try (PrintWriter out = response.getWriter()) {
            String a = request.getParameter("usn");
            String b = request.getParameter("pass"); 
            int id_user = loginDB.validate(a, b);
            if  (id_user == 0) {
                JSObj.put("status","invalid");
            } else {
                String token = UUID.randomUUID().toString().replaceAll("-", "");
                
                JSObj.put("token", token);
                JSObj.put("id_user",((Integer)id_user));
                  
                connectDB connDB = new connectDB();
                Connection conn = connDB.connectIS();
                String query = "INSERT INTO token VALUES ('"+ id_user+"','"+ token +"',NOW())";
                try {
                    //connect to datbabase
                    PreparedStatement ps = conn.prepareStatement(query);
                        int i=ps.executeUpdate();
                        
                }
                  catch (SQLException ex) {
                        System.out.println("Register failed: An Exception has occurred! " + ex);
                    }

                    finally {
                        if (conn!=null) {
                           try {
                               conn.close();
                           }
                           catch (SQLException ex) {}
                                    conn = null;
                        }
                    }
   
                    JSObj.put("status", "valid");
            }
            response.setContentType("application/json:charset=UTF-8");
            response.getWriter().write(JSObj.toString());
             
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        

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
