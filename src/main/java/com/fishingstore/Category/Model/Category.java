package com.fishingstore.Category.Model;

import com.fishingstore.Product.Model.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<Product> products;
}
