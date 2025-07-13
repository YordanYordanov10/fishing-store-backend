package com.fishingstore.Category.Service;

import com.fishingstore.Category.Model.Category;
import com.fishingstore.Category.Repository.CategoryRepository;
import com.fishingstore.Exception.CategoryAlreadyExistException;
import com.fishingstore.Exception.CategoryNotExist;
import com.fishingstore.Web.Dto.CategoryRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public Category findByCategoryId(UUID id) {

        Optional<Category> existCategory = categoryRepository.findCategoriesById(id);

        if(!existCategory.isPresent()) {
            throw new CategoryNotExist("Category does not exist");
        }
        return categoryRepository.findCategoriesById(id).get();
    }
}
