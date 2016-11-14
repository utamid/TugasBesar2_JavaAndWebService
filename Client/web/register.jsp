<%-- 
    Document   : register
    Created on : Nov 11, 2016, 12:13:55 AM
    Author     : User
--%>

<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.DataOutputStream"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="java.net.URL"%>
<%
    // HTTP POST request
            String fullname;
            String username;
            String mail;
            String pass;
            String confpass;
            String address;
            String postcode;
            String phone;
            String errorMessage="";
            String errorMessage1="";
            
            if(request.getParameter("register")!=null) {
                fullname = request.getParameter("fullname");
                username = request.getParameter("username");
                mail = request.getParameter("mail");
                pass = request.getParameter("pass");
                confpass = request.getParameter("confpass");
                address = request.getParameter("address");
                postcode = request.getParameter("postcode");
                phone = request.getParameter("phone");
                String USER_AGENT = "Mozilla/5.0";

                String url = "http://localhost:24488/identityservice/Register";
                URL connection = new URL(url);
                HttpURLConnection con = (HttpURLConnection) connection.openConnection();
                
                con.setRequestMethod("POST");
                con.setRequestProperty("User-Agent", USER_AGENT);
                con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

                String urlParameters = "fullname="+fullname+
                                       "&username="+username+
                                       "&mail="+mail+
                                       "&pass="+pass+
                                       "&confpass="+confpass+
                                       "&address="+address+
                                       "&postcode="+postcode+
                                       "&phone="+phone; 
                // Send post request
                con.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.writeBytes(urlParameters);
                wr.flush();
                wr.close();

                int responseCode = con.getResponseCode();

                
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder resp = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                        resp.append(inputLine);
                }
                in.close();
                
                JSONParser parser = new JSONParser();
                JSONObject obj = (JSONObject) parser.parse(resp.toString());
                HttpSession sessions = request.getSession();
                String status= (String) obj.get("status");
                System.out.println("status "+ status);
                if(status.equals("valid")){
                    String user_id = (String) obj.get("id_user");
                    String token= (String) obj.get("token");
                    String user_name = (String) obj.get("username");

                    sessions.setAttribute("token", token);
                    sessions.setAttribute("id_user",user_id);
                    sessions.setAttribute("username", user_name);
                    sessions.setAttribute("status",status);
                    response.sendRedirect("catalog.jsp");
                    
                }
                else if (status.equals("invalid password")){
                    sessions.setAttribute("status",status);
                    errorMessage1= "Password is Invalid";
                }
                else if (status.equals("invalid")){
                    sessions.setAttribute("status",status);
                    errorMessage= "Invalid";
                }
            }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register - Sale Project</title>
        <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
        <style type="text/css">
            <%@include file = "CSS/register.css"%>
        </style>
        <script type="text/javascript">
            <%@include file = "JS/register.js"%>
        </script>
    </head>
    <body>
    <h1>
        <span class="sale">Sale</span><span class="project">Project</span>
    </h1>    
        <div class="box">
            <h2>Please register</h2>
            <hr>
            <div>
                <form name="register" method="POST" onsubmit="return validate()">
                    Full Name<br> 
                    <div id = "errfn" class="err-msg"> </div>
                    <input type="text" name="fullname">
                    <br>
                    Username<br>
                    <div id = "errun" class="err-msg"> </div>
                    <input type="text" name="username">
                    <br>
                    Email<br>
                    <div id = "errmail" class="err-msg"></div>
                    <input type="text" name="mail">
                    <br>
                    Password<br>
                    <div id = "errpass" class="err-msg"> <%=errorMessage1%></div>
                    <input type="password" name="pass">
                    <br>
                    Confirm Password<br>
                    <div id = "errcpass" class="err-msg"> <%=errorMessage1%> </div>
                    <input type="password" name="confpass">
                    <br>
                    Full Address<br>
                    <div id = "erraddr" class="err-msg"> </div>
                    <textarea name="address"></textarea>
                    <br>
                    Postal Code<br>
                    <div id = "errpc" class="err-msg"> </div>
                    <input type="text" name="postcode">
                    <br>	
                    Phone Number<br>
                    <div id = "errpn" class="err-msg"> </div>
                    <input type="tel" name="phone">  <br><br>
                    <%=errorMessage%> <br><br>
                    <div class="regis">
                        <p>Already have account? Login <a href="login.jsp">here</a> </p>
                    </div>
                    <input class="button" type="submit" value="REGISTER" name = "register">
                   
                </form>
            </div>
        </div>
    </body>
</html>
