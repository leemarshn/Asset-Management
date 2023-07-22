package com.lenhac.deprakt.controllers;

import com.lenhac.deprakt.models.AssetV1;
import com.lenhac.deprakt.repositories.AssetRepo;
import com.lenhac.deprakt.services.AssetService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Date;
import java.util.ArrayList;

import java.util.Objects;

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
    @Test
    public void testCreateAsset() throws Exception {
        AssetV1 savedAssetV1 = new AssetV1();
        savedAssetV1.setId(1L);
        savedAssetV1.setName("Asset Name");
        savedAssetV1.setType("computer");
        savedAssetV1.setUser("John Doe");
        savedAssetV1.setSupplier("Supplier Name");
        savedAssetV1.setModel("Asset Model");
        savedAssetV1.setMake("Asset Make");
        savedAssetV1.setShelfLife(5);
        savedAssetV1.setValue(1000.0);
        savedAssetV1.setPurchaseDate(java.sql.Date.valueOf("2023-12-12"));
        savedAssetV1.setNotes("Asset Notes");

        when(assetService.saveAsset(any(AssetV1.class))).thenReturn(savedAssetV1);

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

        verify(assetService, times(1)).saveAsset(any(AssetV1.class));
    }
    @Test
    public void testCreateAsset_Failure() throws Exception {
        when(assetService.saveAsset(any(AssetV1.class))).thenReturn(null);

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

        verify(assetService, times(1)).saveAsset(any(AssetV1.class));
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
        AssetV1 existingAssetV1 = new AssetV1();
        existingAssetV1.setId(1L);
        existingAssetV1.setName("Existing Asset");
        existingAssetV1.setType("computer");
        existingAssetV1.setUser("John Doe");
        existingAssetV1.setSupplier("Supplier Name");
        existingAssetV1.setModel("Asset Model");
        existingAssetV1.setMake("Asset Make");
        existingAssetV1.setShelfLife(5);
        existingAssetV1.setValue(1000.0);
        existingAssetV1.setPurchaseDate(java.sql.Date.valueOf("2023-12-12"));
        existingAssetV1.setNotes("Asset Notes");

        when(assetService.getAssetById(1L)).thenReturn(existingAssetV1);

        AssetV1 savedAssetV1 = new AssetV1();
        savedAssetV1.setId(1L);
        savedAssetV1.setName("Updated Asset Name");
        savedAssetV1.setType("computer");
        savedAssetV1.setUser("John Doe");
        savedAssetV1.setSupplier("Supplier Name");
        savedAssetV1.setModel("Asset Model");
        savedAssetV1.setMake("Asset Make");
        savedAssetV1.setShelfLife(5);
        savedAssetV1.setValue(1000.0);
        savedAssetV1.setPurchaseDate(java.sql.Date.valueOf("2023-12-12"));
        savedAssetV1.setNotes("Asset Notes");

        when(assetService.saveAsset(any(AssetV1.class))).thenReturn(savedAssetV1);

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

        verify(assetService, times(1)).saveAsset(any(AssetV1.class));
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
        AssetV1 existingAssetV1 = new AssetV1();
        existingAssetV1.setId(1L);
        existingAssetV1.setName("Existing Asset");
        existingAssetV1.setType("computer");
        existingAssetV1.setUser("John Doe");
        existingAssetV1.setSupplier("Supplier Name");
        existingAssetV1.setModel("Asset Model");
        existingAssetV1.setMake("Asset Make");
        existingAssetV1.setShelfLife(5);
        existingAssetV1.setValue(1000.0);
        existingAssetV1.setPurchaseDate(java.sql.Date.valueOf("2023-12-12"));
        existingAssetV1.setNotes("Asset Notes");

        when(assetService.getAssetById(1L)).thenReturn(existingAssetV1);

        when(assetService.saveAsset(any(AssetV1.class))).thenReturn(null);

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

        verify(assetService, times(1)).saveAsset(any(AssetV1.class));
    }

    @Test
    public void testShowAssetDetails() throws Exception {
        AssetV1 assetV1 = new AssetV1();
        assetV1.setId(1L);
        assetV1.setName("Asset 1");
        assetV1.setUser("John Doe");
        assetV1.setValue(1000.0);
        assetV1.setPurchaseDate(Date.valueOf("2023-01-01"));

        when(assetService.getAssetById(1L)).thenReturn(assetV1);

        mockMvc.perform(get("/assets-view/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("asset-details"))
                .andExpect(model().attributeExists("asset"))
                .andExpect(model().attribute("asset", assetV1))
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
    public void testShowNewAssetForm() throws Exception {
        mockMvc.perform(get("/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("new_asset"))
                .andExpect(model().attributeExists("asset"))
                .andExpect(result -> {
                    Object asset = Objects.requireNonNull(result.getModelAndView()).getModel().get("asset");
                    assertTrue(asset instanceof AssetV1);
                });
    }

    @Test
    public void getAllAssets_NoAssetsFound_ErrorMessageIsSet() throws Exception {
        when(assetService.getAllAssets()).thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("error", "No assets found"));
    }





}
