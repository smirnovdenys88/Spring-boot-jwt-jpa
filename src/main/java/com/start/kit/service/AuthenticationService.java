package com.start.kit.service;

import com.start.kit.dto.UserDTO;
import com.start.kit.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.view.RedirectView;

public interface AuthenticationService {
    ResponseEntity singIn(UserDTO userDTO);

    ResponseEntity signUp(User user);

    RedirectView confirmEmail(String token);

    ResponseEntity resetPassword(UserDTO userDTO, String newPass);
}
