package com.khethelihle.vhs.order;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerOrder create (
            @Valid @RequestBody OrderRequest request) {

        return service.create(request);
    }

    @GetMapping("/{id}")
    public CustomerOrder getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<CustomerOrder> getAll() {
        return service.getAll();
    }
}
