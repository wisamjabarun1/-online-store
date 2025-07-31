package com.example.mystore.service;

import com.example.mystore.model.OrderItem;
import com.example.mystore.model.OrderItemWithDetails;
import com.example.mystore.repository.OrderItemRepository;
import com.example.mystore.repository.mapper.ItemOrderMapper;
import com.example.mystore.repository.mapper.OrderItemWithDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ItemOrderService {
    @Autowired
    OrderItemRepository orderItemRepository;


    public OrderItem addOrderItem(OrderItem item) {

        return orderItemRepository.addOrderItem(item);

    }

    public String deleteOrderItem(String username, int orderItemId) {
        int deleted = Integer.parseInt(orderItemRepository.deleteOrderItem(username, orderItemId));
        if (deleted == 0) {
            throw new IllegalArgumentException("Order item does not exist or does not belong to user.");
        }
        return "Order item deleted";
    }


    public List<OrderItem> getAllOrderItems() {

        return orderItemRepository.getAllOrderItems();

    }


    public List<OrderItemWithDetails> getOrderItemsWithDetailsByOrderId(int orderId ,String username) {
        return orderItemRepository.getOrderItemsWithDetailsByOrderId(orderId ,username);
    }


}




