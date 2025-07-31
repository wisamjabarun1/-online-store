package com.example.mystore.controller;

import com.example.mystore.model.FavoriteItem;
import com.example.mystore.service.FavItemService;
import com.example.mystore.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/favorites")
@CrossOrigin(origins = "http://localhost:3000")
public class FavItemsController {
    @Autowired
    private FavItemService favoriteItemService;

    @Autowired
    private JwtUtil jwtUtil;


    // ✅ Add to favorites
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<FavoriteItem> addToFavorites(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody FavoriteItem favoriteItem) {
        try {
            String jwtToken = token.substring(7);
            String username = jwtUtil.extractUsername(jwtToken);

            // Ensure the favorite is associated with the correct user
            favoriteItem.setusername9(username);

            FavoriteItem added = favoriteItemService.addToFavorites(favoriteItem);
            if (added != null) {
                return ResponseEntity.ok(added);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ✅ Remove from favorites (by userId and itemId)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/{itemId}")
    public ResponseEntity<String> removeFromFavorites(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable int itemId) {
        try {
            String jwtToken = token.substring(7); // remove "Bearer "
            String username = jwtUtil.extractUsername(jwtToken);

            String result = favoriteItemService.removeFromFavorites(username, itemId);

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);


        }
    }


    // ✅ Get all favorite items for a user
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<FavoriteItem>> getFavoritesByUser(@RequestHeader(value = "Authorization") String token) {
        try {
            String jwtToken = token.substring(7); // remove "Bearer "
            String username = jwtUtil.extractUsername(jwtToken);

            List<FavoriteItem> favorites = favoriteItemService.getFavoritesByusername(username);

            if (favorites != null) {
                return ResponseEntity.ok(favorites);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
