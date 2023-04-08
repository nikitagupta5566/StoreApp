package com.kosuri.stores.controller;

import com.kosuri.stores.handler.UserHandler;
import com.kosuri.stores.model.request.AddUserRequest;
import com.kosuri.stores.model.request.LoginUserRequest;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserHandler userHandler;
    @PostMapping("/add")
    public ResponseEntity<?> addUser(@Valid @RequestBody AddUserRequest request){
        if(userHandler.addUser(request)){
            return new ResponseEntity<>("User added sauccessfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error adding user", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginUserRequest request){
        try {
            if (userHandler.loginUser(request)){
                return new ResponseEntity<>("User logged in successfully", HttpStatus.OK);
            }
        } catch (Exception e) {
            System.out.println("Error logging in user");
            System.out.println(e.getMessage());
            return new ResponseEntity<>("User logged in successfully", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Invalid credentials", HttpStatus.OK);
    }
}
