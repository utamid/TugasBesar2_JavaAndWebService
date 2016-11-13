<%-- 
    Document   : confirmpurchase
    Created on : Nov 12, 2016, 3:01:30 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmation Purchase - SaleProject</title>
        <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
        <style type="text/css">
            <%@include file="CSS/confirmpurchase.css"%>
        </style>
        <script type="text/javascript">
            <%@include file="JS/confirmPurchase.js"%>
        </script>

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
            <p class="logout"> <a href="login.jsp"> logout </a></p> 
        </div>
        <div class="menu"><ul>
            <li><a href="catalog.jsp">Catalog</a></li>
            <li><a href="product.jsp">Your Product</a></li>
            <li><a href="addProduct.jsp">Add Product</a></li>
            <li><a href="sales.jsp">Sales</a></li>
            <li><a href="purchases.jsp">Purchases</a></li>
         </ul></div>
        <div class="box">
                <h2> Please confirm your purchase</h2>
                <hr>
        </div>
        <div class="fill-form">
            <form method = "post" name = "confirmPurchaseForm" onsubmit="return validateForm()">
                <div class="item-desc">
                <%
                    try {
                        marketplace.ConfirmPurchase_Service service = new marketplace.ConfirmPurchase_Service();
                        marketplace.ConfirmPurchase port = service.getConfirmPurchasePort();
                        java.lang.String idProduct = request.getParameter("id_product");
                        marketplace.Item item = port.showProductInfo(idProduct);
                        out.println("<table>" +
                                    "<tr>" +
                                        "<td> Product </td>" +
                                        "<td> : " + item.getName() + "</td>" +
                                    "</tr>" +
                                    "<tr>" +
                                        "<td> Price </td>" +
                                        "<td> : IDR <input readonly type=\"text\" name=\"price\" class = \"read_only\" value= "+ item.getPrice() +"></td>" +
                                    "</tr>" +
                                    "<tr class=\"q\">" +
                                        "<td> Quantity </td>" +
                                        "<td> : <input type=\"text\" name =\"quantity\" class=\"quantity\" value = \"1\" onkeypress=\"return validateNumber()\" onkeydown=\"return multiplication("+ item.getPrice() +")\" onkeyup=\"return multiplication("+ item.getPrice() +")\">" + "pcs </td>" + 
                                    "</tr>" +
                                    "<tr>" +
                                        "<td> Total Price </td>" +
                                        "<td> : IDR <input readonly type=\"text\" name=\"total_price\" class = \"read_only\" value="+ item.getPrice() +"> </td>" +
                                    "</tr>" +
                                    "<tr>" +
                                        "<td> Delivery to </td>" +
                                        "<td> : </td>" +
                                    "</tr>" +
                                "</table>" +
                                "<div id = \"errq\" class=\"err-msg\"> </div>");
                    } catch (Exception ex) {
                    }
                %>   
                </div>
            <%
            try {
                marketplace.ConfirmPurchase_Service service = new marketplace.ConfirmPurchase_Service();
                marketplace.ConfirmPurchase port = service.getConfirmPurchasePort();
                java.lang.String idUser = idus;
                marketplace.User info = port.showBuyerInfo(idUser);
                out.println("<div class=\"con-form\">" +
                                "Consignee <br>" +
                                "<div id = \"errcons\" class=\"err-msg\"> </div>" +
                                "<input type=\"text\" name = \"consignee\" value = \""+ info.getFName() + "\" onkeypress=\"return validateAlphabet()\"> <br><br>" +
                                "Full Address<br>" +
                                "<div id = \"erraddr\" class=\"err-msg\"> </div>" +
                                "<textarea name = \"full_address\">"+ info.getAddress() +"</textarea> <br><br>" +
                                "Postal Code <br>" +
                                "<div id = \"errpost\" class=\"err-msg\"> </div>" +
                                "<input type=\"text\" name = \"postal_code\" onkeypress=\"return validateNumber() && limitText()\" onkeyup=\"return limitText()\" value = \"" + info.getPostalcode() + "\"> <br><br>" +
                                "Phone Number <br>" +
                                "<div id = \"errphone\" class=\"err-msg\"> </div>" +
                                "<input type=\"text\" name = \"phone_number\" onkeypress=\"return validateNumber()&& limitText()\" onkeyup=\"return limitText()\" value = \"" + info.getPhonenumber() + "\"> <br><br>"  
                );
            } catch (Exception ex) {
            }
            %>
                12 Digits Credit Card Number <br>
                <div id = "errccn" class="err-msg"> </div>
                <input type="text" name = "credit_card_number" onkeypress="return validateNumber() && limitText()" onkeyup="return limitText()"> <br><br>
                3 Digits Card Verification Value <br>
                <div id = "errcvv" class="err-msg"> </div>
                <input type="text" name = "verification_value" onkeypress="return validateNumber() && limitText()" onkeyup="return limitText()"> <br><br><br>
                <button class="cancel"> <a href="catalog.jsp?"> CANCEL </a> </button>
                <input type="submit" name="confirm" value = "CONFIRM">
            </div>
        <%
        if (request.getMethod() == "POST") {    
            try {
                marketplace.ConfirmPurchase_Service service = new marketplace.ConfirmPurchase_Service();
                marketplace.ConfirmPurchase port = service.getConfirmPurchasePort();
                String idpro = request.getParameter("id_product");
                String id_buyer = idus;
                marketplace.Item item = port.showProductInfo(idpro);

                java.lang.String pm = item.getName();
                int pr = item.getPrice();

                String q1 = request.getParameter("quantity");
                int quantity = Integer.parseInt(q1);

                int total_price = pr * quantity;
                java.lang.String consignee = request.getParameter("consignee");
                java.lang.String full_address = request.getParameter("full_address");
                java.lang.String postal_code = request.getParameter("postal_code");
                java.lang.String phone_number = request.getParameter("phone_number");
                java.lang.String ccn = request.getParameter("credit_card_number");
                java.lang.String cvv = request.getParameter("verification_value");

                int result = port.insertPurchase(idpro, id_buyer, pm, pr, quantity, total_price, consignee, full_address, postal_code, phone_number, ccn, cvv);
                if (result > 0) {
                    response.sendRedirect("purchases.jsp");
                }
            } catch (Exception ex) {
            }
        }
        %>
           </form>
        </div>
    </body>
</html>
