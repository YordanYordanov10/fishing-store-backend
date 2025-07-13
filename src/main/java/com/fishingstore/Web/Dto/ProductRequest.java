package com.fishingstore.Web.Dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class ProductRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @Positive(message = "Price must be positive number")
    private double price;

    @Positive(message = "Quantity must be bigger than zero")
    private int quantity;

    @Size(max = 190, message = "Description must be at most 190 characters")
    private String description;

    @URL(message = "Image must be a valid URL")
    private String imageUrl;

    @NotBlank(message = "Category ID is required")
    private String categoryId;
}
