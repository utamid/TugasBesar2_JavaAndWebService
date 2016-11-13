<%-- 
    Document   : sales
    Created on : Nov 12, 2016, 4:15:20 PM
    Author     : vitra
--%>

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
                out.println("<p>Hi, " + name + "!</p>");
            %>
            <p class="logout"> <a href="login.php"> logout </a> </p>
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
             // TODO initialize WS operation arguments here
            java.lang.String idUser = idus;
            // TODO process result here
            java.util.List<marketplace.Itempurchase> list = port.viewSales(idUser);
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
