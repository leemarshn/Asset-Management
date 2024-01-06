package com.lenhac.deprakt.controllers;

import com.lenhac.deprakt.models.Organization;
import com.lenhac.deprakt.repositories.OrganisationRepository;
import com.lenhac.deprakt.services.OrganizationService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by Lee N on 06, Sat,Jan,2024.
 */

@Controller
public class OrganizationController {

    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping("/organization/new")
    public String newOrganizationForm(Model model) {
        model.addAttribute("organizationForm", new Organization());
        return "add_organization";
    }

    @PostMapping("/organization/new")
    public String createOrganization(@Valid @ModelAttribute Organization organizationForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add_organization";
        }

        // Map form fields to entity fields (assuming you've handled this appropriately)
//        Organization organization = new Organization();
        // ... (set organization fields using data from organizationForm)

        organizationService.saveOrganization(organizationForm); // Use the service to save

        return "redirect:/organization/new";
    }
}

