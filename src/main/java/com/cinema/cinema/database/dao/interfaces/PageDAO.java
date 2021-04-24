package com.cinema.cinema.database.dao.interfaces;

import com.cinema.cinema.database.entity.Page;

import java.util.Optional;

public interface PageDAO extends MainDAO<Page> {
    Optional<Page> findWithImagesById(long pageId);
}
