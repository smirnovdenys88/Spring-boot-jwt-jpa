package com.full.circle.registration.restjwtpostgres.service;

import com.full.circle.registration.restjwtpostgres.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.view.RedirectView;

public interface AuthenticationService {
    ResponseEntity singIn(UserDTO userDTO);

    ResponseEntity signUp(UserDTO userDTO);

    RedirectView confirmEmail(String token);

    ResponseEntity resetPassword(UserDTO userDTO, String newPass);
}
