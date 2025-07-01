package com.fishingstore.Order.Model;

import com.fishingstore.OrderItem.Model.OrderItem;
import com.fishingstore.User.Model.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private LocalDate orderDate;

    @Column(nullable = false)
    private double totalPrice;

    @Column(nullable = false)
    private Status status;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
