package com.example.mystore.repository;

import com.example.mystore.model.Item;
import com.example.mystore.repository.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public Item additems (Item item){
      try {
          String sql="INSERT INTO items( title,img,price,quantity) VALUES (?, ?, ?, ?) " ;
          jdbcTemplate.update(sql,item.getTitle(),item.getImg(),item.getPrice(),item.getQuantity());
          return item;
      }catch (Exception e){
          System.out.println(e.getMessage());
          return null;
      }



    }

    public Item updateitem(Item item){
      try {
          String Sql="UPDATE items SET title=?,img=?,price=?,quantity=? WHERE item_id = ?; ";
          jdbcTemplate.update(Sql,item.getTitle(),item.getImg(),item.getPrice(),item.getQuantity(), item.getItemid());
          return item;
      }catch (Exception e){
          System.out.println(e.getMessage());
          return null;
      }
    }


    public String DeleteItem(int id ){
      try {
          String sql="DELETE FROM items WHERE item_id = ?; ";
          jdbcTemplate.update(sql,id);
          return "the item with id " + id + " deleted successfully";

      }catch (Exception e){
          System.out.println(e.getMessage());
          return null;
      }
    }


    public Item getItemById(int id) {
      try {
          String sql = "SELECT * FROM items WHERE item_id = ?";
          return jdbcTemplate.queryForObject(sql, new ItemMapper(), id);
      }catch (Exception e){
          System.out.println(e.getMessage());
          return null;
      }
    }


    public Item getItemByTitle(String title){
        try{
            String sql = "SELECT * FROM items WHERE title =?";
            return jdbcTemplate.queryForObject(sql,new ItemMapper(),title);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }


    public List<Item> getallitems() {
        try {
            String sql = "SELECT * FROM items";
            return jdbcTemplate.query(sql, new ItemMapper());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
   public void reduceItemQuantity(int itemId, int amount) {
        String sql = "UPDATE items SET quantity = quantity - ? WHERE item_id = ? AND quantity >= ?";
        jdbcTemplate.update(sql, amount, itemId, amount);
    }



    public List<Item> searchByTitle(String keyword) {
        String sql = "SELECT * FROM items WHERE LOWER(title) LIKE LOWER(CONCAT('%', ?, '%'))";

        return jdbcTemplate.query(
                sql,
                new Object[]{keyword},
                new ItemMapper()
        );
    }












}
