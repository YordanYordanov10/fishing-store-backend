package com.fishingstore.Web.Dto;

import com.fishingstore.Category.Model.Category;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class ProductRequest {

    private String name;

    private double price;

    private int quantity;

    private String description;

    private String imageUrl;

    private Category category;
}
