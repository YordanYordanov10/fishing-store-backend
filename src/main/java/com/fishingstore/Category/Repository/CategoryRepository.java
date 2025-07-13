package com.fishingstore.Category.Repository;

import com.fishingstore.Category.Model.Category;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {


    Optional<Category> findCategoriesByName( String name);

    Optional<Category> findCategoriesById(UUID id);
}
