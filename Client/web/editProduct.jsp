<%-- 
    Document   : editProduct
    Created on : Nov 13, 2016, 9:16:14 PM
    Author     : utamidias
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
	<title>Edit Product - SaleProject</title>
	<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet"> 
	<style>
            <%@include file="CSS/editProduct.css"%>
        </style>
        <script>
            <%@include file="JS/editProduct.js"%>
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
            <li><a href="sales.jsp" >Sales</a></li>
            <li><a href="purchases.jsp">Purchases</a></li>
         </ul></div>
        <div class="box">
                <h2> Please update your product here </h2>
                <hr>
        </div>
        <%
            try {
                marketplace.EditProduct_Service service = new marketplace.EditProduct_Service();
                marketplace.EditProduct port = service.getEditProductPort();
                java.lang.String idProduct = request.getParameter("id_product");
                marketplace.Map result = port.showproduct(token, idProduct);
                if (result.getStatus().equals("valid")) {
                    HttpSession sessions = request.getSession();
                    sessions.setAttribute("token", result.getToken());
                    sessions.setAttribute("status", "valid");
                } else {
                    HttpSession sessions = request.getSession();
                    sessions.setAttribute("status", "invalid");
                    response.sendRedirect("login.jsp");
                }
                marketplace.Item item = result.getListitem().get(0);
                out.println("<div class=\"fill-form\">" +
                        "<form name = \"editProductForm\" method = \"post\" onsubmit= \"return validateForm()\">" +
                        "Name <br>" +
                        "<div id = \"errname\" class=\"err-msg\"> </div>" +
                        "<input type=\"text\" name = \"name\" value= " + item.getName() +"> <br>" +
                        "Description (max 200 chars) <br>" +
                        "<div id = \"errdesc\" class=\"err-msg\"> </div>" +
                        "<textarea name = \"description\" onkeypress = \"return limitText()\" onkeyup=\"return limitText()\" >" + item.getDesc() + "</textarea> <br>" +
                        "Price (IDR) <br>" +
                        "<div id = \"errprice\" class=\"err-msg\"> </div>" +
                        "<input type=\"text\" name = \"price\" onkeypress=\"return validateNumber()\" value=" + item.getPrice() + "> <br>" +
                        "Photo <br>" +
                        "<div class=\"photo-box\">" +
                        "<input type=\"button\" name= \"photo\" value = \"Choose File\" onclick=\"return disableButton()\"> <span class = \"photo-name\">" + item.getPhoto() + "</span><br>" +
                        "</div>" +
                        "<button class=\"cancel\"> <a href=\"product.jsp\"> CANCEL </a> </button>" +
                        "<input type=\"submit\" name=\"add\" value = \"UPDATE\">" + 
                        "</form>" +
                    "</div>");
            } catch (Exception ex) {
                // TODO handle custom exceptions here
            }
        %>
        <%
        if (request.getMethod() == "POST") {
            try {
                marketplace.EditProduct_Service service = new marketplace.EditProduct_Service();
                marketplace.EditProduct port = service.getEditProductPort();
                 // TODO initialize WS operation arguments here
                java.lang.String idProduct = request.getParameter("id_product");
                java.lang.String prodname = request.getParameter("name");
                java.lang.String description = request.getParameter("description");
                int price = Integer.parseInt(request.getParameter("price"));
                // TODO process result here
                marketplace.Map result = port.edittodb(token, idProduct, prodname, description, price);
                response.sendRedirect("product.jsp");
            } catch (Exception ex) {
                // TODO handle custom exceptions here
            }
        }
        %>
    </body>
</html>

