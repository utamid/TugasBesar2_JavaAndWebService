<%-- 
    Document   : sales
    Created on : Nov 12, 2016, 4:15:20 PM
    Author     : vitra
--%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.DataOutputStream"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <TITLE>Sales - SaleProject</TITLE>
        <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
        <style>
            <%@include file="CSS/sales.css"%>
        </style>
    </head>
    <body>
        <h1>
            <span class="sale">Sale</span><span class="project">Project</span>
        </h1>
        <div class="hi">
            <%
                String name = (String) request.getSession().getAttribute("username");
                String idus = (String) request.getSession().getAttribute("id_user");
                String token = (String) request.getSession().getAttribute("token");
                String status1 = (String) request.getSession().getAttribute("status");
                if (status1.equals("invalid")) {
                    response.sendRedirect("login.jsp");
                }
                out.println("<p>Hi, " + name + "!</p>");
            %>
            <form method="POST" name="logout">
                <input type="submit" value ="logout" name="logout" class = "logout" >
            </form>
            <%
                if(request.getParameter("logout")!=null) {
                    String USER_AGENT = "Mozilla/5.0";

                    String url = "http://localhost:24488/identityservice/logout";

                    URL connection = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) connection.openConnection();

                    con.setRequestMethod("POST");
                    con.setRequestProperty("User-Agent", USER_AGENT);
                    con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
                    String urlParameters = "id_user="+idus;
                    // Send post request

                    con.setDoOutput(true);
                    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                    wr.writeBytes(urlParameters);
                    wr.flush();
                    wr.close();
                    int responseCode = con.getResponseCode();
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuilder resp = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                            resp.append(inputLine);
                    }
                    in.close();
                    JSONParser parser = new JSONParser();

                    JSONObject obj = (JSONObject) parser.parse(resp.toString());

                    String status= (String) obj.get("status");                

                    HttpSession sessions = request.getSession();

                    sessions.setAttribute("status", status);
                    response.sendRedirect("login.jsp");
                }
            %>
        </div>
        <div class="menu"><ul>
            <li><a href="catalog.jsp">Catalog</a></li>
            <li><a href="product.jsp">Your Product</a></li>
            <li><a href="addProduct.jsp">Add Product</a></li>
            <li><a href="#" class="active">Sales</a></li>
            <li><a href="purchases.jsp">Purchases</a></li>
        </ul></div>
        <div class="box">
            <h2>Here are your sales</h2>
            <hr>
        </div>
        <%
        try {
            marketplace.Sales_Service service = new marketplace.Sales_Service();
            marketplace.Sales port = service.getSalesPort();
            java.lang.String idUser = idus;
            marketplace.Map result = port.viewSales(token, idUser);
            if (result.getStatus().equals("valid")) {
                HttpSession sessions = request.getSession();
                sessions.setAttribute("token", result.getToken());
                sessions.setAttribute("status", "valid");
            } else {
                HttpSession sessions = request.getSession();
                sessions.setAttribute("status", "invalid");
                response.sendRedirect("login.jsp");
            }
            java.util.List<marketplace.Itempurchase> list = result.getListpurchase();
            for (int i = 0; i < list.size(); i++) {
                out.println("<div class=\"item\">" +
                                "<p> <b>" + list.get(i).getDate() + "</b> <br>" +
                                "at " + list.get(i).getTime() + "</p> <hr>" +
                                "<div class=\"item-photo\">" +
                                    "<img class=\"item-img\" src=\"" + list.get(i).getPhoto() + "\">" +
                                "</div>" +
                                "<div class=\"item-desc\">" +
                                    "<p> <span class=\"name\"> " + list.get(i).getProdname() + " </span> <br>" +
                                    "<span class=\"price\"> IDR " + list.get(i).getTotal() + " <br>" +
                                    list.get(i).getQuantity() + " pcs <br>" +
                                    "@" + list.get(i).getPrice() + " <br>" +
                                    "</span>" +
                                    "<p> bought by <b> " + list.get(i).getUsername() + " </b> </p>" +
                                "</div>" +
                                "<div class=\"item-deliv\">" +
                                    "<p> Delivery to <b> " + list.get(i).getConsignee() + " </b> <br>" +
                                    list.get(i).getAdd() + " <br>" +
                                    list.get(i).getPostalcode() + " <br>" +
                                    list.get(i).getPhonenumber() + "<br> </p>" +
                                "</div>" +
                            "</div>");
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        %>
    </body>
</html>
