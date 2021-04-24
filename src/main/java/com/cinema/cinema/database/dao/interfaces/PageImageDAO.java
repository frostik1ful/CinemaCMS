package com.cinema.cinema.database.dao.interfaces;

import com.cinema.cinema.database.entity.PageImage;

import java.util.List;

public interface PageImageDAO extends MainDAO<PageImage> {
    void deleteByListOfId(List<Long> deletedImages);
}
