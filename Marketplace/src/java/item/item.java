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
public class item {

    private int idpro;
    private String iditem;
    private String username;
    private String date;
    private String time;
    private String photo;
    private String name;
    private int price;
    private String desc;
    private int likes;
    private int purch;
    private String like;

    public item() {
    }

    public item(int idpro, String iditem, String username, String date, String time, String photo, String name, int price, String desc, int likes, int purch, String like) {
        this.idpro = idpro;
        this.iditem = iditem;
        this.username = username;
        this.date = date;
        this.time = time;
        this.photo = photo;
        this.name = name;
        this.price = price;
        this.desc = desc;
        this.likes = likes;
        this.purch = purch;
        this.like = like;
    }

    public int getIdpro() {
        return idpro;
    }

    public void setIdpro(int idpro) {
        this.idpro = idpro;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getPurch() {
        return purch;
    }

    public void setPurch(int purch) {
        this.purch = purch;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getIditem() {
        return iditem;
    }

    public void setIditem(String iditem) {
        this.iditem = iditem;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
}
