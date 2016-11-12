<%-- 
    Document   : catalog
    Created on : Nov 11, 2016, 10:28:00 PM
    Author     : vitra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <META http-equiv="Content-Type" content="text/html; charset=utf-8">
        <TITLE>Catalog - SaleProject</TITLE>
        <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
        <style>
            <%@include file="catalog.css"%>
        </style>
        <script>
            <%@include file ="catalog.js" %>
        </script>
    </head>
    <body>
        <h1>
            <span class="sale">Sale</span><span class="project">Project</span>
        </h1>
        <div class="hi">
            <p>Hi, $name!</p>
            <p class="logout"> <a href="login.php"> logout </a> </p>
        </div>
        <div class="menu"><ul>
            <li><a href="#" class="active">Catalog</a></li>
            <li><a href="product.jsp?id_user=$idus">Your Product</a></li>
            <li><a href="addProduct.jsp?id_user=$idus">Add Product</a></li>
            <li><a href="sales.jsp?id_user=$idus">Sales</a></li>
            <li><a href="purchases.jsp?id_user=$idus">Purchases</a></li>
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
                if (request.getMethod() == "POST") {
                    marketplace.Service_Service service = new marketplace.Service_Service();
                    marketplace.Service port = service.getServicePort();
                    // TODO initialize WS operation arguments here
                    java.lang.String idUser = "1";
                    java.lang.String search = request.getParameter("search");
                    java.lang.String searchBy = request.getParameter("search_button");
                    // TODO process result here
                    list = port.viewCatalogSearch(idUser, search, searchBy);
                } else {
                    marketplace.Service_Service service = new marketplace.Service_Service();
                    marketplace.Service port = service.getServicePort();
                     // TODO initialize WS operation arguments here
                    java.lang.String idUser = "1";
                    // TODO process result here
                    list = port.viewCatalog(idUser);
                }
                for (int i = 0; i < list.size(); i++) {
                    out.println("<div class=\"item\">" +
                                    "<p> <b>" + list.get(i).getDate() + "</b> <br>" +
                                    "at " + list.get(i).getTime() + "</p>" +
                                    "<hr>" +
                                    "<div class=\"item-photo\">" +
                                        "<img class=\"item-img\" src=\"" + list.get(i).getPhoto() + "\">" +
                                    "</div>" +
                                    "<div class=\"item-desc\">" +
                                        "<p> <span class=\"name\"> " + list.get(i).getName() + " </span> <br>" +
                                        "<span class=\"price\"> IDR " + list.get(i).getPrice() + " </span> <br>" +
                                        list.get(i).getDesc() + "</p>" +
                                    "</div>" +
                                    "<div class=\"item-edit\">" +
                                        "<p> " + list.get(i).getLikes() + " likes <br>" +
                                        list.get(i).getPurch() + " purchases </p>" +
                                        "<button class=\"edit\"> <a href=\"editProduct.php?id_user=$idus&id_product=" + list.get(i).getIdpro() + "\"> EDIT </a> </button>" +
                                        "<button name=\"delete\" class=\"delete\" onclick=\"Delete($idpro" + list.get(i).getIdpro() + ", $idus)\"> DELETE </button>" +
                                    "</div>" +
                                    "<hr class=\"line\">" +
                                "</div>");
                }
            } catch (Exception ex) {
            }
        %>
    </body>
</html>
