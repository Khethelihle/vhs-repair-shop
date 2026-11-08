package com.khethelihle.vhs.order;

import com.khethelihle.vhs.auth.Constants;
import com.khethelihle.vhs.product.Product;
import com.khethelihle.vhs.product.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.math.BigDecimal;

import static org.springframework.http.MediaType.APPLICATION_JSON;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.request
        .MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        orderRepository.deleteAll();
        productRepository.deleteAll();

        productRepository.save(
                new Product(
                        "Carbon-ceramic",
                        "High Performance brakes",
                        new BigDecimal("5000")
                )
        );
    }

    @Test
    void shouldCreateOrder() throws Exception {

        String request = """
            {
                "items": [
                    {
                        "productId": 1,
                        "quantity": 2
                    }
                ]
            }
            """;

        mockMvc.perform(post("/api/orders")
                        .with(httpBasic(Constants.username, Constants.password))
                        .contentType(APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isCreated())
                .andExpect((ResultMatcher) jsonPath("$.id").exists())
                .andExpect((ResultMatcher) jsonPath("$.totalAmount").value(10000));
    }

    @Test
    void shouldGetAllOrders() throws Exception {

        mockMvc.perform(get("/api/orders")
                        .with(httpBasic(Constants.username, Constants.password)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnNotFoundForMissingOrder()
            throws Exception {

        mockMvc.perform(get("/api/orders/999")
                        .with(httpBasic(Constants.username, Constants.password)))
                .andExpect(status().isNotFound());
    }
}