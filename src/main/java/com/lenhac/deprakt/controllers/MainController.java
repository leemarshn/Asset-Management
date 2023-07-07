package com.lenhac.deprakt.controllers;


import com.lenhac.deprakt.models.Asset;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;


@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping("/**")
    public String fallback() {
        return "redirect:/";
    }
    @GetMapping("/new")
    public String showNewAssetForm(Model model) {
        model.addAttribute("asset", new Asset());
        return "frmNewAsset";
    }

    @PostMapping("/new")
    public String createNewAsset(@ModelAttribute("asset") Asset asset) {
        // Logic to process and save the new asset
       // assetService.save(asset);
        return "redirect:/assets";
    }

}
