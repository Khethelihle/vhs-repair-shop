package com.khethelihle.vhs.order;

import com.khethelihle.vhs.product.Product;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;

public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Product product;

    private int quantity;

    private BigDecimal unitPrice;

    protected OrderItem() {}

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = product.getPrice();
    }

    public BigDecimal getSubTotal() {
        return unitPrice.multiply(
                BigDecimal.valueOf(quantity)
        );
    }

    public Product getproduct() {
        return product;
    }

    public int getQuantity(){
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

}
