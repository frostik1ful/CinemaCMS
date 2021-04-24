package com.cinema.cinema.service.interfaces;

import com.cinema.cinema.database.entity.Movie;
import com.cinema.cinema.database.entity.MovieImage;
import com.cinema.cinema.database.entity.MovieType;
import com.cinema.cinema.database.entity.Seo;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface MovieService extends MainService<Movie>{
    Optional<Movie> findWithImagesById(long id);
    Optional<Movie> findWithTypesById(long id);
    Optional<Movie> findWithImagesAndTypesById(long id);
    List<Movie> findAll();
    List<Movie> findAllWithTypes();
    List<MovieType> findAllMovieTypes();
    void updateMovie(long id, String movieName, String description, String dateRange, List<Long> types, MultipartFile mainMovieImage, List<Long> deletedImages,
                     List<MultipartFile> movieImages, String trailerLink, Seo seo);
    /*MovieImage addImageToMovie(long id, MultipartFile file);
    void deleteMovieImage(String name);
    String setMainImageToMovie(long id, MultipartFile file);
    String addImageToNewMovie(MultipartFile file);*/
    void createAndSaveNewMovie(String movieName, String description,String dateRange, List<Long> types,MultipartFile mainMovieImage,
                                List<MultipartFile> movieImages, String trailerLink, Seo seo);
    void updateTypesInMovie(List<String> types,long id);


}
