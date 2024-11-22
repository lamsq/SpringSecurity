package com.example.security.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void homeEndpoint() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(content().string("Home page"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void adminAdminEndpoint() throws Exception {
        mockMvc.perform(get("/admin")).andExpect(status().isOk()).andExpect(content().string("Admin page"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void userUserEndpoint() throws Exception {
        mockMvc.perform(get("/user")).andExpect(status().isOk()).andExpect(content().string("User page"));
    }
}
