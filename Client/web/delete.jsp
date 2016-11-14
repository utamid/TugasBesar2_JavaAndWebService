<%-- 
    Document   : delete
    Created on : Nov 13, 2016, 9:34:22 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <body>
        <%
        try {
            String token = (String) request.getSession().getAttribute("token");
            marketplace.Delete_Service service = new marketplace.Delete_Service();
            marketplace.Delete port = service.getDeletePort();
            java.lang.String idProduct = request.getParameter("id_product");
            marketplace.Map result = port.deleteproduct(token, idProduct);
            if (result.getStatus().equals("valid")) {
                HttpSession sessions = request.getSession();
                sessions.setAttribute("token", result.getToken());
                sessions.setAttribute("status", "valid");
            } else {
                HttpSession sessions = request.getSession();
                sessions.setAttribute("status", "invalid");
                response.sendRedirect("login.jsp");
            }
            response.sendRedirect("product.jsp");
        } catch (Exception ex) {
        }
        %>
    </body>
</html>
