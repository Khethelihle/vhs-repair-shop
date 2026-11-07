package com.khethelihle.vhs.order;

import com.khethelihle.vhs.product.Product;
import com.khethelihle.vhs.product.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService (
            OrderRepository orderRepository,
            ProductRepository productRepository) {

        this.orderRepository = orderRepository;
        this.productRepository = productRepository;

    }

    @Transactional
    public CustomerOrder create(OrderRequest request) {

        List<OrderItem> items = request.items().stream()
                .map(item -> {
                    Product product = productRepository
                            .findById(item.productId())
                            .orElseThrow( () ->
                                    new RuntimeException("Product not found: " + item.productId()
                                    )
                            );

                    return new OrderItem(
                            product,
                            item.quantity()
                    );
                })
                .toList();

        CustomerOrder order = new CustomerOrder(items);

        return orderRepository.save(order);
    }

    public CustomerOrder getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Order not found: " + id));
    }

    public List<CustomerOrder> getAll() {
        return orderRepository.findAll();
    }
}
