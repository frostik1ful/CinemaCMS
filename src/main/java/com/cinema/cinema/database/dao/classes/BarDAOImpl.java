package com.cinema.cinema.database.dao.classes;

import com.cinema.cinema.database.dao.interfaces.BarDAO;
import com.cinema.cinema.database.entity.Bar;
import org.hibernate.SessionFactory;

import java.util.List;

public class BarDAOImpl extends MainDAOImpl<Bar> implements BarDAO {
    public BarDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Bar> getClassType() {
        return Bar.class;
    }

    @Override
    public List<Bar> findAll() {
        return null;
    }
}
