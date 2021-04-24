package com.cinema.cinema.service.interfaces;

import com.cinema.cinema.database.entity.Hall;
import com.cinema.cinema.database.entity.HallImage;
import com.cinema.cinema.database.entity.Seo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface HallService extends MainService<Hall>{
    Optional<Hall> findWithImagesById(long id);
    Optional<Hall> findWithImagesAndSessionsById(long id);
    List<Hall> findAll();

    String setSchemaImageToHall(long hallId, MultipartFile file);

    String setBannerImageToHall(long hallId, MultipartFile file);

    HallImage addImageToHall(long hallId, MultipartFile file);

    void deleteHallImage(String hallImageName);

    void updateHall(Long hallId, String hallName, String hallDescription,MultipartFile hallSchemaImage,MultipartFile hallBannerImage,List<MultipartFile> hallImages,List<Long> deletedImages, Seo seo);

    void createAndSaveNewHall(String hallName, String hallDescription,int countOfTickets, long cinemaId, MultipartFile hallSchemaImage, MultipartFile hallBannerImage, List<MultipartFile> hallImages, Seo seo);

}
