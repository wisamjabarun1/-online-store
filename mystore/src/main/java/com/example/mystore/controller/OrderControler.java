package com.example.mystore.controller;

import com.example.mystore.model.Order;
import com.example.mystore.service.OrderService;
import com.example.mystore.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/Orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderControler {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    OrderService orderService ;

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Order> addorder(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Order order) {
        try {
            String jwtToken = token.substring(7);
            String username = jwtUtil.extractUsername(jwtToken);


            order.setusername(username);

            Order added = orderService.AddOrder(order);
            System.out.println("Saved Order ID: " + added.getOrderid());
            return ResponseEntity.ok(added);
        } catch (Exception e) {
            e.printStackTrace(); // log the error!
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Order>> getorderbyusername(@RequestHeader("Authorization") String token) {
        try {
            String jwtToken = token.substring(7);
            String username = jwtUtil.extractUsername(jwtToken);
            List<Order> orders = orderService.GetOrderByTheUserName(username);

            // Always return 200 OK with a list (even if empty)
            return ResponseEntity.ok(orders);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("/confirm/{orderId}")
    public ResponseEntity<?> confirmOrder(
            @PathVariable int orderId,
            @RequestHeader(value = "Authorization") String token
    ) {
        String username = jwtUtil.extractUsername(token.substring(7));
        orderService.updateStatus(orderId, username);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/update-address/{orderId}")
    public ResponseEntity<?> updateOrderAddress(
            @PathVariable int orderId,
            @RequestBody Map<String, String> body,
            @RequestHeader(value = "Authorization") String token
    ) {
        String newAddress = body.get("shipping_address");
        String username = jwtUtil.extractUsername(token.substring(7));
        orderService.updateAddress(orderId, username, newAddress);
        return ResponseEntity.ok().build();
    }



    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int orderId) {
        orderService.removeOrder(orderId);
        return ResponseEntity.noContent().build();
    }



}
