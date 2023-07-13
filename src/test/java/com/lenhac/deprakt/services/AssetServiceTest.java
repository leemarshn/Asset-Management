package com.lenhac.deprakt.services;

import com.lenhac.deprakt.dto.AssetDTO;
import com.lenhac.deprakt.models.Asset;
import com.lenhac.deprakt.repositories.AssetRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

class AssetServiceTest {

    @Mock
    private AssetRepo assetRepo;
    private AssetService assetService;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void saveAsset() {
        String purchaseDate = "12/12/2023";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate localDate = LocalDate.parse(purchaseDate, formatter);
        java.sql.Date sqlPurchaseDate = java.sql.Date.valueOf(localDate);

        Asset asset = new Asset();
        asset.setName("Sample Asset");
        asset.setType("Sample Type");
        asset.setUser("Sample User");
        asset.setSupplier("Sample Supplier");
        asset.setModel("Sample Model");
        asset.setMake("Sample Make");
        asset.setShelfLife(5);
        asset.setValue(1000.0);
        asset.setPurchaseDate(sqlPurchaseDate);
        asset.setNotes("Sample Notes");

        AssetRepo assetRepo = Mockito.mock(AssetRepo.class);
        Asset savedAsset = new Asset();
//        savedAsset.setId(1L);
        when(assetRepo.save(asset)).thenReturn(savedAsset);

        AssetService assetService = new AssetService(assetRepo);
        savedAsset = assetService.saveAsset(asset);


        Mockito.verify(assetRepo, Mockito.times(1)).save(asset);

        assertEquals("Sample Asset", savedAsset.getName());
        assertEquals("Sample Type", savedAsset.getType());
        assertEquals("Sample User", savedAsset.getUser());
        assertEquals("Sample Supplier", savedAsset.getSupplier());
        assertEquals("Sample Model", savedAsset.getModel());
        assertEquals("Sample Make", savedAsset.getMake());
        assertEquals(5, savedAsset.getShelfLife());
        assertEquals(1000.0, savedAsset.getValue());
        // Assert that the savedAsset has the correct date
        assertEquals(localDate, sqlPurchaseDate.toLocalDate());
        assertEquals("Sample Notes", savedAsset.getNotes());
    }

    @Test
    void calculateDepreciationDate() {
        AssetService assetService = new AssetService(assetRepo);

        String purchaseDate = "2022-01-17";
        int shelfLife = 6;
        String expectedDepreciationDate = "January 17th 2028";
        String actualDepreciationDate = assetService.calculateDepreciationDate(purchaseDate, shelfLife);
        assertEquals(expectedDepreciationDate, actualDepreciationDate);

    }

    @Test
    void testGetAllAssets() {
        // Create a sample Asset
        Asset asset = new Asset();
        asset.setId(1L);
        asset.setName("Asset 1");
        asset.setUser("User 1");
        asset.setValue(1000.0);

        // Set the purchaseDate to a specific date
        LocalDate purchaseDate = LocalDate.of(2023, 7, 5);
        asset.setPurchaseDate(java.sql.Date.valueOf(purchaseDate));

        asset.setShelfLife(3);

        // Mock the assetRepository.findAll() method
        List<Asset> assets = Collections.singletonList(asset);
        Mockito.when(assetRepo.findAll()).thenReturn(assets);

        // Perform the getAllAssets() operation
        List<AssetDTO> assetDTOs = assetService.getAllAssets();

        // Verify the result
        assertEquals(1, assetDTOs.size());
        AssetDTO assetDTO = assetDTOs.get(0);
        assertEquals(asset.getId(), assetDTO.getId());
        assertEquals(asset.getName(), assetDTO.getName());
        assertEquals(asset.getUser(), assetDTO.getUser());
        assertEquals(asset.getValue(), assetDTO.getValue(), 0.0);
        assertNotNull(assetDTO.getDepreciationDate());
    }

    @Test
    public void testGetAllAssetsWithNullDate() {
        // Create a sample Asset with null purchaseDate
        Asset asset = new Asset();
        asset.setId(1L);
        asset.setName("Asset 1");
        asset.setUser("User 1");
        asset.setValue(1000.0);
        asset.setPurchaseDate(null);
        asset.setShelfLife(3);

        // Mock the assetRepository.findAll() method
        List<Asset> assets = Collections.singletonList(asset);
        Mockito.when(assetRepo.findAll()).thenReturn(assets);

        // Perform the getAllAssets() operation
        List<AssetDTO> assetDTOs = assetService.getAllAssets();

        // Verify the result
        assertEquals(1, assetDTOs.size());
        AssetDTO assetDTO = assetDTOs.get(0);
        assertEquals(asset.getId(), assetDTO.getId());
        assertEquals(asset.getName(), assetDTO.getName());
        assertEquals(asset.getUser(), assetDTO.getUser());
        assertEquals(asset.getValue(), assetDTO.getValue(), 0.0);
        assertNull(assetDTO.getDepreciationDate());
    }

}