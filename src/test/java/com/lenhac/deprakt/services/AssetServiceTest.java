package com.lenhac.deprakt.services;

import com.lenhac.deprakt.models.Asset;
import com.lenhac.deprakt.repositories.AssetRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class AssetServiceTest {

    @Mock
    private AssetRepo assetRepo;

    @InjectMocks
    private AssetService assetService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void saveAsset() {
        // Create a sample asset

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
        Mockito.when(assetRepo.save(asset)).thenReturn(savedAsset);

        // Create an instance of AssetService and invoke the saveAsset() method
        // Create an instance of AssetService and invoke the saveAsset() method
        AssetService assetService = new AssetService(assetRepo);
        savedAsset = assetService.saveAsset(asset);


        // Verify the assetRepo.save() method is called once
        Mockito.verify(assetRepo, Mockito.times(1)).save(asset);


        // Assert that the savedAsset has the correct values
        Assertions.assertEquals("Sample Asset", savedAsset.getName());
        Assertions.assertEquals("Sample Type", savedAsset.getType());
        Assertions.assertEquals("Sample User", savedAsset.getUser());
        Assertions.assertEquals("Sample Supplier", savedAsset.getSupplier());
        Assertions.assertEquals("Sample Model", savedAsset.getModel());
        Assertions.assertEquals("Sample Make", savedAsset.getMake());
        Assertions.assertEquals(5, savedAsset.getShelfLife());
        Assertions.assertEquals(1000.0, savedAsset.getValue());

// Assert that the savedAsset has the correct date
        Assertions.assertEquals(localDate, sqlPurchaseDate.toLocalDate());
        Assertions.assertEquals("Sample Notes", savedAsset.getNotes());
    }
}