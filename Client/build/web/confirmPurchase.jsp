<%-- 
    Document   : confirmpurchase
    Created on : Nov 12, 2016, 3:01:30 PM
    Author     : User
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
                String token = (String) request.getSession().getAttribute("token");
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
                        marketplace.Map result = port.showProductInfo(token, idProduct);
                        String status = result.getStatus();
                        if (result.getStatus().equals("valid")) {
                            HttpSession sessions = request.getSession();
                            sessions.setAttribute("token", result.getToken());
                            sessions.setAttribute("status", "valid");
                        } else {
                            HttpSession sessions = request.getSession();
                            sessions.setAttribute("status", "invalid");
                            response.sendRedirect("login.jsp");
                        }
                        out.println("<table>" +
                                    "<tr>" +
                                        "<td> Product </td>" +
                                        "<td> : " + result.getListitem().get(0).getName() + "</td>" +
                                    "</tr>" +
                                    "<tr>" +
                                        "<td> Price </td>" +
                                        "<td> : IDR <input readonly type=\"text\" name=\"price\" class = \"read_only\" value= "+ result.getListitem().get(0).getPrice() +"></td>" +
                                    "</tr>" +
                                    "<tr class=\"q\">" +
                                        "<td> Quantity </td>" +
                                        "<td> : <input type=\"text\" name =\"quantity\" class=\"quantity\" value = \"1\" onkeypress=\"return validateNumber()\" onkeydown=\"return multiplication("+ result.getListitem().get(0).getPrice() +")\" onkeyup=\"return multiplication("+ result.getListitem().get(0).getPrice() +")\">" + "pcs </td>" + 
                                    "</tr>" +
                                    "<tr>" +
                                        "<td> Total Price </td>" +
                                        "<td> : IDR <input readonly type=\"text\" name=\"total_price\" class = \"read_only\" value="+ result.getListitem().get(0).getPrice() +"> </td>" +
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
                marketplace.Map result = port.showBuyerInfo(token, idUser);
                System.out.println("ccc");
                if (result.getStatus().equals("valid")) {
                    System.out.println("eee");
                    HttpSession sessions = request.getSession();
                    sessions.setAttribute("token", result.getToken());
                    sessions.setAttribute("status", "valid");
                    System.out.println("fff");
                } else {
                    System.out.println("xxx");
                    HttpSession sessions = request.getSession();
                    sessions.setAttribute("status", "invalid");
                    response.sendRedirect("login.jsp");
                }
                System.out.println("ddd");
                out.println("<div class=\"con-form\">" +
                                "Consignee <br>" +
                                "<div id = \"errcons\" class=\"err-msg\"> </div>" +
                                "<input type=\"text\" name = \"consignee\" value = \""+ result.getListuser().get(0).getFName() + "\" onkeypress=\"return validateAlphabet()\"> <br><br>" +
                                "Full Address<br>" +
                                "<div id = \"erraddr\" class=\"err-msg\"> </div>" +
                                "<textarea name = \"full_address\">"+ result.getListuser().get(0).getAddress() +"</textarea> <br><br>" +
                                "Postal Code <br>" +
                                "<div id = \"errpost\" class=\"err-msg\"> </div>" +
                                "<input type=\"text\" name = \"postal_code\" onkeypress=\"return validateNumber() && limitText()\" onkeyup=\"return limitText()\" value = \"" + result.getListuser().get(0).getPostalcode() + "\"> <br><br>" +
                                "Phone Number <br>" +
                                "<div id = \"errphone\" class=\"err-msg\"> </div>" +
                                "<input type=\"text\" name = \"phone_number\" onkeypress=\"return validateNumber()&& limitText()\" onkeyup=\"return limitText()\" value = \"" + result.getListuser().get(0).getPhonenumber() + "\"> <br><br>"  
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
                marketplace.Map result = port.showProductInfo(token, idpro);
                if (result.getStatus().equals("valid")) {
                    HttpSession sessions = request.getSession();
                    sessions.setAttribute("token", result.getToken());
                    sessions.setAttribute("status", "valid");
                } else {
                    HttpSession sessions = request.getSession();
                    sessions.setAttribute("status", "invalid");
                    response.sendRedirect("login.jsp");
                }
                java.lang.String pm = result.getListitem().get(0).getName();
                int pr = result.getListitem().get(0).getPrice();

                String q1 = request.getParameter("quantity");
                int quantity = Integer.parseInt(q1);

                int total_price = pr * quantity;
                java.lang.String consignee = request.getParameter("consignee");
                java.lang.String full_address = request.getParameter("full_address");
                java.lang.String postal_code = request.getParameter("postal_code");
                java.lang.String phone_number = request.getParameter("phone_number");
                java.lang.String ccn = request.getParameter("credit_card_number");
                java.lang.String cvv = request.getParameter("verification_value");

                marketplace.Map rs = port.insertPurchase(token, idpro, id_buyer, pm, pr, quantity, total_price, consignee, full_address, postal_code, phone_number, ccn, cvv);
                response.sendRedirect("purchases.jsp");
            } catch (Exception ex) {
            }
        }
        %>
           </form>
        </div>
    </body>
</html>
