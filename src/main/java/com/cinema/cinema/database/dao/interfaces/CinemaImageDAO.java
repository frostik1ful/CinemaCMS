package com.cinema.cinema.database.dao.interfaces;

import com.cinema.cinema.database.entity.CinemaImage;

import java.util.List;

public interface CinemaImageDAO extends MainDAO<CinemaImage> {
    void deleteByImageName(String name);

    void deleteByListOfId(List<Long> deletedImages);
}
