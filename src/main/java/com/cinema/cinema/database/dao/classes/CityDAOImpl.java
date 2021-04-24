package com.cinema.cinema.database.dao.classes;

import com.cinema.cinema.database.dao.interfaces.CityDAO;
import com.cinema.cinema.database.entity.City;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public class CityDAOImpl extends MainDAOImpl<City> implements CityDAO {
    public CityDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<City> getClassType() {
        return City.class;
    }

    @Override
    public List<City> findAll() {
        try (Session session = sessionFactory.openSession()){
            return session.createQuery("SELECT c FROM City c",City.class).getResultList();
        }
    }
}
