/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

/**
 *
 * @author User
 */
public class user {
    private int id_user;
    private String full_name;
    private String username;
    private String password;
    private String full_address;
    private String postal_code;
    private String phone_number;
    private String email;
    
/*    public user(int id_user, String full_name, String username, String password, String full_address, String postal_code, String phone_number, String email) {
        this.id_user = id_user;
        this.full_name = full_name;
        this.username = username;
        this.password = password;
        this.full_address = full_address;
        this.postal_code = postal_code;
        this.phone_number = phone_number;
        this.email = email;
    }
*/
    public user() {
        id_user = 0;
    }
    public int getId() {
        return id_user;
    }
    
    public void setId (int id_user) {
        this.id_user = id_user;
    }
    
    public String getFName() {
        return full_name;
    }
    
    public void setFName (String full_name) {
        this.full_name = full_name;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername (String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword (String password) {
        this.password = password;
    }
    
    public String getAddress() {
        return full_address;
    }
    
    public void setAddress (String full_address) {
        this.password = full_address;
    }
    
    public String getPostalcode() {
        return postal_code;
    }
    
    public void setPostalcode (String postal_code) {
        this.postal_code = postal_code;
    }
    
    public String getPhonenumber() {
        return phone_number;
    }
    
    public void setPhonenumber (String phone_number) {
        this.phone_number = phone_number;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail (String email) {
        this.email = email;
    }
   
}
