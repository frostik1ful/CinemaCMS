package com.cinema.cinema.service.classes;

import com.cinema.cinema.database.dao.interfaces.BarDAO;
import com.cinema.cinema.database.entity.Bar;
import com.cinema.cinema.service.interfaces.BarService;

public class BarServiceImpl extends MainServiceImpl<Bar> implements BarService {
    private final BarDAO dao;
    public BarServiceImpl(BarDAO dao) {
        super(dao);
        this.dao = dao;
    }
}
