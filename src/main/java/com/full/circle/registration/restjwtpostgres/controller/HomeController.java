package com.full.circle.registration.restjwtpostgres.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public ResponseEntity hello(HttpServletRequest request) {
        return new ResponseEntity("Hello" + request.toString(), HttpStatus.OK);
    }
}
