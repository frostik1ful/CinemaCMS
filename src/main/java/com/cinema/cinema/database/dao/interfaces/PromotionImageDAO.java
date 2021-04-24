package com.cinema.cinema.database.dao.interfaces;

import com.cinema.cinema.database.entity.Promotion;
import com.cinema.cinema.database.entity.PromotionImage;

import java.util.List;

public interface PromotionImageDAO extends MainDAO<PromotionImage> {
    void deleteByListOdId(List<Long> list);
}
