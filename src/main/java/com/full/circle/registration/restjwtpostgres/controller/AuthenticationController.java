package com.full.circle.registration.restjwtpostgres.controller;

import com.full.circle.registration.restjwtpostgres.components.SenderNotification;
import com.full.circle.registration.restjwtpostgres.dto.UserDTO;
import com.full.circle.registration.restjwtpostgres.service.AuthenticationService;
import com.full.circle.registration.restjwtpostgres.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@RestController
@RequestMapping("/authentication")
@CrossOrigin
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private SenderNotification senderNotification;

    @Autowired
    private Constants constants;

    @PostMapping("/authenticate")
    public ResponseEntity singIn(@Valid @RequestBody UserDTO userDTO) {
        return authenticationService.singIn(userDTO);
    }

    @PostMapping("/register")
    public ResponseEntity signUp(@Valid @RequestBody UserDTO userDTO) {
        return authenticationService.signUp(userDTO);
    }

    @GetMapping(path = "/register/confirm/{token}")
    public RedirectView confirmEmail(@PathVariable("token") String token) {
        return authenticationService.confirmEmail(token);
    }

    @GetMapping(path = "/register/reset/pass/")
    public ResponseEntity resetPassword(@PathVariable("token") UserDTO userDTO, String newPass) {
        return authenticationService.resetPassword(userDTO, newPass);
    }
}