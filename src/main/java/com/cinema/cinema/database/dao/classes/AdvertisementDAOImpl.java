package com.cinema.cinema.database.dao.classes;

import com.cinema.cinema.database.dao.interfaces.AdvertisementDAO;
import com.cinema.cinema.database.entity.Advertisement;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AdvertisementDAOImpl extends MainDAOImpl<Advertisement> implements AdvertisementDAO {
    public AdvertisementDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Advertisement> getClassType() {
        return Advertisement.class;
    }

    @Override
    public List<Advertisement> findAll() {
        try (Session session = sessionFactory.openSession()){
            return session
                    .createQuery("SELECT a FROM Advertisement a",Advertisement.class)
                    .getResultList();
        }
    }

    @Override
    public Optional<Advertisement> findWithImages(long id) {
        try (Session session = sessionFactory.openSession()){
            Optional<Advertisement> optional = Optional.ofNullable(session.get(getClassType(),id));
            optional.ifPresent(advertisement -> Hibernate.initialize(advertisement.getImages()));
            return optional;
        }
    }
}
