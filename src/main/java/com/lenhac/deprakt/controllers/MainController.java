package com.lenhac.deprakt.controllers;


import com.lenhac.deprakt.models.Asset;
import com.lenhac.deprakt.services.AssetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class MainController {
    private final AssetService assetService;

    public MainController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/new")
    public String showNewAssetForm(Model model) {
        model.addAttribute("asset", new Asset());
        return "frmNewAsset";
    }




    @PostMapping("/assets")
    public String createAsset(@ModelAttribute("asset") Asset form, @RequestParam("purchaseDate") String purchaseDate, Model model) {
        // Create an instance of the Asset entity and populate it with form data
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

        // Save the asset using the AssetService
        Asset savedAsset = assetService.saveAsset(asset);

        // Add the saved asset to the model for display on the success page
        model.addAttribute("asset", savedAsset);

        // Return the view name for the success page
        return "index";
    }

}
