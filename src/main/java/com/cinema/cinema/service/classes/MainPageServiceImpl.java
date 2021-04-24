package com.cinema.cinema.service.classes;

import com.cinema.cinema.database.dao.interfaces.MainDAO;
import com.cinema.cinema.database.dao.interfaces.MainPageDAO;
import com.cinema.cinema.database.entity.MainPage;
import com.cinema.cinema.service.interfaces.MainPageService;

public class MainPageServiceImpl extends MainServiceImpl<MainPage> implements MainPageService {
    private final MainPageDAO mainPageDAO;
    public MainPageServiceImpl(MainPageDAO mainPageDAO) {
        super(mainPageDAO);
        this.mainPageDAO = mainPageDAO;
    }
}
