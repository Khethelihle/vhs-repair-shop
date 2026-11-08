package com.khethelihle.vhs.order;

import com.khethelihle.vhs.exception.ResourceNotFoundException;
import com.khethelihle.vhs.product.Product;
import com.khethelihle.vhs.product.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

class OrderServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService service;

    @Test
    void shouldCreateOrder() {

        Product product = new Product(
                "Carbon-ceramic",
                "High Performance brakes",
                new BigDecimal("4500.00")
        );

        when(productRepository.findById(1L))
                .thenReturn(Optional.of(product));

        when(orderRepository.save(ArgumentMatchers.any(CustomerOrder.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        OrderRequest request = new OrderRequest(
                List.of(new OrderItemRequest(1L, 2))
        );

        CustomerOrder result = service.create(request);

        assertEquals(
                new BigDecimal("9000"),
                result.getTotalAmount()
        );

        assertEquals(1, result.getItems().size());

        verify(orderRepository).save(any(CustomerOrder.class));
    }

    @Test
    void shouldFailWhenProductDoesNotExist() {

        when(productRepository.findById(99L))
                .thenReturn(Optional.empty());

        OrderRequest request = new OrderRequest(
                List.of(new OrderItemRequest(99L, 1))
        );

        assertThrows(
                ResourceNotFoundException.class,
                () -> service.create(request)
        );

        verify(orderRepository, never())
                .save(any(CustomerOrder.class));
    }
}
