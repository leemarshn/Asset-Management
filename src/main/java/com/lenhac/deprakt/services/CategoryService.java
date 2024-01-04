package com.lenhac.deprakt.services;

import com.lenhac.deprakt.exceptions.DuplicateCategoryException;
import com.lenhac.deprakt.models.Category;
import com.lenhac.deprakt.repositories.CategoryRepo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Lee N on 04, Thu,Jan,2024.
 */

@Service
public class CategoryService {

    private final CategoryRepo categoryRepository;

    public CategoryService(CategoryRepo categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category saveCategory(Category category) {
        if (categoryRepository.existsByName(category.getName())) {
            throw new DuplicateCategoryException("Category with name " + category.getName() + " already exists");
        } else {
            return categoryRepository.save(category);
        }
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

}
