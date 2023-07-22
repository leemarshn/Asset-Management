package com.lenhac.deprakt.services;

import com.lenhac.deprakt.models.AssetV1;
import com.lenhac.deprakt.repositories.AssetRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class AssetV1ServiceTest {

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

        AssetV1 assetV1 = new AssetV1();
        assetV1.setName("Sample Asset");
        assetV1.setType("Sample Type");
        assetV1.setUser("Sample User");
        assetV1.setSupplier("Sample Supplier");
        assetV1.setModel("Sample Model");
        assetV1.setMake("Sample Make");
        assetV1.setShelfLife(5);
        assetV1.setValue(1000.0);
        assetV1.setPurchaseDate(sqlPurchaseDate);
        assetV1.setNotes("Sample Notes");

        AssetRepo assetRepo = Mockito.mock(AssetRepo.class);
        AssetV1 savedAssetV1 = new AssetV1();
//        savedAsset.setId(1L);
        when(assetRepo.save(assetV1)).thenReturn(savedAssetV1);

        AssetService assetService = new AssetService(assetRepo);
        savedAssetV1 = assetService.saveAsset(assetV1);


        Mockito.verify(assetRepo, Mockito.times(1)).save(assetV1);

        assertEquals("Sample Asset", savedAssetV1.getName());
        assertEquals("Sample Type", savedAssetV1.getType());
        assertEquals("Sample User", savedAssetV1.getUser());
        assertEquals("Sample Supplier", savedAssetV1.getSupplier());
        assertEquals("Sample Model", savedAssetV1.getModel());
        assertEquals("Sample Make", savedAssetV1.getMake());
        assertEquals(5, savedAssetV1.getShelfLife());
        assertEquals(1000.0, savedAssetV1.getValue());
        // Assert that the savedAsset has the correct date
        assertEquals(localDate, sqlPurchaseDate.toLocalDate());
        assertEquals("Sample Notes", savedAssetV1.getNotes());
    }

    @Test
    public void calculateDepreciationDate_ReturnsDepreciationDate() throws Exception {
        Date purchaseDateString = Date.valueOf("2023-02-11");
        int shelfLife = 6;
        String expectedDepreciationDate = "February, 2029";

        String actualDepreciationDate = AssetService.calculateDepreciationDate(purchaseDateString, shelfLife);

        assertEquals(expectedDepreciationDate, actualDepreciationDate);
    }

    @Test
    public void calculateDepreciationDate_DoesNotThrowException_WhenPurchaseDateStringIsValid() throws Exception {
        int shelfLife = 5;

        String depreciationDate = AssetService.calculateDepreciationDate(null, shelfLife);
        assertEquals("Invalid purchase date string", depreciationDate);
    }
}