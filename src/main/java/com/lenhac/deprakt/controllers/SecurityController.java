package com.lenhac.deprakt.controllers;

import com.lenhac.deprakt.models.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 *
 *
 * */
@Controller
public class SecurityController {

    private final CredentialsService credentialsService;

    @Autowired
    public SecurityController(CredentialsService credentialsService) {
        this.credentialsService = credentialsService;
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("credentials", new Credentials());
        return "loginForm";
    }

    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute Credentials credentials) {
        boolean isValidUser = credentialsService.authenticateUser(credentials);
        if (isValidUser) {
            return "loginSuccess"; // Redirect to a success page after login
        } else {
            return "loginForm"; // Show the login form again with an error message
        }
    }
}
