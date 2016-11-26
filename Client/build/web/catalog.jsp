<%-- 
    Document   : catalog
    Created on : Nov 11, 2016, 10:28:00 PM
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
        <META http-equiv="Content-Type" content="text/html; charset=utf-8">
        <TITLE>Catalog - SaleProject</TITLE>
        <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
        <style>
            <%@include file="CSS/catalog.css"%>
        </style>
        <script>
            <%@include file ="JS/catalog.js" %>
        </script>
    </head>
    <body>
        <h1>
            <span class="sale">Sale</span><span class="project">Project</span>
        </h1>
        <div class="hi">
            <%
                String name = (String) request.getSession().getAttribute("username");
                String token = (String) request.getSession().getAttribute("token");
                String idus = (String) request.getSession().getAttribute("id_user");
                String status = (String) request.getSession().getAttribute("status");
                if (status.equals("invalid")) {
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
                    HttpSession sessions = request.getSession();
                    String status1 = (String) obj.get("status"); 
                    sessions.setAttribute("status", status1);
                    response.sendRedirect("login.jsp");
                }
            %>
        </div>
        <div class="menu"><ul>
            <li><a href="#" class="active">Catalog</a></li>
            <li><a href="product.jsp">Your Product</a></li>
            <li><a href="addProduct.jsp">Add Product</a></li>
            <li><a href="sales.jsp">Sales</a></li>
            <li><a href="purchases.jsp">Purchases</a></li>
        </ul></div>
        <div class="box">
            <h2>What are you going to buy today?</h2>
            <hr>
        </div>
        <div class="search-form">
            <form name="searchCatalogForm" method="post" onsubmit="return validateForm()">
                <div>
                    <input type="search" name="search" placeholder="Search catalog ...">
                    <input type="submit" name="go" value="GO">
                </div>
                <div class="by">
                    by
                </div>
                <div class="radio-button">
                    <input type="radio" name="search_button" value="product" checked> product <br>
                    <input type="radio" name="search_button" value="store"> store <br>
                </div>
                <div id = "errmsg" class="err-msg"> </div>
            </form>
        </div>
        <%
            try {
                java.util.List<marketplace.Item> list = new java.util.ArrayList();
                marketplace.Map result = new marketplace.Map();
                if (request.getMethod() == "POST") {
                    marketplace.Catalog_Service service = new marketplace.Catalog_Service();
                    marketplace.Catalog port = service.getCatalogPort();
                    java.lang.String idUser = idus;
                    java.lang.String search = request.getParameter("search");
                    java.lang.String searchBy = request.getParameter("search_button");
                    result = port.viewCatalogSearch(token, idUser, search, searchBy);
                } else {
                    marketplace.Catalog_Service service = new marketplace.Catalog_Service();
                    marketplace.Catalog port = service.getCatalogPort();
                    java.lang.String idUser = idus;
                    result = port.viewCatalog(token, idUser);
                }
                if (result.getStatus().equals("valid")) {
                    HttpSession sessions = request.getSession();
                    sessions.setAttribute("token", result.getToken());
                    sessions.setAttribute("status", "valid");
                } else {
                    HttpSession sessions = request.getSession();
                    sessions.setAttribute("status", "invalid");
                    response.sendRedirect("login.jsp");
                }
                
                list = result.getListitem();
                
                for (int i = 0; i < list.size(); i++) {
                    out.println("<div class=\"item\">" +
                                    "<p class=\"usn\"> <b>" + list.get(i).getUsername() + " </b> <br>" +
                                    "added this on " + list.get(i).getDate() + ", at " + list.get(i).getTime() + "</p>" +
                                    "<hr>" +
                                    "<div class=\"item-photo\">" +
                                        "<img class=\"item-img\" src=\"" + list.get(i).getPhoto() + "\">" +
                                    "</div>" +
                                    "<div class=\"item-desc\">" +
                                        "<p> <span class=\"name\"> " + list.get(i).getName() + " </span> <br>" +
                                        "<span class=\"price\"> IDR " + list.get(i).getPrice() + " </span> <br>" +
                                        list.get(i).getDesc() + " </p>" +
                                    "</div>" +
                                    "<div  class=\"item-like\">" +
                                        "<p> " + list.get(i).getLikes() + " likes <br>" +
                                        list.get(i).getPurch() + " purchases </p>" +
                                        list.get(i).getLike() +
                                        "<button class=\"buy\"> <a href=\"confirmPurchase.jsp?id_product=" + list.get(i).getIdpro() + "\"> BUY </a> </button>" +
                                    "</div>" +
                                    "<hr class=\"line\">" +
                                "</div>");
                }
            } catch (Exception ex) {
            }
        %>
    </body>
</html>
