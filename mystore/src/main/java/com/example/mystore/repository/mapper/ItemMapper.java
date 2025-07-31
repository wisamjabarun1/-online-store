package com.example.mystore.repository.mapper;

import com.example.mystore.model.Item;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemMapper implements RowMapper<Item> {
    @Override
    public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Item(
                rs.getInt("item_id"),
                rs.getString("title"),
                rs.getString("img"),
                rs.getInt("price"),
                rs.getInt("quantity"));

    }

}
