package com.lenhac.deprakt.controllers;

import com.lenhac.deprakt.exceptions.DuplicateCategoryException;
import com.lenhac.deprakt.models.Category;
import com.lenhac.deprakt.repositories.CategoryRepo;
import com.lenhac.deprakt.services.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;
import java.util.Map;

/**
 * Created by Lee N on 04, Thu,Jan,2024.
 */

@Controller
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
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
        } catch (DuplicateCategoryException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/categories";
    }


}
