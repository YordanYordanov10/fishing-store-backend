package com.fishingstore.Web.Dto;

import com.fishingstore.Category.Model.Category;
import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class CategoryRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @Size(max = 150, message = "Description must be at most 150 characters")
    private String description;

    @URL(message = "Must be a valid URL")
    private String imageUrl;

}
