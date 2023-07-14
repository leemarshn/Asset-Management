package com.lenhac.deprakt.controllers;

import com.lenhac.deprakt.dto.AssetDTO;
import com.lenhac.deprakt.models.Asset;
import com.lenhac.deprakt.repositories.AssetRepo;
import com.lenhac.deprakt.services.AssetService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MainController.class)
public class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssetRepo assetRepo;

    @MockBean
    private AssetService assetService;

    @Mock
    private MainController mainController;

    @Test
    public void testCreateAsset() throws Exception {
        // Mock the saved asset
        Asset savedAsset = new Asset();
        savedAsset.setId(1L);
        savedAsset.setName("Asset Name");
        savedAsset.setType("computer");
        savedAsset.setUser("John Doe");
        savedAsset.setSupplier("Supplier Name");
        savedAsset.setModel("Asset Model");
        savedAsset.setMake("Asset Make");
        savedAsset.setShelfLife(5);
        savedAsset.setValue(1000.0);
        savedAsset.setPurchaseDate(java.sql.Date.valueOf("2023-12-12"));
        savedAsset.setNotes("Asset Notes");

        when(assetService.saveAsset(any(Asset.class))).thenReturn(savedAsset);

        mockMvc.perform(post("/assets")
                        .param("name", "Asset Name")
                        .param("type", "computer")
                        .param("user", "John Doe")
                        .param("supplier", "Supplier Name")
                        .param("model", "Asset Model")
                        .param("make", "Asset Make")
                        .param("shelfLife", "5")
                        .param("value", "1000.0")
                        .param("purchaseDate", "2023-12-12")
                        .param("notes", "Asset Notes"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("/assets-view/*"))
                .andExpect(flash().attributeExists("message"))
                .andExpect(flash().attribute("message", "Asset created successfully"));

        verify(assetService, times(1)).saveAsset(any(Asset.class));
    }

    @Test
    public void testCreateAsset_Failure() throws Exception {
        when(assetService.saveAsset(any(Asset.class))).thenReturn(null);

        mockMvc.perform(post("/assets")
                        .param("name", "Asset Name")
                        .param("type", "computer")
                        .param("user", "John Doe")
                        .param("supplier", "Supplier Name")
                        .param("model", "Asset Model")
                        .param("make", "Asset Make")
                        .param("shelfLife", "5")
                        .param("value", "1000.0")
                        .param("purchaseDate", "2023-12-12")
                        .param("notes", "Asset Notes"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(flash().attributeExists("error"))
                .andExpect(flash().attribute("error", "Failed to create asset"));

        verify(assetService, times(1)).saveAsset(any(Asset.class));
    }

    @Test
    public void deleteAsset_ShouldDeleteExistingAssetAndSetFlashMessage() throws Exception {
        Long assetId = 123L;

        // Mock the behavior of assetRepo.existsById() method
        when(assetRepo.existsById(assetId)).thenReturn(true);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.delete("/assets/delete/{id}", assetId))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.flash().attributeExists("message"))
                .andExpect(MockMvcResultMatchers.flash().attribute("message", "Asset deleted successfully"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
    }
    @Test
    public void deleteAsset_ShouldHandleNonExistingAssetAndSetErrorFlashMessage() throws Exception {
        Long assetId = 123L;
        when(assetRepo.existsById(assetId)).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.delete("/assets/delete/{id}", assetId))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.flash().attributeExists("error"))
                .andExpect(MockMvcResultMatchers.flash().attribute("error", "Asset not found"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
    }
    @Test
    public void testEditAsset() throws Exception {
        Asset existingAsset = new Asset();
        existingAsset.setId(1L);
        existingAsset.setName("Existing Asset");
        existingAsset.setType("computer");
        existingAsset.setUser("John Doe");
        existingAsset.setSupplier("Supplier Name");
        existingAsset.setModel("Asset Model");
        existingAsset.setMake("Asset Make");
        existingAsset.setShelfLife(5);
        existingAsset.setValue(1000.0);
        existingAsset.setPurchaseDate(java.sql.Date.valueOf("2023-12-12"));
        existingAsset.setNotes("Asset Notes");

        when(assetService.getAssetById(1L)).thenReturn(existingAsset);

        Asset savedAsset = new Asset();
        savedAsset.setId(1L);
        savedAsset.setName("Updated Asset Name");
        savedAsset.setType("computer");
        savedAsset.setUser("John Doe");
        savedAsset.setSupplier("Supplier Name");
        savedAsset.setModel("Asset Model");
        savedAsset.setMake("Asset Make");
        savedAsset.setShelfLife(5);
        savedAsset.setValue(1000.0);
        savedAsset.setPurchaseDate(java.sql.Date.valueOf("2023-12-12"));
        savedAsset.setNotes("Asset Notes");

        when(assetService.saveAsset(any(Asset.class))).thenReturn(savedAsset);

        mockMvc.perform(post("/assets/1")
                        .param("name", "Updated Asset Name")
                        .param("type", "computer")
                        .param("user", "John Doe")
                        .param("supplier", "Supplier Name")
                        .param("model", "Asset Model")
                        .param("make", "Asset Make")
                        .param("shelfLife", "5")
                        .param("value", "1000.0")
                        .param("purchaseDate", "2023-12-12")
                        .param("notes", "Asset Notes"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/assets-view/1"))
                .andExpect(flash().attributeExists("message"))
                .andExpect(flash().attribute("message", "Asset saved successfully"));

        verify(assetService, times(1)).getAssetById(1L);

        verify(assetService, times(1)).saveAsset(any(Asset.class));
    }
    @Test
    public void testShowEditPage_AssetNotFound() throws Exception {
        Long assetId = 123L;

        when(assetService.getAssetById(assetId)).thenReturn(null);

        mockMvc.perform(get("/{id}", assetId))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.flash().attributeExists("error"))
                .andExpect(MockMvcResultMatchers.flash().attribute("error", "Asset not found"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
    }
    @Test
    public void testEditAsset_UpdateFailed() throws Exception {
        Asset existingAsset = new Asset();
        existingAsset.setId(1L);
        existingAsset.setName("Existing Asset");
        existingAsset.setType("computer");
        existingAsset.setUser("John Doe");
        existingAsset.setSupplier("Supplier Name");
        existingAsset.setModel("Asset Model");
        existingAsset.setMake("Asset Make");
        existingAsset.setShelfLife(5);
        existingAsset.setValue(1000.0);
        existingAsset.setPurchaseDate(java.sql.Date.valueOf("2023-12-12"));
        existingAsset.setNotes("Asset Notes");

        when(assetService.getAssetById(1L)).thenReturn(existingAsset);

        when(assetService.saveAsset(any(Asset.class))).thenReturn(null);

        mockMvc.perform(post("/assets/1")
                        .param("name", "Updated Asset Name")
                        .param("type", "computer")
                        .param("user", "John Doe")
                        .param("supplier", "Supplier Name")
                        .param("model", "Asset Model")
                        .param("make", "Asset Make")
                        .param("shelfLife", "5")
                        .param("value", "1000.0")
                        .param("purchaseDate", "2023-12-12")
                        .param("notes", "Asset Notes"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/assets-view/1"))
                .andExpect(flash().attributeExists("error"))
                .andExpect(flash().attribute("error", "Failed to save asset"));

        verify(assetService, times(1)).getAssetById(1L);

        verify(assetService, times(1)).saveAsset(any(Asset.class));
    }

    @Test
    public void testShowAssetDetails() throws Exception {
        Asset asset = new Asset();
        asset.setId(1L);
        asset.setName("Asset 1");
        asset.setUser("John Doe");
        asset.setValue(1000.0);
        asset.setPurchaseDate(Date.valueOf("2023-01-01"));

        when(assetService.getAssetById(1L)).thenReturn(asset);

        mockMvc.perform(get("/assets-view/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("asset-details"))
                .andExpect(model().attributeExists("asset"))
                .andExpect(model().attribute("asset", asset))
                .andExpect(flash().attributeCount(0));

        verify(assetService, times(1)).getAssetById(1L);
    }

    @Test
    public void testShowAssetDetails_ItemNotFound() throws Exception {
        when(assetService.getAssetById(2L)).thenReturn(null);

        mockMvc.perform(get("/assets-view/{id}", 2L))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"))
                .andExpect(flash().attributeExists("error"))
                .andExpect(flash().attribute("error", "Item not found"));

        verify(assetService, times(1)).getAssetById(2L);
    }


    @Test
    public void testGetAllAssets_NoAssetsFound() throws Exception {
        List<AssetDTO> assets = new ArrayList<>(); // Empty list of assets

        when(assetService.getAllAssets()).thenReturn(assets);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("assets"))
                .andExpect(model().attribute("assets", assets))
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attribute("error", "No assets found"));

        verify(assetService, times(1)).getAllAssets();
    }

    @Test
    public void testShowNewAssetForm() throws Exception {
        mockMvc.perform(get("/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("new_asset"))
                .andExpect(model().attributeExists("asset"))
                .andExpect(result -> {
                    Object asset = Objects.requireNonNull(result.getModelAndView()).getModel().get("asset");
                    assertTrue(asset instanceof Asset);
                });
    }


}