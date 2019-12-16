package com.full.circle.registration.restjwtpostgres.service.impl;

import com.full.circle.registration.restjwtpostgres.config.JwtTokenUtil;
import com.full.circle.registration.restjwtpostgres.dto.UserDTO;
import com.full.circle.registration.restjwtpostgres.model.AuthToken;
import com.full.circle.registration.restjwtpostgres.model.User;
import com.full.circle.registration.restjwtpostgres.repository.UserRepository;
import com.full.circle.registration.restjwtpostgres.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AuthenticationServiceImpl implements UserDetailsService, AuthenticationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid email or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority());
    }

    private List<SimpleGrantedAuthority> getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public ResponseEntity singIn(UserDTO userDTO) {
        try {
            User user = userRepository.findByEmail(userDTO.getEmail());
            if (user != null && user.isConfirmEmail() && checkPass(userDTO.getPassword(), user.getPassword())) {
                return ResponseEntity.ok(new AuthToken(jwtTokenUtil.generateToken(user), user.getUserName()));
            }
            return new ResponseEntity("Wrong Email or Password. Please Try Again.", HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity signUp(UserDTO userDTO) {
        List<User> daoUsers = userRepository.findByEmailIgnoreCaseOrUserNameIgnoreCase(userDTO.getEmail(), userDTO.getUsername());

        if (!daoUsers.isEmpty()) {
            String message = "";
            String fullCoincidence = "Email and Username are already registered";
            String onlyName = "Username is already registered";
            String onlyEmail = "Email is already registered";

            for (User us : daoUsers) {
                if (us.getEmail().equalsIgnoreCase(userDTO.getEmail()) && us.getUserName().equalsIgnoreCase(userDTO.getUsername())) {
                    message = fullCoincidence;
                    break;
                }
                if (us.getEmail().equalsIgnoreCase(userDTO.getEmail())) {
                    if (!message.isEmpty()) {
                        message = fullCoincidence;
                        break;
                    }
                    message = onlyEmail;
                } else {
                    if (!message.isEmpty()) {
                        message = fullCoincidence;
                        break;
                    }
                    message = onlyName;
                }
            }
            return new ResponseEntity(message, HttpStatus.FORBIDDEN);
        }

        User newUser = new User.Builder()
                .setEmail(userDTO.getEmail())
                .setUserName(userDTO.getUsername())
                .setPass(bcryptEncoder.encode(userDTO.getPassword()))
                .build();

        return new ResponseEntity(userRepository.save(newUser), HttpStatus.OK);
    }

    private boolean checkPass(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
