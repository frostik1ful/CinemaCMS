package com.cinema.cinema.database.dao.interfaces;

import com.cinema.cinema.database.entity.News;

import java.util.Optional;

public interface NewsDAO extends MainDAO<News>{
    Optional<News> findWithImagesById(long id);
}
