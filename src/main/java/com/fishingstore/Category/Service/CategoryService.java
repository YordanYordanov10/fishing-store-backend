package com.fishingstore.Category.Service;

import com.fishingstore.Category.Model.Category;
import com.fishingstore.Category.Repository.CategoryRepository;
import com.fishingstore.Exception.CategoryAlreadyExistException;
import com.fishingstore.Web.Dto.CategoryRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(@Valid CategoryRequest categoryRequest) {

        Optional<Category> existCategory = categoryRepository.findCategoriesByName(categoryRequest.getName());

        if(existCategory.isPresent()) {
            throw new CategoryAlreadyExistException("Category already exist");
        }

        Category category = Category.builder()
                .name(categoryRequest.getName())
                .description(categoryRequest.getDescription())
                .imageUrl(categoryRequest.getImageUrl())
                .build();

        categoryRepository.save(category);
        return category;
    }
}
