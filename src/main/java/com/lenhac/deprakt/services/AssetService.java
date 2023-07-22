package com.lenhac.deprakt.services;

import com.lenhac.deprakt.dto.AssetDTO;
import com.lenhac.deprakt.models.AssetV1;
import com.lenhac.deprakt.repositories.AssetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class AssetService {
    private final AssetRepo assetRepository;

    @Autowired
    public AssetService(AssetRepo assetRepository) {
        this.assetRepository = assetRepository;
    }

    public AssetV1 saveAsset(AssetV1 assetV1) {
        assetRepository.save(assetV1);
        return assetV1;
    }

    public static String calculateDepreciationDate(Date purchaseDateString, int shelfLife) {
        try {
            Calendar depreciationCalendar = Calendar.getInstance();
            depreciationCalendar.setTime(purchaseDateString);
            depreciationCalendar.add(Calendar.YEAR, shelfLife);
            depreciationCalendar.set(Calendar.DAY_OF_MONTH, depreciationCalendar.get(Calendar.DAY_OF_MONTH));

            Locale locale = Locale.getDefault();
            String depreciationMonth = depreciationCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, locale);
            String depreciationYear = String.valueOf(depreciationCalendar.get(Calendar.YEAR));

            return depreciationMonth + ", " + depreciationYear;
        } catch (Exception e) {
            return "Invalid purchase date string";
        }
    }

    public String currencyFormat(double num) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(num);
    }
    public List<AssetDTO> getAllAssets() {
        List<AssetV1> assetV1s = assetRepository.findAll();

        return assetV1s.stream()
                .map(asset -> {
                    Date purchaseDate = asset.getPurchaseDate();
                    int shelfLife = asset.getShelfLife();
                    String depreciationDate = null;
                    if (purchaseDate != null) {
                        try {
                            depreciationDate = calculateDepreciationDate(purchaseDate, shelfLife);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    return new AssetDTO(asset.getId(), asset.getName(), asset.getUser(), currencyFormat(asset.getValue()), depreciationDate);
                })
                .collect(Collectors.toList());
    }
    public AssetV1 getAssetById(Long id) {
        return assetRepository.findById(id).orElse(null);
    }
}
