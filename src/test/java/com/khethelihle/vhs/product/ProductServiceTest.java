package com.khethelihle.vhs.product;

import com.khethelihle.vhs.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService service;

    @Test
    void shouldCreateproduct() {

        ProductRequest request = new ProductRequest(
                "Carbon-ceramic",
                "High Performance brakes",
                new BigDecimal("4500.00")
        );

        Product saved = new Product(
                "Carbon-ceramic",
                "High Performance brakes",
                new BigDecimal("4500.00")
        );

        when(repository.save(any(Product.class)))
                .thenReturn(saved);

        Product result = service.create(request);

        assertEquals("Carbon-ceramic", result.getName());

        assertEquals(
                new BigDecimal("4500.00"),
                result.getPrice(), "4500.00"
        );

        verify(repository).save(any(Product.class));
    }

        @Test
        void shouldGetProductById() {

            Product product = new Product(
                    "Carbon-ceramic",
                    "High Performance brakes",
                    new BigDecimal("4500.00")
            );

            when(repository.findById(1L))
                    .thenReturn(Optional.of(product));

            Product result1 = service.getById(1L);

            assertEquals("Carbon-ceramic", result1.getName());
        }

        @Test
        void shouldThrowExceptionWhenProductDoesNotExist() {
            when(repository.findById(55L))
                    .thenReturn(Optional.empty());

            assertThrows (
                    ResourceNotFoundException.class,
                    () -> service.getById(55L)
            );
        }

    }

