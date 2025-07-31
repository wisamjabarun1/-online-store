package com.example.mystore.controller;


import com.example.mystore.model.FavoriteItem;
import com.example.mystore.model.OrderItem;
import com.example.mystore.model.OrderItemWithDetails;
import com.example.mystore.service.ItemOrderService;
import com.example.mystore.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/OrderItemController")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderItemController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ItemOrderService itemOrderService;

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<OrderItem> addtoorderitem(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody OrderItem orderitem) {
        try {
            String jwtToken = token.substring(7);
            String username = jwtUtil.extractUsername(jwtToken);

            // Ensure the favorite is associated with the correct user
            orderitem.setUsername1(username);

            OrderItem added = itemOrderService.addOrderItem(orderitem);
            if (added != null) {
                return ResponseEntity.ok(added);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{orderItemId}")
    public ResponseEntity<String> removeFromOrderItem(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable int orderItemId) {
        try {
            String jwtToken = token.substring(7);
            String username = jwtUtil.extractUsername(jwtToken);

            // FIXED ORDER: username FIRST, then orderItemId
            String result = itemOrderService.deleteOrderItem(username, orderItemId);

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{orderItemId}")
    public ResponseEntity<List<OrderItemWithDetails>> getitemsorderByUser(@RequestHeader(value = "Authorization") String token , @PathVariable int orderItemId) {
        try {
            String jwtToken = token.substring(7); // remove "Bearer "
            String username = jwtUtil.extractUsername(jwtToken);

            List<OrderItemWithDetails> orderItem = itemOrderService.getOrderItemsWithDetailsByOrderId(orderItemId,username);

            if (orderItem != null) {
                return ResponseEntity.ok(orderItem);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }









}

























