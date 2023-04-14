package com.kosuri.stores.controller;

import com.kosuri.stores.exception.APIException;
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
    public ResponseEntity<?> addUser(@Valid @RequestBody AddUserRequest request) {
        HttpStatus httpStatus;
        String body;
        try {
            userHandler.addUser(request);
            httpStatus = HttpStatus.OK;
            body = "User added successfully";
        } catch (APIException e) {
            httpStatus = HttpStatus.BAD_REQUEST;
            body = e.getMessage();
        } catch (Exception e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            body = e.getMessage();
        }

        return new ResponseEntity<>(body, httpStatus);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginUserRequest request) {
        HttpStatus httpStatus;
        String body;
        try {
            userHandler.loginUser(request);
            httpStatus = HttpStatus.OK;
            body = "User logged in successfully!";
        } catch (APIException e) {
            httpStatus = HttpStatus.BAD_REQUEST;
            body = e.getMessage();
        }
        catch (Exception e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            body = e.getMessage();
        }
        return new ResponseEntity<>(body, httpStatus);
    }
}
