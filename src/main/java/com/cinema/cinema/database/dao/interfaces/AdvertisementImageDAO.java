package com.cinema.cinema.database.dao.interfaces;

import com.cinema.cinema.database.entity.AdvertisementImage;

import java.util.List;

public interface AdvertisementImageDAO extends MainDAO<AdvertisementImage> {
    public void deleteByListOfId(List<Long> list);
}
