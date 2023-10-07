package com.lenhac.deprakt.controllers;


import com.lenhac.deprakt.dto.AssetDTO;
import com.lenhac.deprakt.models.Asset;
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
    private Asset asset;

    private final AssetService assetService;
    @Autowired
    private AssetRepo assetRepo;

    public MainController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping("/")
    public String getAllAssets(Model model) {
        List<AssetDTO> assets = assetService.getAllAssets();
        model.addAttribute("assets", assets);

        if (assets.isEmpty()) {
            model.addAttribute("error", "No assets found");
        }
        return "index";
    }


    @GetMapping("/new")
    public String showNewAssetForm(Model model) {
        model.addAttribute("asset", new Asset());
        return "new_asset";
    }

    @PostMapping("/assets")
    public String createAsset(@ModelAttribute("asset") Asset form, @RequestParam("purchaseDate") String purchaseDate, Model model, RedirectAttributes redirectAttributes) {
        java.sql.Date sqlPurchaseDate = java.sql.Date.valueOf(purchaseDate);
        Asset asset = new Asset();
        asset.setName(form.getName());
        asset.setType(form.getType());
        asset.setUser(form.getUser());
        asset.setSupplier(form.getSupplier());
        asset.setModel(form.getModel());
        asset.setMake(form.getMake());
        asset.setShelfLife(form.getShelfLife());
        asset.setValue(form.getValue());
        asset.setPurchaseDate(sqlPurchaseDate);
        asset.setNotes(form.getNotes());


        Asset savedAsset = assetService.saveAsset(asset);

        if (savedAsset != null) {
            model.addAttribute("asset", savedAsset);
            redirectAttributes.addFlashAttribute("message", "Asset created successfully");
            return "redirect:/assets-view/" + savedAsset.getId();
        } else {
            redirectAttributes.addFlashAttribute("error", "Failed to create asset");
            return "redirect:/";
        }
    }


    @GetMapping("/{id}")
    public String showEditPage(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Asset existingAssetV1 = assetService.getAssetById(id);
        if (existingAssetV1 == null) {
            redirectAttributes.addFlashAttribute("error", "Asset not found");
            return "redirect:/";
        }
        model.addAttribute("asset", existingAssetV1);
        return "update_asset";
    }

    @PostMapping("/assets/{id}")
    public String editAsset(@PathVariable Long id, @ModelAttribute("asset") Asset asset, RedirectAttributes redirectAttributes) {
        Asset existingAssetV1 = assetService.getAssetById(id);
        if (existingAssetV1 == null) {
            // Handle error: Asset not found
            return "index";
        }
        existingAssetV1.setName(asset.getName());
        existingAssetV1.setType(asset.getType());
        existingAssetV1.setUser(asset.getUser());
        existingAssetV1.setSupplier(asset.getSupplier());
        existingAssetV1.setModel(asset.getModel());
        existingAssetV1.setMake(asset.getMake());
        existingAssetV1.setPurchaseDate(asset.getPurchaseDate());
        existingAssetV1.setShelfLife(asset.getShelfLife());
        existingAssetV1.setValue(asset.getValue());
        existingAssetV1.setNotes(asset.getNotes());

        Asset savedAsset = assetService.saveAsset(existingAssetV1);
        if (savedAsset != null) {
            redirectAttributes.addFlashAttribute("message", "Asset saved successfully");
        } else {
            redirectAttributes.addFlashAttribute("error", "Failed to save asset");
        }
        return "redirect:/assets-view/" + id;
    }

    @GetMapping("/assets-view/{id}")
    public String showAssetDetails(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Asset assetV1 = assetService.getAssetById(id);
        if (assetV1 == null) {
            redirectAttributes.addFlashAttribute("error", "Item not found");
            return "redirect:/";
        }
        model.addAttribute("asset", assetV1);
        return "asset-details";
    }

    @RequestMapping(value = "/assets/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteAsset(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (assetRepo.existsById(id)) {
            assetRepo.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Asset deleted successfully");
        } else {
            redirectAttributes.addFlashAttribute("error", "Asset not found");
        }
        return "redirect:/";
    }
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
