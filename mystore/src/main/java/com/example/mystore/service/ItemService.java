package com.example.mystore.service;

import com.example.mystore.model.Item;
import com.example.mystore.repository.ItemRepository;
import com.example.mystore.repository.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepository;

    public Item additems(Item item) {
        return itemRepository.additems(item);
    }

    public Item updateitem(Item item) {
        return itemRepository.updateitem(item);
    }

    public String DeleteItem(int id) {
        return itemRepository.DeleteItem(id);
    }

    public Item getItemById(int id) {
        return itemRepository.getItemById(id);
    }

    public Item getItemByTitle(String title) {
        return itemRepository.getItemByTitle(title);
    }
    public List<Item> getAllItems() {
        return itemRepository.getallitems();
    }

    public void reduceStockForItem(int itemId) {
        itemRepository.reduceItemQuantity(itemId, 1); // Always subtract 1 for a cart add
    }




    public List<Item> searchByTitle1(String keyword) {

        return itemRepository.searchByTitle(keyword);
    }













}