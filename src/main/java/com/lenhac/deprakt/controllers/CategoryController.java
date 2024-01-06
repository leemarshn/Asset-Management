package com.lenhac.deprakt.controllers;

import com.lenhac.deprakt.exceptions.InvalidException;
import com.lenhac.deprakt.models.Category;
import com.lenhac.deprakt.repositories.CategoryRepo;
import com.lenhac.deprakt.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lee N on 04, Thu,Jan,2024.
 */

@Controller
public class CategoryController {


    private final  CategoryRepo categoryRepo;
    private final CategoryService categoryService;
    public CategoryController(CategoryRepo categoryRepo, CategoryService categoryService) {
        this.categoryRepo = categoryRepo;
        this.categoryService = categoryService;
    }
    @GetMapping("/categories")
    public String index(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("category", new Category());

        return "categories";
    }

    @PostMapping("/categories")
    public String store(@ModelAttribute("category") @Valid Category category, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.category", bindingResult);
            redirectAttributes.addFlashAttribute("category", category);
            return "redirect:/categories";
        }

        try {
            categoryService.saveCategory(category);
            redirectAttributes.addFlashAttribute("success", "Category added successfully!");
        } catch (InvalidException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/categories";
    }

    @DeleteMapping("/category/delete/{id}")
    @ResponseBody  // Indicate a JSON response instead of rendering a view
    public Map<String, String> deleteCategory(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        try {
            if (categoryRepo.existsById(id)) {
                categoryRepo.deleteById(id);
                response.put("success", "Category deleted successfully");
            } else {
                response.put("error", "Category not found");
            }
        } catch (Exception e) {
            response.put("error", "An error occurred while deleting the category");
        }
        return response;
    }


    @GetMapping("/categories/edit/{id}")
    public String edit(Model model, @PathVariable String id) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("category", new Category());

        return "edit_category";
    }





}
