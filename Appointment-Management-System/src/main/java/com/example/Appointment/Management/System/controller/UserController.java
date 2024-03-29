package com.example.Appointment.Management.System.controller;

import com.example.Appointment.Management.System.entity.User;
import com.example.Appointment.Management.System.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService service;
    @PostMapping("register")
    public User register(@RequestBody User user){
        return service.saveUser(user);
    }
}
