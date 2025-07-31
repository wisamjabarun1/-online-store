package com.example.mystore.repository.mapper;

import com.example.mystore.model.CustomUser;
import com.example.mystore.model.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<CustomUser> {
    @Override
    public CustomUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CustomUser(
                rs.getInt("user_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getString("phone"),
                rs.getString("address"),
                rs.getString("username"),
                rs.getString("password"),
                Role.valueOf( rs.getString("role")));
    }
}

