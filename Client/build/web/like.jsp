<%-- 
    Document   : like
    Created on : Nov 13, 2016, 8:15:48 PM
    Author     : vitra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <%
        try {
            String token = (String) request.getSession().getAttribute("token");
            marketplace.Like_Service service = new marketplace.Like_Service();
            marketplace.Like port = service.getLikePort();
            java.lang.String idUser = (String) request.getSession().getAttribute("id_user");
            java.lang.String idProduct = request.getParameter("id_product");
            String idLike = request.getParameter("id_like");
            int id_like = Integer.parseInt(idLike);
            marketplace.Map result = port.like(token, idUser, idProduct, id_like);
            if (result.getStatus().equals("valid")) {
                HttpSession sessions = request.getSession();
                sessions.setAttribute("token", result.getToken());
                sessions.setAttribute("status", "valid");
            } else {
                HttpSession sessions = request.getSession();
                sessions.setAttribute("status", "invalid");
                response.sendRedirect("login.jsp");
            }
            response.sendRedirect("catalog.jsp");
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        %>
    </body>
</html>
