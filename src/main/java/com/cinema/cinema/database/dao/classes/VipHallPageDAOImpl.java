package com.cinema.cinema.database.dao.classes;

import com.cinema.cinema.database.dao.interfaces.VipHallPageDAO;
import com.cinema.cinema.database.entity.VipHallPage;
import org.hibernate.SessionFactory;

import java.util.List;

public class VipHallPageDAOImpl extends MainDAOImpl<VipHallPage> implements VipHallPageDAO {
    public VipHallPageDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<VipHallPage> getClassType() {
        return VipHallPage.class;
    }

    @Override
    public List<VipHallPage> findAll() {
        return null;
    }
}
