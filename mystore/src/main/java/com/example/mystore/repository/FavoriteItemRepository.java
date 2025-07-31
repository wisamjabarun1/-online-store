package com.example.mystore.repository;

import com.example.mystore.model.FavoriteItem;
import com.example.mystore.repository.mapper.FavoriteItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class FavoriteItemRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;



    public FavoriteItem addfavitems (FavoriteItem favoriteItem){
        try {
            String sql="INSERT INTO Favorites(username ,item_id ) VALUES (?, ?) " ;
            jdbcTemplate.update(sql,favoriteItem.getusername9(),favoriteItem.getItemid());
            return favoriteItem;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }



    }


    public String removeFavorite(String username, int itemId) {
        try {
            String sql = "DELETE FROM Favorites WHERE username = ? AND item_id = ?";
            jdbcTemplate.update(sql, username, itemId);
            return "Favorite for user " + username + " and item " + itemId + " removed successfully";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }





    public List<FavoriteItem> getFavoriteItemsByusername(String username) {
        try {
            String sql = "SELECT f.id, f.username, f.item_id, i.title,i.img, i.price, i.quantity FROM favorites f JOIN items i ON f.item_id = i.item_id WHERE f.username = ?;";
            return jdbcTemplate.query(sql, new FavoriteItemMapper(), username);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


}
