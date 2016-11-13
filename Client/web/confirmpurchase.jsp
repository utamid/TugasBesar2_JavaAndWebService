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
        <title> Confirmation Purchase - JSP Page</title>
        <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
        <style type="text/css">
            <%@include file="confirmpurchase.css"%>
        </style>
        <script type="text/javascript">
            <%@include file="confirmPurchase.js"%>
        </script>

    </head>
    <body>
                <h1>
			<span class="sale">Sale</span><span class="project">Project</span>
		</h1>
		<div class="hi">
                    <p>Hi, $usn!</p>
                    <p class="logout"> <a href="login.jsp"> logout </a></p> 
                
                    
l			
		</div>
		<div class="menu"><ul>
                    <li><a href="catalog.php?id_user=$id_user">Catalog</a></li>
                    <li><a href="product.php?id_user=$id_user">Your Product</a></li>
                    <li><a href="addProduct.php?id_user=$id_user">Add Product</a></li>
                    <li><a href="sales.php?id_user=$id_user">Sales</a></li>
                    <li><a href="purchases.php?id_user=$id_user">Purchases</a></li>
                 </ul></div>
		<div class="box">
			<h2> Please confirm your purchase</h2>
			<hr>
		</div>
                    <div class="fill-form">
			<form method = "post" name = "confirmPurchaseForm" onsubmit="return validateForm()">
                 
                    
                            <%
                               try {
                                   marketplace.Item item = new marketplace.Item();

                                   marketplace.Service_Service service = new marketplace.Service_Service();
                                   marketplace.Service port = service.getServicePort();
                                    // TODO initialize WS operation arguments here
                                    
                                   java.lang.String idProduct = "1";
                                   // TODO process result here
                                   item = port.menunjukkan(idProduct);
                                   System.out.println("nama = "+ item.getName());
                                   out.println("<div class=\"item-desc\">" +               
                                                                "<table>" +
								"<tr>" +
									"<td> Product </td>" +
									"<td> : "+ item.getName() + "</td>" +
								"</tr>" +
								"<tr>" +
									"<td> Price </td>" +
									"<td> : IDR <input readonly type=\"text\" name=\"price\" class = \"read_only\" value= "+ item.getPrice() +"></td>" +
								"</tr>" +
								"<tr class=\"q\">" +
									"<td> Quantity </td>" +
									"<td> : <input type=\"text\" name =\"quantity\" class=\"quantity\" value = \"1\" onkeypress=\"return validateNumber()\" onkeydown=\"return multiplication($price)\" onkeyup=\"return multiplication($price)\">" + "pcs </td>" + 
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
                                marketplace.Service_Service service = new marketplace.Service_Service();
                                marketplace.Service port = service.getServicePort();
                                 // TODO initialize WS operation arguments here
                                int idUser = 24;
                                // TODO process result here
                                marketplace.User result = port.showbuyer(idUser);
                                out.println("<div class=\"con-form\">" +
                                    "Consignee <br>" +
                                    "<div id = \"errcons\" class=\"err-msg\"> </div>" +
                                    "<input type=\"text\" name = \"consignee\" value = "+ result.getFName() + " onkeypress=\"return validateAlphabet()\"> <br><br>" +
                                    "Full Address<br>" +
                                    "<div id = \"erraddr\" class=\"err-msg\"> </div>" +
                                    "<textarea name = \"full_address\">"+ result.getAddress() +"</textarea> <br><br>" +
                                    "Postal Code <br>" +
                                    "<div id = \"errpost\" class=\"err-msg\"> </div>" +
                                    "<input type=\"text\" name = \"postal_code\" onkeypress=\"return validateNumber() && limitText()\" onkeyup=\"return limitText()\" value = " + result.getPostalcode() + "> <br><br>" +
                                    "Phone Number <br>" +
                                    "<div id = \"errphone\" class=\"err-msg\"> </div>" +
                                    "<input type=\"text\" name = \"phone_number\" onkeypress=\"return validateNumber()&& limitText()\" onkeyup=\"return limitText()\" value = " + result.getPhonenumber() + "> <br><br>"  
                                );
                            } catch (Exception ex) {
                                // TODO handle custom exceptions here
                            }
                            %>
    


                            12 Digits Credit Card Number <br>
                            <div id = "errccn" class="err-msg"> </div>
                            <input type="text" name = "credit_card_number" onkeypress="return validateNumber() && limitText()" onkeyup="return limitText()"> <br><br>
                            3 Digits Card Verification Value <br>
                            <div id = "errcvv" class="err-msg"> </div>
                            <input type="text" name = "verification_value" onkeypress="return validateNumber() && limitText()" onkeyup="return limitText()"> <br><br><br>
                            <button class="cancel"> <a href="catalog.php?id_user="> CANCEL </a> </button>
                            <input type="submit" name="confirm" value = "CONFIRM">
                            
                                <%
                                if (request.getMethod() == "POST") {    
                                    try {
                                        
                                        marketplace.Item item = new marketplace.Item();
                                        marketplace.Service_Service service = new marketplace.Service_Service();
                                        marketplace.Service port = service.getServicePort();
                                         // TODO initialize WS operation arguments here
                                         int idpro = 1;
                                         int id_buyer = 24;
                                         item = port.menunjukkan(((Integer) idpro).toString());
                                        
                                         java.lang.String pm = item.getName();
                                        int pr = item.getPrice();

                                        String q1 = request.getParameter("quantity");
                                        int quantity = Integer.parseInt(q1);
                                        
                                        int total_price = pr * quantity;
                                        System.out.println("total = " + total_price);
                                         java.lang.String consignee = request.getParameter("consignee");
                                         java.lang.String full_address = request.getParameter("full_address");
                                         java.lang.String postal_code = request.getParameter("postal_code");
                                         java.lang.String phone_number = request.getParameter("phone_number");
                                         java.lang.String ccn = request.getParameter("credit_card_number");
                                         java.lang.String cvv = request.getParameter("verification_value");
                                     
                                        // TODO process result here
                                       
                                        int result = port.purchase(idpro,id_buyer,pm,pr,quantity,total_price,consignee,full_address,postal_code,phone_number,ccn,cvv);
                                        if (result > 0) {
                                            response.sendRedirect("catalog.jsp?idus="+id_buyer);
                                        }
                                       
                                    } catch (Exception ex) {
                                        // TODO handle custom exceptions here
                                    }
                                }
                                %>

				</div>
			</form>
		</div>
    </body>
</html>
