package com.cinema.cinema.service.classes;

import com.cinema.cinema.database.dao.interfaces.VipHallPageDAO;
import com.cinema.cinema.database.entity.VipHallPage;
import com.cinema.cinema.service.interfaces.VipHallPageService;

public class VipHallPageServiceImpl extends MainServiceImpl<VipHallPage> implements VipHallPageService {
    private final VipHallPageDAO dao;
    public VipHallPageServiceImpl(VipHallPageDAO dao) {
        super(dao);
        this.dao = dao;
    }
}
