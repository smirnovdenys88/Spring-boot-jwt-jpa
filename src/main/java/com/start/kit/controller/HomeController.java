package com.start.kit.controller;

import com.start.kit.dto.UserDTO;
import com.start.kit.model.User;
import com.start.kit.utils.DTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public ResponseEntity hello() {
        return new ResponseEntity("Hello JWT", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveUser(@DTO(UserDTO.class) User user){
        return new ResponseEntity(user, HttpStatus.OK);
    }
}
