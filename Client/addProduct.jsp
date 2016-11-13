<%-- 
    Document   : addProduct
    Created on : Nov 13, 2016, 7:24:59 PM
    Author     : utamidias
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
 
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
                out.println("<p>Hi, " + name + "!</p>");
            %>
		<p class="logout"> <a href="login.jsp"> logout </a> </p>
	</div>
	 <div class="menu"><ul>
            <li><a href="catalog.jsp">Catalog</a></li>
            <li><a href="product.jsp">Your Product</a></li>
            <li><a href="#" class="active">Add Product</a></li>
            <li><a href="sales.jsp" >Sales</a></li>
            <li><a href="purchases.jsp">Purchases</a></li>
        </ul></div>
	<div class="box">
		<h2> Please add your product here </h2>
		<hr>
	</div>
            <%
                marketplace.Item item = new marketplace.Item();
                marketplace.Service_Service service = new marketplace.Service_Service();
                marketplace.addProduct port = addProduct.getServicePort();
                
            %>
            <%
                if (request.getMethod() == "POST") {
                    String prodname, description, photo;
                    int price;
                    String photourl = request.getParameter("photo");
                    String seller_id = idus;
                    prodname = request.getParameter("name");
                    description = request.getParameter("description");
                    price = Integer.parseInt(request.getParameter("price"));
                    photo = request.getParameter("photo");

                    int result =  port.insertnewprod(prodname, price, description, photourl, seller_id);;
                    
                    if (result > 0) {
                        response.sendRedirect("product.jsp");
                    }
                }
            %>
            
	<div class="fill-form">
		<form name = "addProductForm" method = "post" onsubmit="return validateForm()" ontype enctype="multipart/form-data">
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
</body>
</html>
