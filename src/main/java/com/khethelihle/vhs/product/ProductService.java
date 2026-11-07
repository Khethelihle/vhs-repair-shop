package com.khethelihle.vhs.product;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product create(ProductRequest request) {
        Product product = new Product(request.name(), request.description(), request.price());

        return repository.save(product);
    }

    public Product getById(Long id) {
        return repository.findById(id)
                .orElseThrow( () ->
                        new RuntimeException("Product not found " + id));
    }

    public List<Product> getAll() {
        return repository.findAll();
    }
}
