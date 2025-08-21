package io.irwansyahdev96.readcollection.business.user.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.irwansyahdev96.readcollection.business.user.service.UserService;
import io.irwansyahdev96.readcollection.model.User;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody User user){
        Map<String,Object> map = userService.save(user);

        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
}
