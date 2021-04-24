package com.cinema.cinema.service.classes;

import com.cinema.cinema.database.dao.interfaces.MovieDAO;
import com.cinema.cinema.database.dao.interfaces.MovieImageDao;
import com.cinema.cinema.database.dao.interfaces.MovieTypeDAO;
import com.cinema.cinema.database.entity.Movie;
import com.cinema.cinema.database.entity.MovieImage;
import com.cinema.cinema.database.entity.MovieType;
import com.cinema.cinema.database.entity.Seo;
import com.cinema.cinema.service.interfaces.MovieService;
import com.cinema.cinema.util.DateTimeParser;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl extends MainServiceImpl<Movie> implements MovieService {
    private final MovieDAO dao;
    private final MovieTypeDAO movieTypeDAO;
    private final MovieImageDao movieImageDao;

    public MovieServiceImpl(MovieDAO dao, MovieTypeDAO movieTypeDAO, MovieImageDao movieImageDao) {
        super(dao);
        this.dao = dao;
        this.movieTypeDAO = movieTypeDAO;
        this.movieImageDao = movieImageDao;
    }

    @Override
    public Optional<Movie> findWithImagesById(long id) {
        return dao.findWithImagesById(id);
    }

    @Override
    public Optional<Movie> findWithTypesById(long id) {
        return dao.findWithTypesById(id);
    }

    @Override
    public Optional<Movie> findWithImagesAndTypesById(long id) {
        return dao.findWithImagesAndTypesById(id);
    }

    @Override
    public List<Movie> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Movie> findAllWithTypes() {
        return dao.findAllWithTypes();
    }

    @Override
    public List<MovieType> findAllMovieTypes() {
        return movieTypeDAO.findAll();
    }

    @Override
    public void updateMovie(long id, String movieName, String description, String dateRange, List<Long> movieTypeIds,
                            MultipartFile mainMovieImage, List<Long> deletedImages, List<MultipartFile> movieImages, String trailerLink, Seo seo) {
        findWithImagesAndTypesById(id).ifPresent(movie -> {
            movie.setName(movieName);
            movie.setDescription(description);
            try {
                Date[] dates = DateTimeParser.convertDateRangeStringToDates(dateRange);
                movie.setDateOfRelease(dates[0]);
                movie.setDateOfFinish(dates[1]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            movie.setTrailerLink(trailerLink);
            if (!mainMovieImage.isEmpty()){
                movie.setMainImage(saveImageAndGetName(mainMovieImage));
            }
            if (movieImages != null){
                movieImages.forEach(image ->{
                        movie.addMovieImage(new MovieImage(saveImageAndGetName(image)));
                });
            }

            if (!movie.getSeo().equals(seo)) {
                movie.setSeo(seo);
            }

            movie.getTypes().clear();
            if (movieTypeIds != null) {
                movie.setTypes(movieTypeDAO.findByListOfIds(movieTypeIds));
            }

            update(movie);

            if (deletedImages != null) {
                movieImageDao.deleteByListOfId(deletedImages);
            }

        });
    }

    /*@Override
    public MovieImage addImageToMovie(long id, MultipartFile file) {
        Optional<Movie> optionalMovie = dao.findWithImagesById(id);
        if (optionalMovie.isPresent() && !file.isEmpty()) {
            Movie movie = optionalMovie.get();
            String fileName = getRandomUUID() + file.getOriginalFilename();
            if (saveFile(fileName, file)) {
                MovieImage image = new MovieImage(fileName);
                movie.addMovieImage(image);
                update(movie);
                return image;
            }
        }
        return null;
    }

    @Override
    public void deleteMovieImage(String name) {
        movieImageDao.deleteByImageName(name);
    }

    @Override
    public String setMainImageToMovie(long id, MultipartFile file) {
        Optional<Movie> optionalMovie = dao.findById(id);
        if (optionalMovie.isPresent() && !file.isEmpty()) {
            Movie movie = optionalMovie.get();
            String fileName = UUID.randomUUID().toString() + file.getOriginalFilename();
            if (saveFile(fileName, file)) {
                movie.setMainImage(fileName);
                update(movie);
                return fileName;
            }
        }
        return null;
    }

    @Override
    public String addImageToNewMovie(MultipartFile file) {
        return saveImageAndGetName(file);

    }*/

    @Override
    public void createAndSaveNewMovie(String movieName, String description,String dateRange, List<Long> types, MultipartFile mainMovieImage, List<MultipartFile> movieImages, String trailerLink, Seo seo) {
        Movie movie = new Movie();
        movie.setName(movieName);
        movie.setDescription(description);
        try {
            Date[] dates = DateTimeParser.convertDateRangeStringToDates(dateRange);
            movie.setDateOfRelease(dates[0]);
            movie.setDateOfFinish(dates[1]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        movie.setTrailerLink(trailerLink);
        movie.setSeo(seo);

        if (!mainMovieImage.isEmpty()) {
            movie.setMainImage(saveImageAndGetName(mainMovieImage));
        }
        if (types != null) {
            movie.setTypes(movieTypeDAO.findByListOfIds(types));
        }
        if (movieImages != null) {
            movieImages.forEach(image -> {
                movie.addMovieImage(new MovieImage(saveImageAndGetName(image)));
            });
        }
        dao.save(movie);
    }


    @Override
    public void updateTypesInMovie(List<String> types, long id) {
        Optional<Movie> movieOptional = findById(id);
        movieOptional.ifPresent(movie -> {
            //movie.getTypes().clear();
            System.out.println(movieTypeDAO.findByListOfNames(types));
        });
    }
}
