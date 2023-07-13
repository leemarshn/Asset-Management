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
        return "index";
    }

    @GetMapping("/new")
    public String showNewAssetForm(Model model) {
        model.addAttribute("asset", new Asset());
        return "new_asset";
    }

    @PostMapping("/assets")
    public String createAsset(@ModelAttribute("asset") Asset form, @RequestParam("purchaseDate") String purchaseDate, Model model) {
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
        model.addAttribute("asset", savedAsset);

        return "asset-details";
    }

    @GetMapping("/{id}")
    public String showEditPage(@PathVariable Long id, Model model) {
        Asset existingAsset = assetService.getAssetById(id);
        if (existingAsset == null) {
            // Handle error: Asset not found
            return "index";
        }

        model.addAttribute("asset", existingAsset);
        return "update_asset";
    }

    @PostMapping("/assets/{id}")
    public String editAsset(@PathVariable Long id, @ModelAttribute("asset") Asset asset) {
        Asset existingAsset = assetService.getAssetById(id);
        if (existingAsset == null) {
            // Handle error: Asset not found
            return "index";
        }

        existingAsset.setName(asset.getName());
        existingAsset.setType(asset.getType());
        existingAsset.setUser(asset.getUser());
        existingAsset.setSupplier(asset.getSupplier());
        existingAsset.setModel(asset.getModel());
        existingAsset.setMake(asset.getMake());
        existingAsset.setPurchaseDate(asset.getPurchaseDate());
        existingAsset.setShelfLife(asset.getShelfLife());
        existingAsset.setValue(asset.getValue());
        existingAsset.setNotes(asset.getNotes());

        assetService.saveAsset(existingAsset);

        return "redirect:/assets-view/"+id;
    }

    @GetMapping("/assets-view/{id}")
    public String showAssetDetails(@PathVariable Long id, Model model) {
        Asset asset = assetService.getAssetById(id);
        model.addAttribute("asset", asset);
        return "asset-details";
    }

    @RequestMapping(value = "/assets/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteAsset(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        // Check if the asset exists
        if (assetRepo.existsById(id)) {
            // Delete the asset from the database
            assetRepo.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Asset deleted successfully");
        }
        return "redirect:/";
    }


}
