package com.cinema.cinema.database.dao.interfaces;

import com.cinema.cinema.database.entity.MainPage;

import java.util.Optional;

public interface MainPageDAO extends MainDAO<MainPage> {
    Optional<MainPage> findSingle();
}
