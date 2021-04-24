package com.cinema.cinema.service.interfaces;

import com.cinema.cinema.database.entity.Promotion;
import com.cinema.cinema.database.entity.Seo;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.List;

public interface PromotionService extends MainService<Promotion>{
    List<Promotion> findAll();

    void updatePromotion(long promotionId, String promotionName, String promotionDescription, String promotionVideoLink, boolean promotionStatus, String promotionPublishDate, MultipartFile promotionMainImage, List<MultipartFile> promotionImages, List<Long> deletedImages, Seo seo);

    void createAndSaveNewPromotion(String promotionName, String promotionDescription, String promotionVideoLink, boolean promotionStatus, String promotionPublishDate, MultipartFile promotionMainImage, List<MultipartFile> promotionImages, Seo seo);
}
