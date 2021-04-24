package com.cinema.cinema.database.dao.interfaces;

import com.cinema.cinema.database.entity.MovieImage;

import java.util.List;
import java.util.Optional;

public interface MovieImageDao extends MainDAO<MovieImage> {
    Optional<MovieImage> findByImageName(String name);
    void deleteByImageName(String name);
    void deleteByListOfId(List<Long> list);
}
