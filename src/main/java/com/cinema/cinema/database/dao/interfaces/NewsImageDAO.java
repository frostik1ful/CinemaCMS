package com.cinema.cinema.database.dao.interfaces;

import com.cinema.cinema.database.entity.NewsImage;

import java.util.List;

public interface NewsImageDAO extends MainDAO<NewsImage> {
    void deleteByListOfId(List<Long> list);
}
