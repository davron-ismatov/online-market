package com.example.auth.controller;

import com.example.auth.entity.Users;
import com.example.auth.service.UserService;
import com.example.feignclients.auth.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor

public class UserController {
    private final UserService service;

    @GetMapping("/{id}")
    public Users getUserById(@PathVariable Long id){
        return service.getUserById(id);
    }


    @GetMapping("/for-order")
    public List<UserInfo> getUserForOrder(){
        return service.getUserForOrder();
    }

    @GetMapping("/for-order/{id}")
    public UserInfo getUserForOrder(@PathVariable Long id){
        return service.getUserForOrder(id);
    }


}
