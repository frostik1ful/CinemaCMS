package com.cinema.cinema.service.interfaces;

import com.cinema.cinema.database.entity.AdvertisementImage;
import com.cinema.cinema.database.entity.Cinema;
import com.cinema.cinema.database.entity.CinemaImage;
import com.cinema.cinema.database.entity.Seo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface CinemaService extends MainService<Cinema> {
    Optional<Cinema> findWithImagesById(long id);

    Optional<Cinema> findWithHallsById(long id);

    Optional<Cinema> findWithImagesAndHallsById(long id);

    String setMainImageToCinema(long cinemaId, MultipartFile file);

    List<Cinema> findAll();

    String setLogoImageToCinema(long cinemaId, MultipartFile file);

    String setUpperBannerImageToCinema(long cinemaId, MultipartFile file);

    void deleteCinemaImage(String cinemaImageName);

    CinemaImage addImageToCinema(long cinemaId, MultipartFile file);

    void updateCinema(Long cinemaId, String cinemaName, String cinemaDescription, String cinemaRules, MultipartFile mainImageFile, MultipartFile logoImageFile, MultipartFile UpperBannerImageFile, List<MultipartFile> cinemaImages, List<Long> deletedImages, Seo seo);

    void createAndSaveNewCinema(String cinemaName, String cinemaDescription, String cinemaRules, MultipartFile cinemaImage, MultipartFile cinemaLogoImage, MultipartFile cinemaUpperBannerImage, List<MultipartFile> cinemaImages, Seo seo);

}
