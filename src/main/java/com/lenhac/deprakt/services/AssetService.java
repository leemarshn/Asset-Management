package com.lenhac.deprakt.services;

import com.lenhac.deprakt.models.Asset;
import com.lenhac.deprakt.repositories.AssetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
