/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import item.item;
import item.itempurchase;
import java.util.ArrayList;
import user.user;

/**
 *
 * @author vitra
 */
public class map {
    private String token;
    private String status;
    private int obj;
    private ArrayList<item> listitem;
    private ArrayList<itempurchase> listpurchase;
    private ArrayList<user> listuser;

    public ArrayList<user> getListuser() {
        return listuser;
    }

    public void setListuser(ArrayList<user> listuser) {
        this.listuser = listuser;
    }

    public ArrayList<item> getListitem() {
        return listitem;
    }

    public void setListitem(ArrayList<item> listitem) {
        this.listitem = listitem;
    }

    public ArrayList<itempurchase> getListpurchase() {
        return listpurchase;
    }

    public void setListpurchase(ArrayList<itempurchase> listpurchase) {
        this.listpurchase = listpurchase;
    }

    public map(String token, String status, int obj, ArrayList<item> listitem, ArrayList<itempurchase> listpurchase, ArrayList<user> listuser) {
        this.token = token;
        this.status = status;
        this.obj = obj;
        this.listitem = listitem;
        this.listpurchase = listpurchase;
        this.listuser = listuser;
    }


    public map() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getObj() {
        return obj;
    }

    public void setObj(int obj) {
        this.obj = obj;
    }
    
}
