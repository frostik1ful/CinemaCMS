package com.cinema.cinema.service.classes;

import com.cinema.cinema.database.dao.interfaces.ChildRoomDAO;
import com.cinema.cinema.database.entity.ChildRoom;
import com.cinema.cinema.service.interfaces.ChildRoomService;

public class ChildRoomServiceImpl extends MainServiceImpl<ChildRoom> implements ChildRoomService {
    private final ChildRoomDAO dao;
    public ChildRoomServiceImpl(ChildRoomDAO dao) {
        super(dao);
        this.dao = dao;
    }
}
