package com.example.security.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigTest {

    @Autowired
    private MockMvc mock;

    @Test
    public void publicEndpoint() throws Exception {
        mock.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void adminAdminEndpoint() throws Exception {
        mock.perform(get("/admin")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user")
    public void userAdminEndpoint() throws Exception {
        mock.perform(get("/admin")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "user")
    public void userUserEndpoint() throws Exception {
        mock.perform(get("/user")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void adminUserEndpoint() throws Exception {
        mock.perform(get("/user")).andExpect(status().isForbidden());
    }
}