package com.khethelihle.vhs.order;

import com.khethelihle.vhs.product.Product;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
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
