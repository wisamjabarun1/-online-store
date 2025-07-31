package com.example.mystore.model;

public class OrderItemWithDetails {
    private int orderItemId;
    private int orderId;
    private int itemId;
    private int priceAtPurchase;
    private String username;

    // From items table
    private String title;
    private String img;
    private int currentPrice;
    private int stockQuantity;
    private int totalQuantity;


    // Constructors
    public OrderItemWithDetails() {}

    public OrderItemWithDetails(int orderItemId, int orderId, int itemId, int priceAtPurchase, String username,
                                String title, String img, int currentPrice, int stockQuantity,int totalQuantity ) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.itemId = itemId;
        this.priceAtPurchase = priceAtPurchase;
        this.username = username;
        this.title = title;
        this.img = img;
        this.currentPrice = currentPrice;
        this.stockQuantity = stockQuantity;
        this.totalQuantity =totalQuantity;
    }

    // Getters and Setters
    public int getOrderItemId() { return orderItemId; }
    public void setOrderItemId(int orderItemId) { this.orderItemId = orderItemId; }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }

    public int getPriceAtPurchase() { return priceAtPurchase; }
    public void setPriceAtPurchase(int priceAtPurchase) { this.priceAtPurchase = priceAtPurchase; }

    public String getUsername2() { return username; }
    public void setUsername2(String username) { this.username = username; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getImg() { return img; }
    public void setImg(String img) { this.img = img; }

    public int getCurrentPrice() { return currentPrice; }
    public void setCurrentPrice(int currentPrice) { this.currentPrice = currentPrice; }

    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    @Override
    public String toString() {
        return "OrderItemWithDetails{" +
                "orderItemId=" + orderItemId +
                ", orderId=" + orderId +
                ", itemId=" + itemId +
                ", priceAtPurchase=" + priceAtPurchase +
                ", username='" + username + '\'' +
                ", title='" + title + '\'' +
                ", img='" + img + '\'' +
                ", currentPrice=" + currentPrice +
                ", stockQuantity=" + stockQuantity +
                ", totalQuantity=" + totalQuantity +
                '}';
    }
}

