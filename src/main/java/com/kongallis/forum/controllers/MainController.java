package com.kongallis.forum.controllers;


import com.kongallis.forum.models.User;
import com.kongallis.forum.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class MainController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping(value = "/users", produces = "application/json")
    public List<User> getUsers () {
        return userService.listAll();
    }
}
