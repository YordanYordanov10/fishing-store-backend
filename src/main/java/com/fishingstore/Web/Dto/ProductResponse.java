package com.fishingstore.Web.Dto;

import com.fishingstore.Category.Model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private UUID id;

    private String name;

    private double price;

    private int quantity;

    private String description;

    private String imageUrl;

    private Category category;


}
