package com.lenhac.deprakt.services;

import com.lenhac.deprakt.dto.AssetDTO;
import com.lenhac.deprakt.models.Asset;
import com.lenhac.deprakt.repositories.AssetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssetService {
    private final AssetRepo assetRepository;

    @Autowired
    public AssetService(AssetRepo assetRepository) {
        this.assetRepository = assetRepository;
    }

    public Asset saveAsset(Asset asset) {
        assetRepository.save(asset);
        return asset;
    }

    public String calculateDepreciationDate(String purchaseDate, int shelfLife) {
        LocalDate date = LocalDate.parse(purchaseDate);
        LocalDate depreciationDate = date.plusYears(shelfLife);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d'th' yyyy");

        return depreciationDate.format(formatter);
    }

    public List<AssetDTO> getAllAssets() {
        List<Asset> assets = assetRepository.findAll();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return assets.stream()
                .map(asset -> {
                    Date purchaseDate = asset.getPurchaseDate();
                    int shelfLife = asset.getShelfLife();
                    String depreciationDate = null;
                    if (purchaseDate != null) {
                        depreciationDate = calculateDepreciationDate(dateFormat.format(purchaseDate), shelfLife);
                    }
                    return new AssetDTO(asset.getId(), asset.getName(), asset.getUser(), asset.getValue(), depreciationDate);
                })
                .collect(Collectors.toList());
    }

    public Asset getAssetById(Long id) {
        return assetRepository.findById(id).orElse(null);
    }




}
