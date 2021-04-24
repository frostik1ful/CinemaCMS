package com.cinema.cinema.database.dao.classes;

import com.cinema.cinema.database.dao.interfaces.ChildRoomDAO;
import com.cinema.cinema.database.entity.ChildRoom;
import org.hibernate.SessionFactory;

import java.util.List;

public class ChildRoomDAOImpl extends MainDAOImpl<ChildRoom> implements ChildRoomDAO {
    public ChildRoomDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<ChildRoom> getClassType() {
        return ChildRoom.class;
    }




}
