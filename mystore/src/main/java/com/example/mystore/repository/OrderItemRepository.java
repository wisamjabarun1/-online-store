package com.example.mystore.repository;

import com.example.mystore.model.OrderItem;
import com.example.mystore.model.OrderItemWithDetails;
import com.example.mystore.repository.mapper.ItemOrderMapper;
import com.example.mystore.repository.mapper.OrderItemWithDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository

public class OrderItemRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean isItemInStock(int itemId) {
        String sql = "SELECT quantity FROM items WHERE item_id = ?";
        Integer qty = jdbcTemplate.queryForObject(sql, Integer.class, itemId);
        return qty != null && qty > 0;
    }

    public OrderItem addOrderItem(OrderItem item) {
        // First: check if the item is in stock
        if (!isItemInStock(item.getItemId())) {
            throw new RuntimeException("Item is out of stock");
        }

        try {
            // Add item to order_items
            String sql = "INSERT INTO order_items(order_id, item_id, price_at_purchase, username) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sql, item.getOrderId(), item.getItemId(), item.getPriceAtPurchase(), item.getUsername1());

            // Update total_price in orders table
            String updateTotalSql = "UPDATE orders SET total_price = (SELECT SUM(price_at_purchase) FROM order_items WHERE order_id = ?) WHERE order_id = ?";
            jdbcTemplate.update(updateTotalSql, item.getOrderId(), item.getOrderId());

            // Reduce item quantity
            String updateStockSql = "UPDATE items SET quantity = quantity - 1 WHERE item_id = ?";
            jdbcTemplate.update(updateStockSql, item.getItemId());

            return item;
        } catch (Exception e) {
            System.out.println("Error inserting order item: " + e.getMessage());
            return null;
        }
    }


    public String deleteOrderItem(String username, int orderItemId) {
        try {
            // 1. Get item_id and order_id for this order item before deleting
            String selectSql = "SELECT item_id, order_id FROM order_items WHERE username = ? AND order_item_id = ?";
            Map<String, Object> row = jdbcTemplate.queryForMap(selectSql, username, orderItemId);

            int itemId = (int) row.get("item_id");
            int orderId = (int) row.get("order_id");

            // 2. Delete the order item
            String sql = "DELETE FROM order_items WHERE username = ? AND order_item_id = ?";
            int deleted = jdbcTemplate.update(sql, username, orderItemId);

            if (deleted == 0) {
                throw new IllegalArgumentException("Order item does not exist or does not belong to user.");
            }

            // 3. Increase item stock by 1
            String updateStockSql = "UPDATE items SET quantity = quantity + 1 WHERE item_id = ?";
            jdbcTemplate.update(updateStockSql, itemId);

            // 4. Update total_price in orders table
            String updateTotalSql = "UPDATE orders SET total_price = (SELECT COALESCE(SUM(price_at_purchase), 0) FROM order_items WHERE order_id = ?) WHERE order_id = ?";
            jdbcTemplate.update(updateTotalSql, orderId, orderId);

            return "Order item removed and stock updated";
        } catch (Exception e) {
            System.out.println("Error deleting order item: " + e.getMessage());
            return null;
        }
    }

    public List<OrderItem> getAllOrderItems() {
        try {
            String sql = "SELECT * FROM order_items";
            return jdbcTemplate.query(sql,new ItemOrderMapper());
        } catch (Exception e) {
            System.out.println("Error getting all order items: " + e.getMessage());
            return null;
        }
    }

    public List<OrderItemWithDetails> getOrderItemsWithDetailsByOrderId(int orderId, String username) {
        try {
            String sql = "SELECT " +
                    "  oi.order_item_id, " +
                    "  oi.order_id, " +
                    "  oi.item_id, " +
                    "  oi.price_at_purchase, " +
                    "  oi.username, " +
                    "  i.title, " +
                    "  i.img, " +
                    "  i.price AS current_price, " +
                    "  i.quantity AS stock_quantity, " +
                    "  COUNT(*) AS total_quantity " +
                    "FROM order_items oi " +
                    "JOIN items i ON oi.item_id = i.item_id " +
                    "WHERE oi.order_id = ? AND oi.username = ? " +
                    "GROUP BY " +
                    "  oi.order_item_id, " +
                    "  oi.order_id, " +
                    "  oi.item_id, " +
                    "  oi.price_at_purchase, " +
                    "  oi.username, " +
                    "  i.title, " +
                    "  i.img, " +
                    "  i.price, " +
                    "  i.quantity";


            return jdbcTemplate.query(sql, new OrderItemWithDetailsMapper(), orderId, username);

        } catch (Exception e) {
            System.out.println("Error getting detailed order items: " + e.getMessage());
            return null;
        }
    }


}




