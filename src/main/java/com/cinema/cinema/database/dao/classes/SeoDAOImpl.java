package com.cinema.cinema.database.dao.classes;

import com.cinema.cinema.database.dao.interfaces.SeoDAO;
import com.cinema.cinema.database.entity.Seo;
import org.hibernate.SessionFactory;

import java.util.List;

public class SeoDAOImpl extends MainDAOImpl<Seo> implements SeoDAO {
    public SeoDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Seo> getClassType() {
        return Seo.class;
    }

    @Override
    public List<Seo> findAll() {
        return null;
    }
}
