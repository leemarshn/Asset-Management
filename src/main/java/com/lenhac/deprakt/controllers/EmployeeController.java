package com.lenhac.deprakt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Lee N on 06, Sat,Jan,2024.
 */

@Controller
public class EmployeeController {

    @GetMapping("/employees/new")
    public String newEmployeeForm(Model model) {
//        model.addAttribute("combinedForm", new CombinedEmployeePersonForm());
//        // Add other model attributes for organization and department selections
        return "employees/new";
    }
}
