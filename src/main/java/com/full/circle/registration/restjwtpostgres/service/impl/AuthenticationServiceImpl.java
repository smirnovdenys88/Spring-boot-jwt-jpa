package com.full.circle.registration.restjwtpostgres.service.impl;

import com.full.circle.registration.restjwtpostgres.components.SenderNotification;
import com.full.circle.registration.restjwtpostgres.config.JwtTokenUtil;
import com.full.circle.registration.restjwtpostgres.dto.UserDTO;
import com.full.circle.registration.restjwtpostgres.model.AuthToken;
import com.full.circle.registration.restjwtpostgres.model.User;
import com.full.circle.registration.restjwtpostgres.repository.UserRepository;
import com.full.circle.registration.restjwtpostgres.service.AuthenticationService;
import com.full.circle.registration.restjwtpostgres.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.servlet.view.RedirectView;

import javax.mail.MessagingException;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationServiceImpl implements UserDetailsService, AuthenticationService {
    private static Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private SenderNotification senderNotification;
    @Autowired
    private Constants constants;


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
            logger.info("Sing in user" + userDTO.getEmail());
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
        logger.info("Sing up user" + userDTO.getEmail());

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

        String token = UUID.randomUUID().toString();
        String subject = "Registration Confirmation";
        String confirmationUrl = "Follow the link to verify your email " + constants.baseURL + "/" + constants.pathConfirmEmail + token;

        try {
            senderNotification.sendMail(constants.EMAIL_USER, constants.EMAIL_PASS,
                    constants.EMAIL_HOST, constants.EMAIL_PORT,
                    new String[]{userDTO.getEmail()}, subject, confirmationUrl);
        } catch (MessagingException e) {
            new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        User newUser = new User.Builder()
                .setEmail(userDTO.getEmail())
                .setUserName(userDTO.getUsername())
                .setToken(token)
                .setPass(bcryptEncoder.encode(userDTO.getPassword()))
                .build();

        userRepository.save(newUser);
        return new ResponseEntity(userDTO, HttpStatus.OK);
    }

    @Override
    public RedirectView confirmEmail(@NotNull String token) {
        Optional<User> optional = userRepository.findByTokenConfirmEmail(token);
        if (optional.isPresent()) {
            optional.get().setConfirmEmail(true);
            optional.get().setTokenConfirmEmail("");
            userRepository.save(optional.get());
            return new RedirectView(constants.URL_SIGN_IN_PAGE);
        } else {
            return new RedirectView();
        }
    }

    @Override
    public ResponseEntity resetPassword(UserDTO userDTO, String newPass) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(userDTO.getEmail()));
        if (user.isPresent() && checkPass(userDTO.getPassword(), user.get().getPassword())){
            user.get().changePassword(bcryptEncoder.encode(newPass));
            userRepository.save(user.get());
            return new ResponseEntity(userDTO, HttpStatus.OK);
        }
        return new ResponseEntity("password is not correct", HttpStatus.NOT_FOUND);
    }

    private synchronized boolean checkPass(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
