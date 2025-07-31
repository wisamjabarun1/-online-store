package com.example.mystore.repository;

import com.example.mystore.model.Order;
import com.example.mystore.repository.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean isItemInStock(int itemId) {
        String sql = "SELECT quantity FROM items WHERE item_id = ?";
        Integer qty = jdbcTemplate.queryForObject(sql, Integer.class, itemId);
        return qty != null && qty > 0;
    }

    public Order AddOrder (Order order){
//        if (!isItemInStock(item.getItemId())) {
//            throw new RuntimeException("Item is out of stock");
//        }

        String sql = "INSERT INTO orders (username ,order_date,shipping_address,total_price,status) VALUES (?, ?,?,?,?) ";
        jdbcTemplate.update(sql,order.getusername(),order.getOrderdate(),order.getAddress(),order.getTotalprice(), order.getOrderStatus().name());
    return order ;
    }




    public List<Order> GetFOrdersByusername(String username) {
        try {
            String sql = "SELECT  *  FROM orders  WHERE username = ? ; ";
            return jdbcTemplate.query(sql, new OrderMapper(), username);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }



    public void updateStatus(int order_id, String username) {
        try {
            String sql = "UPDATE orders SET status = 'CLOSED' WHERE order_id = ? AND username = ?";
            jdbcTemplate.update(sql, order_id, username);
        } catch (Exception e) {
            System.out.println("Error updating order status: " + e.getMessage());
        }
    }


    public void updateAddress(int orderId, String username, String newAddress) {
        try {
            String sql = "UPDATE orders SET shipping_address = ? WHERE order_id = ? AND username = ?";
            jdbcTemplate.update(sql, newAddress, orderId, username);
        } catch (Exception e) {
            System.out.println("Error updating order address: " + e.getMessage());
        }
    }


    public void deleteOrderById(int orderId) {
        String sql = "DELETE FROM orders WHERE order_id = ?";
        jdbcTemplate.update(sql, orderId);
    }














}
