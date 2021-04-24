package com.cinema.cinema.service.classes;

import com.cinema.cinema.database.dao.interfaces.AdvertisementDAO;
import com.cinema.cinema.database.dao.interfaces.AdvertisementImageDAO;
import com.cinema.cinema.database.entity.Advertisement;
import com.cinema.cinema.database.entity.AdvertisementImage;
import com.cinema.cinema.database.entity.Seo;
import com.cinema.cinema.service.interfaces.AdvertisementService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class AdvertisementServiceImpl extends MainServiceImpl<Advertisement> implements AdvertisementService {
    private final AdvertisementDAO dao;
    private final AdvertisementImageDAO advertisementImageDAO;
    public AdvertisementServiceImpl(AdvertisementDAO dao,AdvertisementImageDAO advertisementImageDAO) {
        super(dao);
        this.dao = dao;
        this.advertisementImageDAO = advertisementImageDAO;
    }

    @Override
    public List<Advertisement> findAll() {
        return dao.findAll();
    }

    @Override
    public Optional<Advertisement> findWithImages(long id) {
        return dao.findWithImages(id);
    }

    @Override
    public void createAndSaveNewAdvertisement(String advertisementName, String advertisementDescription, MultipartFile advertisementMainImage,
                                              List<MultipartFile> advertisementImages, Seo seo) {
        Advertisement advertisement = new Advertisement();
        advertisement.setName(advertisementName);
        advertisement.setDescription(advertisementDescription);
        advertisement.setSeo(seo);
        if (!advertisementMainImage.isEmpty()){
            advertisement.setMainImage(saveImageAndGetName(advertisementMainImage));
        }
        if (advertisementImages != null){
            advertisementImages.forEach(image->advertisement.addAdvertisementImage(new AdvertisementImage(saveImageAndGetName(image))));
        }
        save(advertisement);
    }

    @Override
    public void updateAdvertisement(long advertisementId, String advertisementName, String advertisementDescription, MultipartFile advertisementMainImage,
                                    List<MultipartFile> advertisementImages,List<Long> deletedImages, Seo seo) {
        findWithImages(advertisementId).ifPresent(advertisement -> {
            advertisement.setName(advertisementName);
            advertisement.setDescription(advertisementDescription);
            if (!advertisement.getSeo().equals(seo)){
                advertisement.setSeo(seo);
            }
            if (!advertisementMainImage.isEmpty()){
                advertisement.setMainImage(saveImageAndGetName(advertisementMainImage));
            }
            if (advertisementImages != null){
                advertisementImages.forEach(image-> advertisement.addAdvertisementImage(new AdvertisementImage(saveImageAndGetName(image))));
            }

            update(advertisement);

            if (deletedImages!=null){
                advertisementImageDAO.deleteByListOfId(deletedImages);
            }
        });
    }
}
