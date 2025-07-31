package com.example.mystore.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {
    @JsonProperty(value = "item_id")
    private int itemid ;
    private String title ;
    private String img ;
    private  int price ;
    private  int quantity ;

    public Item() {

    }

    public Item(int itemid, String title, String img, int price, int quantity) {
        this.itemid = itemid;
        this.title = title;
        this.img = img;
        this.price = price;
        this.quantity = quantity;
    }

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public String toString() {
        return "Item{" +
                "img='" + img + '\'' +
                ", itemid=" + itemid +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
