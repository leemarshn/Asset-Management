package com.lenhac.deprakt.services;

import com.lenhac.deprakt.models.Asset;
import com.lenhac.deprakt.repositories.AssetRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import static org.junit.jupiter.api.Assertions.assertEquals;
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



}