package com.cinema.cinema.database.dao.interfaces;

import com.cinema.cinema.database.entity.Cinema;

import java.util.Optional;

public interface CinemaDAO extends MainDAO<Cinema> {
    Optional<Cinema> findWithImagesById(long id);
    Optional<Cinema> findWithHallsById(long id);
    Optional<Cinema> findWithImagesAndHallsById(long id);
}
