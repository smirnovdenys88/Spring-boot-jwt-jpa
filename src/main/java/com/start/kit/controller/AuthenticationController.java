package com.start.kit.controller;

import com.start.kit.components.SenderNotification;
import com.start.kit.dto.UserDTO;
import com.start.kit.model.User;
import com.start.kit.service.AuthenticationService;
import com.start.kit.utils.Constants;
import com.start.kit.utils.DTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/signin")
    public ResponseEntity singIn(@Valid @RequestBody UserDTO userDTO) {
        return authenticationService.singIn(userDTO);
    }

    @PostMapping("/register")
    public ResponseEntity signUp(@Valid @RequestBody UserDTO userDTO) {
        ModelMapper mapper = new ModelMapper();
        User user = mapper.map(userDTO, User.class);
        return authenticationService.signUp(user);
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