package com.cinema.cinema.service.classes;

import com.cinema.cinema.database.dao.interfaces.PromotionDAO;
import com.cinema.cinema.database.dao.interfaces.PromotionImageDAO;
import com.cinema.cinema.database.entity.Promotion;
import com.cinema.cinema.database.entity.PromotionImage;
import com.cinema.cinema.database.entity.Seo;
import com.cinema.cinema.service.interfaces.PromotionService;
import com.cinema.cinema.util.DateTimeParser;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

@Service
public class PromotionServiceImpl extends MainServiceImpl<Promotion> implements PromotionService {
    private final PromotionDAO dao;
    private final PromotionImageDAO promotionImageDAO;
    public PromotionServiceImpl(PromotionDAO dao,PromotionImageDAO promotionImageDAO) {
        super(dao);
        this.dao = dao;
        this.promotionImageDAO = promotionImageDAO;
    }

    @Override
    public List<Promotion> findAll() {
        return dao.findAll();
    }

    @Override
    public void updatePromotion(long promotionId, String promotionName, String promotionDescription, String promotionVideoLink,
                                boolean promotionStatus, String promotionPublishDate, MultipartFile promotionMainImage, List<MultipartFile> promotionImages, List<Long> deletedImages, Seo seo) {
        findById(promotionId).ifPresent(promotion -> {
            promotion.setName(promotionName);
            promotion.setDescription(promotionDescription);
            promotion.setVideoLink(promotionVideoLink);
            promotion.setActive(promotionStatus);
            try {
                promotion.setPublishDate(DateTimeParser.convertStringToDate(promotionPublishDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            seo.setSeoId(promotion.getSeo().getSeoId());
            promotion.setSeo(seo);
            if (!promotionMainImage.isEmpty()){
                promotion.setMainImage(saveImageAndGetName(promotionMainImage));
            }
            if (promotionImages != null){
                promotionImages.forEach(image->promotion.addPromotionImage(new PromotionImage(saveImageAndGetName(image))));
            }
            update(promotion);

            if (deletedImages != null){
                promotionImageDAO.deleteByListOdId(deletedImages);
            }
        });
    }

    @Override
    public void createAndSaveNewPromotion(String promotionName, String promotionDescription, String promotionVideoLink, boolean promotionStatus, String promotionPublishDate, MultipartFile promotionMainImage,List<MultipartFile> promotionImages, Seo seo) {
        Date publishDate = null;
        try {
            publishDate = DateTimeParser.convertStringToDate(promotionPublishDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Promotion promotion = new Promotion(promotionName,Date.valueOf(LocalDate.now()),publishDate,promotionDescription,promotionVideoLink,promotionStatus,seo);
        if (!promotionMainImage.isEmpty()){
            promotion.setMainImage(saveImageAndGetName(promotionMainImage));
        }
        if (promotionImages != null){
            promotionImages.forEach(image -> promotion.addPromotionImage(new PromotionImage(saveImageAndGetName(image))));
        }
        save(promotion);
    }
}
