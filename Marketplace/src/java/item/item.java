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
    private String date;
    private String time;
    private String photo;
    private String name;
    private int price;
    private String desc;
    private int likes;
    private int purch;

    public item(int idpro, String date, String time, String photo, String name, int price, String desc, int likes, int purch) {
        this.idpro = idpro;
        this.date = date;
        this.time = time;
        this.photo = photo;
        this.name = name;
        this.price = price;
        this.desc = desc;
        this.likes = likes;
        this.purch = purch;
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
}
