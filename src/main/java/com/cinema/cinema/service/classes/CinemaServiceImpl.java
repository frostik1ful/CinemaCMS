package com.cinema.cinema.service.classes;

import com.cinema.cinema.database.dao.interfaces.CinemaDAO;
import com.cinema.cinema.database.dao.interfaces.CinemaImageDAO;
import com.cinema.cinema.database.entity.Cinema;
import com.cinema.cinema.database.entity.CinemaImage;
import com.cinema.cinema.database.entity.Seo;
import com.cinema.cinema.service.interfaces.CinemaService;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class CinemaServiceImpl extends MainServiceImpl<Cinema> implements CinemaService {
    private final CinemaDAO cinemaDAO;
    private final CinemaImageDAO cinemaImageDAO;
    public CinemaServiceImpl(CinemaDAO cinemaDAO,CinemaImageDAO cinemaImageDAO) {
        super(cinemaDAO);
        this.cinemaDAO = cinemaDAO;
        this.cinemaImageDAO = cinemaImageDAO;
    }

    @Override
    public Optional<Cinema> findWithImagesById(long id) {
        return cinemaDAO.findWithImagesById(id);
    }

    @Override
    public Optional<Cinema> findWithHallsById(long id) {
        return cinemaDAO.findWithHallsById(id);
    }

    @Override
    public Optional<Cinema> findWithImagesAndHallsById(long id) {
        return cinemaDAO.findWithImagesAndHallsById(id);
    }

    @Override
    public String setMainImageToCinema(long cinemaId, MultipartFile file) {
        Optional<Cinema> optionalCinema = cinemaDAO.findById(cinemaId);
        if (optionalCinema.isPresent() && !file.isEmpty()){
            Cinema cinema = optionalCinema.get();
            String fileName = getRandomUUID() + file.getOriginalFilename();
            if (saveFile(fileName,file)){
                cinema.setMainImage(fileName);
                update(cinema);
                return fileName;
            }
        }

        return null;
    }

    @Override
    public List<Cinema> findAll() {
        return cinemaDAO.findAll();
    }

    @Override
    public String setLogoImageToCinema(long cinemaId, MultipartFile file) {
        Optional<Cinema> optionalCinema = cinemaDAO.findById(cinemaId);
        if (optionalCinema.isPresent() && !file.isEmpty()){
            Cinema cinema = optionalCinema.get();
            String fileName = getRandomUUID() + file.getOriginalFilename();
            if (saveFile(fileName,file)){
                cinema.setLogoImage(fileName);
                update(cinema);
                return fileName;
            }
        }
        return null;
    }

    @Override
    public String setUpperBannerImageToCinema(long cinemaId, MultipartFile file) {
        Optional<Cinema> optionalCinema = cinemaDAO.findById(cinemaId);
        if (optionalCinema.isPresent() && !file.isEmpty()){
            Cinema cinema = optionalCinema.get();
            String fileName = getRandomUUID() + file.getOriginalFilename();
            if (saveFile(fileName,file)){
                cinema.setUpperBannerImage(fileName);
                update(cinema);
                return fileName;
            }
        }
        return null;
    }

    @Override
    public void deleteCinemaImage(String cinemaImageName) {
        cinemaImageDAO.deleteByImageName(cinemaImageName);
    }

    @Override
    public CinemaImage addImageToCinema(long cinemaId, MultipartFile file) {
        Optional<Cinema> optionalCinema = cinemaDAO.findWithImagesById(cinemaId);
        if (optionalCinema.isPresent() && !file.isEmpty()){
            Cinema cinema = optionalCinema.get();
            String fileName = getRandomUUID() + file.getOriginalFilename();
            if (saveFile(fileName,file)){
                CinemaImage image = new CinemaImage(fileName);
                cinema.addCinemaImage(image);
                update(cinema);
                return image;
            }
        }

        return null;
    }

    @Override
    public void updateCinema(Long cinemaId, String cinemaName, String cinemaDescription, String cinemaRules,
                             MultipartFile mainImageFile, MultipartFile logoImageFile, MultipartFile upperBannerImageFile, List<MultipartFile> cinemaImages, List<Long> deletedImages, Seo seo) {
        findWithImagesById(cinemaId).ifPresent(cinema -> {
            cinema.setName(cinemaName);
            cinema.setDescription(cinemaDescription);
            cinema.setRules(cinemaRules);

            if (!mainImageFile.isEmpty()){
                cinema.setMainImage(saveImageAndGetName(mainImageFile));
            }
            if (!logoImageFile.isEmpty()){
                cinema.setLogoImage(saveImageAndGetName(logoImageFile));
            }
            if (!upperBannerImageFile.isEmpty()){
                cinema.setUpperBannerImage(saveImageAndGetName(upperBannerImageFile));
            }
            if (cinemaImages != null){
                cinemaImages.forEach(image-> cinema.addCinemaImage(new CinemaImage(saveImageAndGetName(image))));
            }

            if (!cinema.getSeo().equals(seo)){
                cinema.setSeo(seo);
            }
            update(cinema);

            if (deletedImages != null){
                cinemaImageDAO.deleteByListOfId(deletedImages);
            }
        });
    }

    @Override
    public void createAndSaveNewCinema(String cinemaName, String cinemaDescription, String cinemaRules, MultipartFile cinemaImage,
                                       MultipartFile cinemaLogoImage, MultipartFile cinemaUpperBannerImage, List<MultipartFile> cinemaImages, Seo seo) {
        Cinema cinema = new Cinema();
        cinema.setName(cinemaName);
        cinema.setDescription(cinemaDescription);
        cinema.setRules(cinemaRules);
        cinema.setSeo(seo);

        if (!cinemaImage.isEmpty()){
            cinema.setMainImage(saveImageAndGetName(cinemaImage));
        }
        if (!cinemaLogoImage.isEmpty()){
            cinema.setLogoImage(saveImageAndGetName(cinemaLogoImage));
        }
        if (!cinemaUpperBannerImage.isEmpty()){
            cinema.setUpperBannerImage(saveImageAndGetName(cinemaUpperBannerImage));
        }
        if (cinemaImages != null){
            cinemaImages.forEach(image -> cinema.addCinemaImage(new CinemaImage(saveImageAndGetName(image))));
        }

        cinemaDAO.save(cinema);
    }
}
