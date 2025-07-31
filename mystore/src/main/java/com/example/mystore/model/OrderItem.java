package com.example.mystore.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderItem {
    @JsonProperty(value = "order_item_id")
    private int orderItemId;
    @JsonProperty(value = "order_id")
    private int orderId;
    @JsonProperty(value = "item_id")
    private int itemId;
    @JsonProperty(value = "price_at_purchase")
    private int priceAtPurchase;
    private String username;

    public OrderItem() {
    }

    public OrderItem(int orderItemId, int orderId, int itemId, int priceAtPurchase, String username) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.itemId = itemId;
        this.priceAtPurchase = priceAtPurchase;
        this.username = username;
    }

    // Getters and Setters
    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getPriceAtPurchase() {
        return priceAtPurchase;
    }

    public void setPriceAtPurchase(int priceAtPurchase) {
        this.priceAtPurchase = priceAtPurchase;
    }

    public String getUsername1() {
        return username;
    }

    public void setUsername1(String username) {
        this.username = username;
    }
}

