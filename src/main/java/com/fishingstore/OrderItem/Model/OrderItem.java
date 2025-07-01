package com.fishingstore.OrderItem.Model;

import com.fishingstore.Order.Model.Order;
import com.fishingstore.Product.Model.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double unitPrice;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
