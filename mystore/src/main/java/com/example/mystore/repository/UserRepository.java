package com.example.mystore.repository;

import com.example.mystore.model.CustomUser;
import com.example.mystore.repository.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String USERS_TABLE = "users";

    public String register(CustomUser user) {
        String sql = String.format("INSERT INTO %s (first_name, last_name, email, phone, address, username, password, role) VALUES (?,?,?,?,?,?,?,?)", USERS_TABLE);
        jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone(), user.getAddress(), user.getUsername(), user.getPassword(), user.getRole().name());
        return "User registered successfully";
    }

    public CustomUser findUserByUsername(String username) {
        try {
            String sql = String.format("SELECT * FROM %s WHERE username = ?", USERS_TABLE);
            CustomUser user = jdbcTemplate.queryForObject(sql, new UserMapper(), username);
            return user;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public CustomUser findUserByEmail(String email) {
        try {
            String sql = String.format("SELECT * FROM %s WHERE email = ?", USERS_TABLE);
            CustomUser user = jdbcTemplate.queryForObject(sql, new UserMapper(), email);
            return user;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<CustomUser> findAllUsers() {
        String sql = String.format("SELECT * FROM %s", USERS_TABLE);
        List<CustomUser> users = jdbcTemplate.query(sql, new UserMapper());
        return users;
    }

    public CustomUser updateUser(CustomUser user) {
        String sql = String.format("UPDATE %s SET first_name = ?, last_name = ?, email = ?, phone = ?, address = ? WHERE username = ?", USERS_TABLE);
        jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone(), user.getAddress(), user.getUsername());
        return findUserByUsername(user.getUsername());
    }

    public String deleteUser(String username) {
        String sql = String.format("DELETE FROM %s WHERE username = ?", USERS_TABLE);
        jdbcTemplate.update(sql, username);
        return "User deleted successfully";
    }



    public boolean existsById(int userId) {
        String sql = "SELECT COUNT(*) FROM users WHERE user_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, userId);
        return count != null && count > 0;
    }





}
