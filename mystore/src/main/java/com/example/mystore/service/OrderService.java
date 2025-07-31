package com.example.mystore.service;

import com.example.mystore.model.Order;
import com.example.mystore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class OrderService {
    @Autowired
    OrderRepository orderRepository ;

    public Order AddOrder (Order order){
         return orderRepository.AddOrder(order);
    }


    public List<Order> GetOrderByTheUserName(String username){
        return orderRepository.GetFOrdersByusername(username);
    }


    public void updateStatus(int order_id, String username) {
        orderRepository.updateStatus(order_id,username);
    }




    public void  updateAddress (int order_id, String username, String newAddress){
        orderRepository.updateAddress(order_id, username, newAddress);
    }

    public void removeOrder(int orderId) {
        orderRepository.deleteOrderById(orderId);
    }


}
