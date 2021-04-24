package com.cinema.cinema.database.dao.interfaces;

import com.cinema.cinema.database.entity.Advertisement;

import java.util.Optional;

public interface AdvertisementDAO extends MainDAO<Advertisement> {
    Optional<Advertisement> findWithImages(long id);
}
