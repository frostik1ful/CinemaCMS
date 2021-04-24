package com.cinema.cinema.service.classes;

import com.cinema.cinema.database.dao.interfaces.CinemaDAO;
import com.cinema.cinema.database.dao.interfaces.HallDAO;
import com.cinema.cinema.database.dao.interfaces.HallImageDAO;
import com.cinema.cinema.database.entity.Hall;
import com.cinema.cinema.database.entity.HallImage;
import com.cinema.cinema.database.entity.Seo;
import com.cinema.cinema.service.interfaces.HallService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
@Service
public class HallServiceImpl extends MainServiceImpl<Hall> implements HallService {
    private final HallDAO hallDAO;
    private final HallImageDAO hallImageDAO;
    private final CinemaDAO cinemaDAO;
    public HallServiceImpl(HallDAO hallDAO,HallImageDAO hallImageDAO,CinemaDAO cinemaDAO) {
        super(hallDAO);
        this.hallDAO = hallDAO;
        this.hallImageDAO = hallImageDAO;
        this.cinemaDAO = cinemaDAO;
    }

    @Override
    public Optional<Hall> findWithImagesById(long id) {
        return hallDAO.findWithImagesById(id);

    }

    @Override
    public Optional<Hall> findWithImagesAndSessionsById(long id) {
        return hallDAO.findWithImagesAndSessionsById(id);
    }

    @Override
    public List<Hall> findAll() {
        return hallDAO.findAll();
    }

    @Override
    public void updateHall(Long hallId, String hallName, String hallDescription,MultipartFile hallSchemaImage,MultipartFile hallBannerImage,
                           List<MultipartFile> hallImages,List<Long> deletedImages, Seo seo) {
        hallDAO.findWithImagesById(hallId).ifPresent(hall -> {
            hall.setName(hallName);
            hall.setDescription(hallDescription);
            if (!hall.getSeo().equals(seo)){
                hall.setSeo(seo);
            }
            if (!hallSchemaImage.isEmpty()){
                hall.setSchemaImage(saveImageAndGetName(hallSchemaImage));
            }
            if (!hallBannerImage.isEmpty()){
                hall.setBannerImage(saveImageAndGetName(hallBannerImage));
            }
            if (hallImages != null){
                hallImages.forEach(image->hall.addImage(new HallImage(saveImageAndGetName(image))));
            }
            hallDAO.update(hall);
            if (deletedImages != null){
                hallImageDAO.deleteByListOfId(deletedImages);
            }
        });
    }

    @Override
    public void createAndSaveNewHall(String hallName, String hallDescription, int countOfTickets, long cinemaId, MultipartFile hallSchemaImage,
                                     MultipartFile hallBannerImage, List<MultipartFile> hallImages, Seo seo) {
        cinemaDAO.findWithImagesAndHallsById(cinemaId).ifPresent(cinema -> {
            Hall hall = new Hall();
            hall.setName(hallName);
            hall.setDescription(hallDescription);
            hall.setCountOfTickets(countOfTickets);
            hall.setCinema(cinema);
            hall.setSeo(seo);

            if (!hallSchemaImage.isEmpty()){
                hall.setSchemaImage(saveImageAndGetName(hallSchemaImage));
            }
            if (!hallBannerImage.isEmpty()){
                hall.setBannerImage(saveImageAndGetName(hallBannerImage));
            }
            if (hallImages != null){
                hallImages.forEach(image -> hall.addImage(new HallImage(saveImageAndGetName(image))));
            }



            hallDAO.save(hall);
        });

    }

    @Override
    public String setSchemaImageToHall(long hallId, MultipartFile file) {
        Optional<Hall> optionalHall = hallDAO.findById(hallId);
        if (optionalHall.isPresent() && !file.isEmpty()){
            Hall hall = optionalHall.get();
            String fileName = getRandomUUID() + file.getOriginalFilename();
            if (saveFile(fileName,file)){
                hall.setSchemaImage(fileName);
                hallDAO.update(hall);
                return fileName;
            }
        }
        return null;
    }

    @Override
    public String setBannerImageToHall(long hallId, MultipartFile file) {
        Optional<Hall> optionalHall = hallDAO.findById(hallId);
        if (optionalHall.isPresent() && !file.isEmpty()){
            Hall hall = optionalHall.get();
            String fileName = getRandomUUID() + file.getOriginalFilename();
            if (saveFile(fileName,file)){
                hall.setBannerImage(fileName);
                hallDAO.update(hall);
                return fileName;
            }
        }
        return null;
    }

    @Override
    public HallImage addImageToHall(long hallId, MultipartFile file) {
        Optional<Hall> optionalHall = hallDAO.findById(hallId);
        if (optionalHall.isPresent() && !file.isEmpty()){
            Hall hall = optionalHall.get();
            String fileName = getRandomUUID() + file.getOriginalFilename();
            if (saveFile(fileName,file)){
                HallImage image = new HallImage(fileName);
                hall.addImage(image);
                hallDAO.update(hall);
                return image;
            }
        }
        return null;
    }

    @Override
    public void deleteHallImage(String hallImageName) {
        hallImageDAO.deleteImageByName(hallImageName);
    }


}
