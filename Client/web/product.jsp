<%-- 
    Document   : product
    Created on : Nov 11, 2016, 2:22:33 AM
    Author     : vitra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <TITLE>Your Product - SaleProject</TITLE>
        <link rel="stylesheet" type="text/css" href="product.css">
        <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
        <script src="product.js" type="text/javascript"></script>
    </head>
    <body>
        <h1>
            <span class="sale">Sale</span><span class="project">Project</span>
        </h1>
        <div class="hi">
            <p>Hi, $name!</p>
            <p class="logout"> <a href="#"> logout </a> </p>
        </div>
        <div class=\"menu\"><ul>
            <li><a href="catalog.php?id_user=$idus">Catalog</a></li>
            <li><a href="#" class="active">Your Product</a></li>
            <li><a href="addProduct.php?id_user=$idus">Add Product</a></li>
            <li><a href="sales.php?id_user=$idus">Sales</a></li>
            <li><a href="purchases.php?id_user=$idus">Purchases</a></li>
        </ul></div>
        <div class="box">
            <h2>What are you going to sell today?</h2>
            <hr>
        </div>
        
    </body>
</html>
