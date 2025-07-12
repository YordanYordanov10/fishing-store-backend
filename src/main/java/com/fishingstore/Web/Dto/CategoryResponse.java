package com.fishingstore.Web.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {

    private UUID categoryId;

    private String categoryName;

    private String categoryDescription;

    private String categoryImage;
}
