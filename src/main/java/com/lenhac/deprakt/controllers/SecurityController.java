package com.lenhac.deprakt.controllers;

import com.lenhac.deprakt.exceptions.InvalidException;
import com.lenhac.deprakt.models.Credentials;
import com.lenhac.deprakt.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 *
 *
 * */
@Controller
public class SecurityController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/login")
    public String loginPage() {
        return "loginForm";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password, Model model) {
        try {
                Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "redirect:/index"; // Redirect to a secured page
        } catch (InvalidException e) {
            model.addAttribute("error", "Invalid username or password");
            return "redirect:/login";
        }
    }
}

