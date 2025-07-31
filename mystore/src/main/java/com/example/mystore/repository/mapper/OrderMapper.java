package com.example.mystore.repository.mapper;

import com.example.mystore.model.Order;
import com.example.mystore.model.OrderStatus;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class OrderMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Order(
                rs.getInt("order_id"),
                rs.getString("username"),
                rs.getObject("order_date", LocalDate.class),
                rs.getString("shipping_address"),
                rs.getInt("total_price"),
                OrderStatus.valueOf(rs.getString("status"))
        );
    }
}
