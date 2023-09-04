package com.lenhac.deprakt.controllers;


import com.lenhac.deprakt.dto.AssetDTO;
import com.lenhac.deprakt.models.AssetV1;
import com.lenhac.deprakt.repositories.AssetRepo;
import com.lenhac.deprakt.services.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class MainController {
//    private final AssetService assetService;
//    @Autowired
//    private AssetRepo assetRepo;
//
//    public MainController(AssetService assetService) {
//        this.assetService = assetService;
//    }
//
//    @GetMapping("/")
//    public String getAllAssets(Model model) {
//        List<AssetDTO> assets = assetService.getAllAssets();
//        model.addAttribute("assets", assets);
//
//        if (assets.isEmpty()) {
//            model.addAttribute("error", "No assets found");
//        }
//        return "index";
//    }
//
//
//    @GetMapping("/new")
//    public String showNewAssetForm(Model model) {
//        model.addAttribute("asset", new AssetV1());
//        return "new_asset";
//    }
//
//    @PostMapping("/assets")
//    public String createAsset(@ModelAttribute("asset") AssetV1 form, @RequestParam("purchaseDate") String purchaseDate, Model model, RedirectAttributes redirectAttributes) {
//        java.sql.Date sqlPurchaseDate = java.sql.Date.valueOf(purchaseDate);
//        AssetV1 assetV1 = new AssetV1();
//        assetV1.setName(form.getName());
//        assetV1.setType(form.getType());
//        assetV1.setUser(form.getUser());
//        assetV1.setSupplier(form.getSupplier());
//        assetV1.setModel(form.getModel());
//        assetV1.setMake(form.getMake());
//        assetV1.setShelfLife(form.getShelfLife());
//        assetV1.setValue(form.getValue());
//        assetV1.setPurchaseDate(sqlPurchaseDate);
//        assetV1.setNotes(form.getNotes());
//
//        AssetV1 savedAssetV1 = assetService.saveAsset(assetV1);
//
//        if (savedAssetV1 != null) {
//            model.addAttribute("asset", savedAssetV1);
//            redirectAttributes.addFlashAttribute("message", "Asset created successfully");
//            return "redirect:/assets-view/" + savedAssetV1.getId();
//        } else {
//            redirectAttributes.addFlashAttribute("error", "Failed to create asset");
//            return "redirect:/";
//        }
//    }
//
//
//    @GetMapping("/{id}")
//    public String showEditPage(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
//        AssetV1 existingAssetV1 = assetService.getAssetById(id);
//        if (existingAssetV1 == null) {
//            redirectAttributes.addFlashAttribute("error", "Asset not found");
//            return "redirect:/";
//        }
//        model.addAttribute("asset", existingAssetV1);
//        return "update_asset";
//    }
//
//    @PostMapping("/assets/{id}")
//    public String editAsset(@PathVariable Long id, @ModelAttribute("asset") AssetV1 assetV1, RedirectAttributes redirectAttributes) {
//        AssetV1 existingAssetV1 = assetService.getAssetById(id);
//        if (existingAssetV1 == null) {
//            // Handle error: Asset not found
//            return "index";
//        }
//        existingAssetV1.setName(assetV1.getName());
//        existingAssetV1.setType(assetV1.getType());
//        existingAssetV1.setUser(assetV1.getUser());
//        existingAssetV1.setSupplier(assetV1.getSupplier());
//        existingAssetV1.setModel(assetV1.getModel());
//        existingAssetV1.setMake(assetV1.getMake());
//        existingAssetV1.setPurchaseDate(assetV1.getPurchaseDate());
//        existingAssetV1.setShelfLife(assetV1.getShelfLife());
//        existingAssetV1.setValue(assetV1.getValue());
//        existingAssetV1.setNotes(assetV1.getNotes());
//
//        AssetV1 savedAssetV1 = assetService.saveAsset(existingAssetV1);
//        if (savedAssetV1 != null) {
//            redirectAttributes.addFlashAttribute("message", "Asset saved successfully");
//        } else {
//            redirectAttributes.addFlashAttribute("error", "Failed to save asset");
//        }
//        return "redirect:/assets-view/" + id;
//    }
//
//    @GetMapping("/assets-view/{id}")
//    public String showAssetDetails(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
//        AssetV1 assetV1 = assetService.getAssetById(id);
//        if (assetV1 == null) {
//            redirectAttributes.addFlashAttribute("error", "Item not found");
//            return "redirect:/";
//        }
//        model.addAttribute("asset", assetV1);
//        return "asset-details";
//    }
//
//    @RequestMapping(value = "/assets/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
//    public String deleteAsset(@PathVariable Long id, RedirectAttributes redirectAttributes) {
//        if (assetRepo.existsById(id)) {
//            assetRepo.deleteById(id);
//            redirectAttributes.addFlashAttribute("message", "Asset deleted successfully");
//        } else {
//            redirectAttributes.addFlashAttribute("error", "Asset not found");
//        }
//        return "redirect:/";
//    }
//
//    @ExceptionHandler(Exception.class)
//    public String handleError(Exception exception, Model model) {
//        String message = "Oops! Something went wrong. We couldn't find the page you were looking for.";
//        if (exception.getMessage() != null) {
//            model.addAttribute("error", message);
//        }
//        return "index";
//    }

}
