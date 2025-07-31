package com.example.mystore.repository.mapper;

import com.example.mystore.model.FavoriteItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FavoriteItemMapper  implements RowMapper<FavoriteItem> {

    @Override
    public FavoriteItem mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new FavoriteItem(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getInt("item_id"),
                rs.getString("title"),
                rs.getString("img"),
                rs.getInt("price"),
                rs.getInt("quantity"));


    }
}
