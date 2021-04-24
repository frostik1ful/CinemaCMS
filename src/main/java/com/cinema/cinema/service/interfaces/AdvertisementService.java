package com.cinema.cinema.service.interfaces;

import com.cinema.cinema.database.entity.Advertisement;
import com.cinema.cinema.database.entity.Seo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface AdvertisementService extends MainService<Advertisement>{
    List<Advertisement> findAll();
    Optional<Advertisement> findWithImages(long id);
    void createAndSaveNewAdvertisement(String advertisementName, String advertisementDescription, MultipartFile advertisementMainImage, List<MultipartFile> advertisementImages, Seo seo);

    void updateAdvertisement(long advertisementId, String advertisementName, String advertisementDescription, MultipartFile advertisementMainImage, List<MultipartFile> advertisementImages,List<Long> deletedImages, Seo seo);
}
