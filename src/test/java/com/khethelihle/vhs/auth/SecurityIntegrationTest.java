package com.khethelihle.vhs.auth;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request
        .MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result
        .MockMvcResultMatchers.*;

import static org.springframework.security.test.web.servlet.request
        .SecurityMockMvcRequestPostProcessors.httpBasic;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class SecurityIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldRejectRequestWithoutCredentials()
            throws Exception {

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldRejectInvalidCredentials()
            throws Exception {

        mockMvc.perform(
                        get("/api/products")
                                .with(httpBasic("wrong-user", "wrong-password"))
                )
                .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldAllowRequestWithValidCredentials()
            throws Exception {

        mockMvc.perform(
                        get("/api/products")
                                .with(httpBasic("vhs-user", "hide-me"))
                )
                .andExpect(status().isOk());
    }

    @Test
    void shouldRequireAuthenticationForOrders()
            throws Exception {

        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isUnauthorized());
    }

}
