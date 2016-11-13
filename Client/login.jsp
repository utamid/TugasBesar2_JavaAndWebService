<%-- 
    Document   : login
    Created on : Nov 10, 2016, 11:58:20 PM
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
            String usn;
            String pass;
            String errorMessage="";
            
            if(request.getParameter("login")!=null) {
                usn = request.getParameter("usn");
                pass = request.getParameter("pass");
                String USER_AGENT = "Mozilla/5.0";
                
                String url = "http://localhost:24488/identityservice/login";
                URL connection = new URL(url);
                HttpURLConnection con = (HttpURLConnection) connection.openConnection();
               
                con.setRequestMethod("POST");
                con.setRequestProperty("User-Agent", USER_AGENT);
                con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
                String urlParameters = "usn="+usn+
                                       "&pass="+pass;
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
                
                String token= (String) obj.get("token");
                Long iduser = (Long) obj.get("id_user");
                String id_user= Long.toString(iduser);
                String username= (String) obj.get("username");
                HttpSession sessions = request.getSession();
                sessions.setAttribute("token", token);
                sessions.setAttribute("id_user", id_user);
                sessions.setAttribute("username", username);
                
                if(status.equals("valid")){
                    //errorMessage="SUCCESSS " + token;
                    response.sendRedirect("catalog.jsp");
                }
                else{
                    errorMessage= "Invalid Username and Password";
                }
                
            }
        
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login - Sale Project</title>
        <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
        <style type="text/css">
            <%@include file = "CSS/login.css"%>
        </style>
        <script type="text/javascript">
            <%@include file = "JS/login.js"%>
        </script>
    </head>
    <body>
        <h1>
            <span class="sale">Sale</span><span class="project">Project</span>
        </h1>
        <div class="box">
            <h2>Please login</h2>
            <hr>
            <div>
                <form name="loginForm" method="post">
                    Email or Username<br>
                    <input type="text" name="usn" onkeypress="return limitText()" onkeyup="return limitText()"><br>
                    Password<br>
                    <input type="password" name="pass" onkeypress="return limitPass()" onkeyup="return limitPass()"><br>
                    <input type="submit" value="LOGIN" name="login">
                </form>
            </div>
             <%=errorMessage%>
            <div class="regis">
                <p>Don't have an account yet? Register <a href="register.jsp">here</a> </p>
            </div>
        </div>
    </body>
</html>
