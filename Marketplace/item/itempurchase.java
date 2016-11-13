/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package item;

/**
 *
 * @author vitra
 */
public class itempurchase {
    private String date;
    private String time;
    private String photo;
    private String prodname;
    private int price;
    private int quantity;
    private int total;
    private String username;
    private String consignee;
    private String add;
    private String postal_code;
    private String phone_number;

    public itempurchase(String date, String time, String photo, String prodname, int price, int quantity, int total, String username, String consignee, String add, String postal_code, String phone_number) {
        this.date = date;
        this.time = time;
        this.photo = photo;
        this.prodname = prodname;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
        this.username = username;
        this.consignee = consignee;
        this.add = add;
        this.postal_code = postal_code;
        this.phone_number = phone_number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public String getPostalcode() {
        return postal_code;
    }

    public void setPostalcode(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getPhonenumber() {
        return phone_number;
    }

    public void setPhonenumber(String phone_number) {
        this.phone_number = phone_number;
    }
}
