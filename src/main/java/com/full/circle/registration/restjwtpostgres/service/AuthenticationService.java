package com.full.circle.registration.restjwtpostgres.service;

import com.full.circle.registration.restjwtpostgres.dto.UserDTO;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity singIn(UserDTO userDTO);

    ResponseEntity signUp(UserDTO userDTO);
}
