package com.lenhac.deprakt.controllers;

import com.lenhac.deprakt.dto.OrganizationDTO;
import com.lenhac.deprakt.models.Organization;
import com.lenhac.deprakt.services.OrganizationService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

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
        return "organizationForm";
    }

    @PostMapping("/organization/new")
    public String createOrganization(@Valid @ModelAttribute Organization organizationForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "organizationForm"; // Return to the form if errors exist
        }

        Organization organization = new Organization();
        organization.setName(organizationForm.getName());
        String uniqueName = organizationForm.getUniqueName().toLowerCase().trim().replaceAll("\\s+|\\W+", "");
        organization.setUniqueName(uniqueName);
        organization.setPhoneNumber(organizationForm.getPhoneNumber());
        organization.setEmailAddress(organizationForm.getEmailAddress());
        organization.setLogoPath(organizationForm.getLogoPath());
        organization.setPinNumber(organizationForm.getPinNumber());
        organization.setNotes(organizationForm.getNotes());
        try {
            organizationService.saveOrganization(organization);
            redirectAttributes.addFlashAttribute("success", "Organization added successfully!");
            return "redirect:/organization";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error saving organization: " + e.getMessage());
            return "redirect:/organization/new";
        }
    }



    @GetMapping("/organization")
    public String showOrganizations(Model model) {

        List<Organization> organizations = organizationService.getAllOrganizations();

        // Fetch only necessary fields for efficiency
        List<OrganizationDTO> organizationDTOs = organizations.stream()
                .map(organization -> new OrganizationDTO(
                        organization.getId(),
                        organization.getName(),
                        organization.getPhoneNumber(),
                        organization.getPinNumber(),
                        organization.getEmailAddress()))
                .collect(Collectors.toList());

        model.addAttribute("organizations", organizationDTOs);
        return "organizations";
    }
}

