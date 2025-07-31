package com.example.mystore.repository.mapper;

import com.example.mystore.model.OrderItem;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemOrderMapper implements RowMapper<OrderItem> {
    @Override
    public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new OrderItem (
                rs.getInt("order_item_id"),
                rs.getInt("order_id"),
                rs.getInt("item_id"),
                rs.getInt("price_at_purchase"),
                rs.getString("username")
        );
    }
}
