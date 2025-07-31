package com.example.mystore.controller;

import com.example.mystore.model.AuthenticationRequest;
import com.example.mystore.model.AuthenticationResponse;
import com.example.mystore.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            AuthenticationResponse authResponse = authenticationService.createAuthenticationToken(authenticationRequest);
            return ResponseEntity.ok()
                    .body(authResponse);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Incorrect Username Or Password");
        }
    }
}



