package com.khethelihle.vhs.product;

import com.khethelihle.vhs.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request
        .MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result
        .MockMvcResultMatchers.*;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateProduct() throws Exception {

        String request = """
                {
                    "name" : "Carbon-ceramic",
                    "description" : "High Performance brakes",
                    "price" : "4500.00"
                }
                """;

        mockMvc.perform(post("/api/products")
                        .contentType(APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Carbon-ceramic"))
                .andExpect(jsonPath("$.price").value(4500.00));
    }

    @Test
    void shouldReturnAllProducts() throws Exception {

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnNotFoundForMissingProduct()
            throws Exception {

        mockMvc.perform(get("/api/products/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldRejectInvalidProduct() throws Exception {

        String request = """
                {
                    "name": "",
                    "description": "Invalid product",
                    "price": -10
                }
                """;

        mockMvc.perform(post("/api/products")
                        .contentType(APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isBadRequest());

    }
}
