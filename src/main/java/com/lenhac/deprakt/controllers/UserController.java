package com.lenhac.deprakt.controllers;

import com.lenhac.deprakt.models.Permission;
import com.lenhac.deprakt.repositories.PermissionRepository;
import com.lenhac.deprakt.services.PermissionService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UserController {

//    private PermissionRepository permissionRepository;
//
//
//    private final PermissionService permissionService;
//
//    public UserController(PermissionService permissionService) {
//        this.permissionService = permissionService;
//    }
//
//    @GetMapping("/entity-form")
//    public ModelAndView showForm(Model model) {
//        Permission entity = new Permission();
//        model.addAttribute("entity", entity);
//        return new ModelAndView("entity-form");
//    }
//
//
//    @PostMapping("/save-entity")
//    public ModelAndView saveEntity(@ModelAttribute Permission entity) {
//        Permission savedEntity = permissionService.savePermission(entity);
//        String viewName = savedEntity != null ? "entity-form" : "error";
//        String message = savedEntity != null ? "Entity saved successfully" : "Error saving entity";
//        ModelAndView modelAndView = new ModelAndView(viewName);
//        modelAndView.addObject("entity", savedEntity);
//        modelAndView.addObject("message", message);
//        return modelAndView;
//    }
//
//    @GetMapping("/login")
//    public String loginPage() {
//        return "login"; // Assuming "login" is the name of your login page Thymeleaf template
//    }
//
//    @GetMapping("/register")
//    public String registerPage() {
//        return "register"; // Assuming "register" is the name of your register page Thymeleaf template
//    }
//
//    @GetMapping("/about")
//    public String aboutPage() {
//        return "about"; // Assuming "about" is the name of your about page Thymeleaf template
//    }

}

