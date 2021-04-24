package com.cinema.cinema.database.dao.interfaces;

import com.cinema.cinema.database.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieDAO extends MainDAO<Movie>{
    Optional<Movie> findWithImagesById(long id);
    Optional<Movie> findWithTypesById(long id);
    Optional<Movie> findWithImagesAndTypesById(long id);

    List<Movie> findAllWithTypes();
}
