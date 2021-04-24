package com.cinema.cinema.database.dao.interfaces;

import com.cinema.cinema.database.entity.MovieType;

import java.util.List;
import java.util.Optional;

public interface MovieTypeDAO extends MainDAO<MovieType> {
    Optional<MovieType> findByName(String name);
    List<MovieType> findByListOfNames(List<String> names);
    List<MovieType> findByListOfIds(List<Long> ids);
}
