<%-- 
    Document   : purchase
    Created on : Nov 12, 2016, 6:21:14 PM
    Author     : vitra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <TITLE>Purchases - SaleProject</TITLE>
        <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
        <style>
            <%@include file="CSS/purchases.css"%>
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
                out.println("<p>Hi, " + name + "!</p>");
            %>
            <p class="logout"> <a href="login.php"> logout </a> </p>
        </div>
        <div class="menu"><ul>
            <li><a href="catalog.jsp">Catalog</a></li>
            <li><a href="product.jsp">Your Product</a></li>
            <li><a href="addProduct.jsp">Add Product</a></li>
            <li><a href="sales.jsp">Sales</a></li>
            <li><a href="#" class="active">Purchases</a></li>
        </ul></div>
        <div class="box">
            <h2>Here are your purchases</h2>
            <hr>
        </div>
        <%
        try {
            marketplace.Purchase_Service service = new marketplace.Purchase_Service();
            marketplace.Purchase port = service.getPurchasePort();
             // TODO initialize WS operation arguments here
            java.lang.String idUser = idus;
            // TODO process result here
            java.util.List<marketplace.Itempurchase> list = port.viewPurchases(idUser);
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
                                    "<p> bought from <b> " + list.get(i).getUsername() + " </b> </p>" +
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
