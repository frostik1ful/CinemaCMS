package com.cinema.cinema.database.dao.classes;

import com.cinema.cinema.database.dao.interfaces.CinemaDAO;
import com.cinema.cinema.database.entity.Cinema;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class CinemaDAOImpl extends MainDAOImpl<Cinema> implements CinemaDAO {

    public CinemaDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Cinema> getClassType() {
        return Cinema.class;
    }

    @Override
    public Optional<Cinema> findWithImagesById(long id) {
        try(Session session = sessionFactory.openSession()) {
            Optional<Cinema> optional = Optional.ofNullable(session.get(getClassType(),id));
            optional.ifPresent(cinema ->{
                Hibernate.initialize(cinema.getImages());
            });
            return optional;
        }

    }

    @Override
    public Optional<Cinema> findWithHallsById(long id) {
        try(Session session = sessionFactory.openSession()) {
            Optional<Cinema> optional = Optional.ofNullable(session.get(getClassType(),id));
            optional.ifPresent(cinema ->{
                Hibernate.initialize(cinema.getHalls());
            });
            return optional;
        }
    }

    @Override
    public Optional<Cinema> findWithImagesAndHallsById(long id) {
        try(Session session = sessionFactory.openSession()) {
            Optional<Cinema> optional = Optional.ofNullable(session.get(getClassType(),id));
            optional.ifPresent(cinema ->{
                Hibernate.initialize(cinema.getImages());
                Hibernate.initialize(cinema.getHalls());
            });
            return optional;
        }
    }



    @Override
    public List<Cinema> findAll() {
        try (Session session = sessionFactory.openSession()){
            return session.createQuery("SELECT c FROM Cinema c",Cinema.class).getResultList();
        }
    }
}
