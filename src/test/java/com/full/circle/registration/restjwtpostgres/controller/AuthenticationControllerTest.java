package com.full.circle.registration.restjwtpostgres.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.full.circle.registration.restjwtpostgres.config.CORSFilter;
import com.full.circle.registration.restjwtpostgres.dto.UserDTO;
import com.full.circle.registration.restjwtpostgres.service.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApplicationContext.class})
class AuthenticationControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService service;

    @InjectMocks
    private AuthenticationController controller;

    public void startService() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .addFilters(new CORSFilter())
                .build();
    }

    @Test
    void singIn() throws Exception {
        startService();
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("ivanov@ivan.com");
        userDTO.setUsername("Ivanov");
        userDTO.setPassword("123456");

        this.mockMvc.perform(post("http://localhost:8080/authentication/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userDTO))
                .header("Origin","http://localhost:3000"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Access-Control-Allow-Origin", "http://localhost:3000"))
                .andExpect(header().string("Access-Control-Allow-Methods", "POST, HEAD, PUT, GET, OPTIONS, DELETE"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andExpect(header().string("Access-Control-Max-Age", "3600"));
    }

    @Test
    void signUp() {

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}