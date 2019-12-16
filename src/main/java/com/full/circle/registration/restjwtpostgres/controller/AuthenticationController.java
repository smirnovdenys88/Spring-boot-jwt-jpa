package com.full.circle.registration.restjwtpostgres.controller;

import com.full.circle.registration.restjwtpostgres.dto.UserDTO;
import com.full.circle.registration.restjwtpostgres.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/authentication")
@CrossOrigin
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity singIn(@RequestBody UserDTO userDTO) throws Exception {
        return authenticationService.singIn(userDTO);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity signUp(@Valid @RequestBody UserDTO userDTO) throws Exception {
        return authenticationService.signUp(userDTO);
    }

}