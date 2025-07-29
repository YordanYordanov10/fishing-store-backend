package com.fishingstore.Web;

import com.fishingstore.Category.Model.Category;
import com.fishingstore.Category.Service.CategoryService;
import com.fishingstore.Web.Dto.CategoryRequest;
import com.fishingstore.Web.Dto.CategoryResponse;
import com.fishingstore.Web.Mapper.DtoMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/categories")
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody @Valid CategoryRequest categoryRequest) {

       Category category = categoryService.createCategory(categoryRequest);
       CategoryResponse categoryResponse = DtoMapper.toCategoryResponse(category);
       return ResponseEntity.status(HttpStatus.CREATED).body(categoryResponse);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {

        List <Category> categoryList = categoryService.findAllCategories();
        List<CategoryResponse> categoryResponse = DtoMapper.toCategoryResponseList(categoryList);
        return ResponseEntity.ok(categoryResponse);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable UUID id) {

        Category category = categoryService.findByCategoryId(id);

        CategoryResponse categoryResponse = DtoMapper.toCategoryResponse(category);
        return ResponseEntity.ok(categoryResponse);
    }
}
