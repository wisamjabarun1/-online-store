package com.example.mystore.service;

import com.example.mystore.model.FavoriteItem;
import com.example.mystore.repository.FavoriteItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FavItemService {

    @Autowired
    private FavoriteItemRepository favoriteItemRepository;

    public FavoriteItem addToFavorites(FavoriteItem favoriteItem) {
        return favoriteItemRepository.addfavitems(favoriteItem);
    }
    public String removeFromFavorites(String username, int itemId) {
        return favoriteItemRepository.removeFavorite(username, itemId);
    }


    public List<FavoriteItem> getFavoritesByusername(String username) {
        return favoriteItemRepository.getFavoriteItemsByusername(username);
    }


}
