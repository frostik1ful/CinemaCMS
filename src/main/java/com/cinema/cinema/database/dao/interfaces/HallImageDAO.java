package com.cinema.cinema.database.dao.interfaces;

import com.cinema.cinema.database.entity.HallImage;

import java.util.List;

public interface HallImageDAO extends MainDAO<HallImage> {
    void deleteImageByName(String name);

    void deleteByListOfId(List<Long> deletedImages);
}
