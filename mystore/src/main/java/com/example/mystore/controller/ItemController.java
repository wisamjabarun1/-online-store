package com.example.mystore.controller;

import com.example.mystore.model.Item;
import com.example.mystore.repository.ItemRepository;
import com.example.mystore.service.ItemService;
import com.example.mystore.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/items")
@CrossOrigin(origins = "http://localhost:3000")
public class ItemController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    ItemService itemService;


    @GetMapping("/getitems")
    public ResponseEntity<List<Item>> GetAllitems() {
        try {
            List<Item> items = itemService.getAllItems();
            return ResponseEntity.ok(items);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PreAuthorize("hasRole('ADMIN')")
        @GetMapping("/getitembytitle/{title}")
        public ResponseEntity<Item> getItemByTitle(@PathVariable String title) {
            try {
                Item item = itemService.getItemByTitle(title);
                if (item != null) {
                    return ResponseEntity.ok(item);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getItemById/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable int id) {
        try {
            Item item = itemService.getItemById(id);
            if (item != null) {
                return ResponseEntity.ok(item);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    // POST: Add new item (ADMIN only)
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<Item> addItem(@RequestBody Item item) {
        Item addedItem = itemService.additems(item);
        if (addedItem != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(addedItem);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // PUT: Update item (ADMIN only)
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<Item> updateItem(@RequestBody Item item) {
        Item updatedItem = itemService.updateitem(item);
        if (updatedItem != null) {
            return ResponseEntity.ok(updatedItem);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // DELETE: Delete item by ID (ADMIN only)
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable int id) {
        String message = itemService.DeleteItem(id);
        if (message != null) {
            return ResponseEntity.ok(message);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete item.");
        }
    }





    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{itemId}/reduce")
    public ResponseEntity<?> reduceItemQuantity(@PathVariable int itemId) {
        try {
            itemService.reduceStockForItem(itemId);
            return ResponseEntity.ok("Quantity reduced");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to reduce quantity");
        }
    }



    @GetMapping("/search")
    public ResponseEntity<List<Item>> searchItems(@RequestParam String keyword) {
        List<Item> results = itemService.searchByTitle1(keyword);
        return ResponseEntity.ok(results);
    }











}




























