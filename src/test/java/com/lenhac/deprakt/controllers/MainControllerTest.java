package com.lenhac.deprakt.controllers;

import com.lenhac.deprakt.models.Asset;
import com.lenhac.deprakt.services.AssetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@RunWith(SpringRunner.class)
@WebMvcTest(MainController.class)
public class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssetService assetService;

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

        // Set up the mock behavior for the assetService.saveAsset() method
        when(assetService.saveAsset(any(Asset.class))).thenReturn(savedAsset);

        // Perform the POST request to create an asset
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
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("asset"))
                .andExpect(model().attribute("asset", savedAsset));

        // Verify that the assetService.saveAsset() method was called with the correct asset
        verify(assetService, times(1)).saveAsset(any(Asset.class));
    }


}