/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import org.json.simple.JSONObject;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import user.user;

/**
 *
 * @author vitra
 */
public class request {

    public request() {
    }
    
    public JSONObject getValidatedToken(String token) {
        JSONObject obj = new JSONObject();
        try {
            URL connection = new URL("http://localhost:24488/identityservice/validateToken");
            HttpURLConnection con = (HttpURLConnection) connection.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setAllowUserInteraction(false);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            
            String urlParameters = "token=" + token;
            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.writeBytes(urlParameters);
                wr.flush();
                wr.close();
            }
            con.connect();
            StringBuilder resp;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                resp = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    resp.append(inputLine);
                }
            }
            con.disconnect();
            JSONParser parser = new JSONParser();
            obj = (JSONObject) parser.parse(resp.toString());
        } catch (MalformedURLException ex) {
            Logger.getLogger(request.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ParseException ex) {
            Logger.getLogger(request.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }
    
    public ArrayList<String> requestValidatedToken (String token) {
        JSONObject js = getValidatedToken(token);
        String newtoken = (String) js.get("token");
        String status = (String) js.get("status");
        ArrayList<String> list = new ArrayList();
        list.add(newtoken);
        list.add(status);
        return list;
    }
    
    public JSONObject getUserData(String idus) {
        JSONObject obj = new JSONObject();
        try {
            URL connection = new URL("http://localhost:24488/identityservice/username");
            HttpURLConnection con = (HttpURLConnection) connection.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setAllowUserInteraction(false);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            
            String urlParameters = "id_user=" + idus;
            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.writeBytes(urlParameters);
                wr.flush();
                wr.close();
            }
            con.connect();
            StringBuilder resp;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                resp = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    resp.append(inputLine);
                }
            }
            con.disconnect();
            JSONParser parser = new JSONParser();
            obj = (JSONObject) parser.parse(resp.toString());
        } catch (MalformedURLException ex) {
            Logger.getLogger(request.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ParseException ex) {
            Logger.getLogger(request.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }
    
    public String requestName(String idus) {
        JSONObject js = getUserData(idus);
        String name = (String) js.get("username");
        return name;
    }
    
    public JSONObject getUserData2(String name) {
        JSONObject obj = new JSONObject();
        try {
            URL connection = new URL("http://localhost:24488/identityservice/id_user");
            HttpURLConnection con = (HttpURLConnection) connection.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setAllowUserInteraction(false);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            
            String urlParameters = "name=" + name;
            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.writeBytes(urlParameters);
                wr.flush();
                wr.close();
            }
            con.connect();
            StringBuilder resp;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                resp = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    resp.append(inputLine);
                }
            }
            con.disconnect();
            JSONParser parser = new JSONParser();
            obj = (JSONObject) parser.parse(resp.toString());
        } catch (MalformedURLException ex) {
            Logger.getLogger(request.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ParseException ex) {
            Logger.getLogger(request.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }
    
    public ArrayList<String> requestIduser(String name) {
        JSONObject js = getUserData2(name);
        ArrayList<String> list = (ArrayList) js.get("id_user");
        return list;
    }
    
    public JSONObject getUser(int idus) {
        JSONObject obj = new JSONObject();
        try {
            URL connection = new URL("http://localhost:24488/identityservice/userInfo");
            HttpURLConnection con = (HttpURLConnection) connection.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setAllowUserInteraction(false);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            
            String urlParameters = "id_user=" + idus;
            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.writeBytes(urlParameters);
                wr.flush();
                wr.close();
            }
            con.connect();
            StringBuilder resp;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                resp = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    resp.append(inputLine);
                }
            }
            con.disconnect();
            JSONParser parser = new JSONParser();
            obj = (JSONObject) parser.parse(resp.toString());
        } catch (MalformedURLException ex) {
            Logger.getLogger(request.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ParseException ex) {
            Logger.getLogger(request.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }
    
    public user requestUser(int idus) {
        JSONObject js = getUser(idus);
        ArrayList<String> list = (ArrayList) js.get("user");
        user userinfo = new user(Integer.parseInt(list.get(0)), list.get(1), "", "", list.get(2), list.get(3), list.get(4), "");
        return userinfo;
    }
}
