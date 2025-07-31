package com.example.mystore.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;



public class Order {

    @JsonProperty(value = "order_id")
    private int orderid ;

    @JsonProperty(value = "username")
    private String  username ;

    @JsonProperty(value = "order_date")
    private LocalDate orderdate ;

    @JsonProperty(value = "shipping_address")
    private String address ;

    @JsonProperty(value = "total_price")
    private int totalprice ;

    @JsonProperty(value = "status")
    private OrderStatus orderStatus;

    public Order() {
    }

    public Order(int orderid, String username, LocalDate orderdate, String address, int totalprice, OrderStatus orderStatus) {
        this.orderid = orderid;
        this.username = username;
        this.orderdate = orderdate;
        this.address = address;
        this.totalprice = totalprice;
        this.orderStatus = orderStatus;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public LocalDate getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(LocalDate orderdate) {
        this.orderdate = orderdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderid=" + orderid +
                ", username=" + username +
                ", orderdate=" + orderdate +
                ", address='" + address + '\'' +
                ", totalprice=" + totalprice +
                ", orderStatus=" + orderStatus +
                '}';
    }
}