package com.start.kit.service;

import com.start.kit.dto.UserDTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class UserServiceTest {
    private UserDTO user;

    @Autowired
    private AuthenticationService authenticationService;

    @Before
    public void initUser(){
        user = new UserDTO();
        user.setUserName("test");
        user.setEmail("test@r.com");
        user.setPassword("12345678");
    }

    @Test
    public void signUp(){
//       ResponseEntity response = authenticationService.signUp(user);

//       Assert.assertTrue();
    }

    @After
    public void deleteUser(){

    }
}
