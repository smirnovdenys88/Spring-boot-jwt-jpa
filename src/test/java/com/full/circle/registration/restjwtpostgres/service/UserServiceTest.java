package com.full.circle.registration.restjwtpostgres.service;

import com.full.circle.registration.restjwtpostgres.dto.UserDTO;
import com.full.circle.registration.restjwtpostgres.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class UserServiceTest {
    private UserDTO user;

    @Autowired
    private AuthenticationService authenticationService;

    @Before
    public void initUser(){
        user = new UserDTO();
        user.setUsername("test");
        user.setEmail("test@r.com");
        user.setPassword("12345678");
    }

    @Test
    public void signUp(){
       ResponseEntity response = authenticationService.signUp(user);

//       Assert.assertTrue();
    }

    @After
    public void deleteUser(){

    }
}
