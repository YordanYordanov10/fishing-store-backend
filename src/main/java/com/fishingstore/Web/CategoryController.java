package com.fishingstore.Web;

import com.fishingstore.Category.Model.Category;
import com.fishingstore.Category.Service.CategoryService;
import com.fishingstore.Web.Dto.CategoryRequest;
import com.fishingstore.Web.Dto.CategoryResponse;
import com.fishingstore.Web.Mapper.DtoMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody @Valid CategoryRequest categoryRequest) {

       Category category = categoryService.createCategory(categoryRequest);

       CategoryResponse categoryResponse = DtoMapper.toCategoryResponse(category);

       return ResponseEntity.status(HttpStatus.CREATED).body(categoryResponse);
    }
}
