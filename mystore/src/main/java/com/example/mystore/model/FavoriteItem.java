package com.example.mystore.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FavoriteItem {
    private int id;
    private String username;
    @JsonProperty(value = "item_id")
    private int itemid;
   private String title;
    private String img;
    private int price;
    private int quantity;

    public FavoriteItem() {

    }


    public FavoriteItem(int id, String username, int itemid, String title,String img, int price, int quantity) {
        this.id = id;
        this.username = username;
        this.itemid = itemid;
        this.title=title;
        this.img = img;
        this.price = price;
        this.quantity = quantity;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getusername9() {
        return username;
    }

    public void setusername9(String userid) {
        this.username = userid;
    }

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitlem() {
        return title;
    }

    public void setTitlem(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    @Override
    public String toString() {
        return "FavoriteItem{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", itemid=" + itemid +
                ", title='" + title + '\'' +
                ", img='" + img + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
