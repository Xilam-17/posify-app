package com.posify.api.user.controller;

import com.posify.api.user.model.Users;
import com.posify.api.user.request.UserRequest;
import com.posify.api.user.response.UserResponse;
import com.posify.api.user.service.IUserService;
import com.posify.api.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class UserController {

    private final IUserService service;

    public UserController(IUserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest request) {
        return new ResponseEntity<>(service.register(request), HttpStatus.CREATED);
    }
}
