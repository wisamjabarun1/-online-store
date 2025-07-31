package com.example.mystore.repository.mapper;

import com.example.mystore.model.OrderItemWithDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItemWithDetailsMapper implements RowMapper<OrderItemWithDetails> {
    @Override
    public OrderItemWithDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new OrderItemWithDetails(
                rs.getInt("order_item_id"),
                rs.getInt("order_id"),
                rs.getInt("item_id"),
                rs.getInt("price_at_purchase"),
                rs.getString("username"),
                rs.getString("title"),
                rs.getString("img"),
                rs.getInt("current_price"),
                rs.getInt("stock_quantity"),
                rs.getInt("total_quantity")
        );
    }
}
