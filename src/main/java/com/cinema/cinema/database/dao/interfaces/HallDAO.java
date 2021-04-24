package com.cinema.cinema.database.dao.interfaces;

import com.cinema.cinema.database.entity.Hall;

import java.util.Optional;

public interface HallDAO extends MainDAO<Hall>{
    Optional<Hall> findWithImagesById(long id);
    public Optional<Hall> findWithImagesAndSessionsById(long id);

}
