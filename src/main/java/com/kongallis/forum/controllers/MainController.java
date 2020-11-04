package com.kongallis.forum.controllers;


import com.kongallis.forum.dto.UserDto;
import com.kongallis.forum.models.User;
import com.kongallis.forum.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class MainController {

    @Autowired
    UserServiceImpl userService;


    @GetMapping(produces = "application/json")
    public ResponseEntity<List<UserDto>> getUsers() {
        return new ResponseEntity<>(userService.listAllUsers(), HttpStatus.OK);
    }

    
    @GetMapping(value = "/{userId}", produces = "application/json")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable("userId") Long id) {
        return new ResponseEntity<>(userService.readSingleUser(id), HttpStatus.OK);
    }

}
