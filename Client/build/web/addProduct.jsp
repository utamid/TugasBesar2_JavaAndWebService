<%-- 
    Document   : addProduct
    Created on : Nov 13, 2016, 7:24:59 PM
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
	<title>Add Product - SaleProject</title>
	<link rel="stylesheet" type="text/css" href="addProduct.css"> 
	<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet"> 
	<style>
            <%@include file="CSS/addProduct.css"%>
        </style>
        <script>
            <%@include file="JS/addProduct.js"%>
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

                    String status1= (String) obj.get("status");                

                    HttpSession sessions = request.getSession();

                    sessions.setAttribute("status", status1);
                    response.sendRedirect("login.jsp");
                }
            %>
        </div>
	 <div class="menu"><ul>
            <li><a href="catalog.jsp">Catalog</a></li>
            <li><a href="product.jsp">Your Product</a></li>
            <li><a href="#" class="active">Add Product</a></li>
            <li><a href="sales.jsp">Sales</a></li>
            <li><a href="purchases.jsp">Purchases</a></li>
        </ul></div>
	<div class="box">
		<h2> Please add your product here </h2>
		<hr>
	</div>
        <div class="fill-form">
		<form name = "addProductForm" method = "post" onsubmit="return validateForm()">
			Name <br>
			<div id = "errname" class="err-msg"> </div>
			<input type="text" name="name"> <br>
			Description (max 200 chars) <br>
			<div id = "errdesc" class="err-msg"> </div>
			<textarea name="description" onkeypress = "return limitText()" onkeyup="return limitText()"> </textarea> <br>
			Price (IDR) <br>
			<div id = "errprice" class="err-msg"> </div>
			<input type="text" name="price" onkeypress="return validateNumber()"> <br>
			Photo <br>
			<div id = "errphoto" class="err-msg"> </div>
			<input type="file" name="photo"> <br>
			<input type="reset" name="cancel" value="CANCEL" onclick="return clear_err()">
			<input type="submit" name="add" value="ADD"> 
		</form>
	</div>
        <%
            if (request.getMethod() == "POST") {
                try {
                    marketplace.AddProduct_Service service = new marketplace.AddProduct_Service();
                    marketplace.AddProduct port = service.getAddProductPort();
                    marketplace.Map result = new marketplace.Map();
                    String prodname, description, photo;
                    int price;
                    int seller_id = Integer.parseInt(idus);
                    prodname = request.getParameter("name");
                    description = request.getParameter("description");
                    price = Integer.parseInt(request.getParameter("price"));
                    photo = request.getParameter("photo");

                    result =  port.insertnewproduct(token, prodname, price, description, photo, seller_id);
                    String status2 = result.getStatus();
                    if (status2.equals("valid")) {
                        HttpSession sessions = request.getSession();
                        sessions.setAttribute("token", result.getToken());
                        sessions.setAttribute("status", "valid");
                        int i = result.getObj();
                        if (i > 0) {
                            response.sendRedirect("product.jsp");
                        }
                    } else {
                        HttpSession sessions = request.getSession();
                        sessions.setAttribute("status", "invalid");
                        response.sendRedirect("login.jsp");
                    }
                } catch (Exception ex) {
                }
            }
            %>
</body>
</html>
