package com.cinema.cinema.database.dao.classes;

import com.cinema.cinema.database.dao.interfaces.HallDAO;
import com.cinema.cinema.database.entity.Hall;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class HallDAOImpl extends MainDAOImpl<Hall> implements HallDAO {
    public HallDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Hall> getClassType() {
        return Hall.class;
    }

    @Override
    public Optional<Hall> findWithImagesById(long id) {
        try(Session session = sessionFactory.openSession()) {
            Optional<Hall> optional = Optional.ofNullable(session.get(getClassType(),id));
            optional.ifPresent(hall -> Hibernate.initialize(hall.getImages()));
            return optional;
        }
    }

    public Optional<Hall> findWithImagesAndSessionsById(long id){
        try(Session session = sessionFactory.openSession()) {
            Optional<Hall> optional = Optional.ofNullable(session.get(getClassType(),id));
            optional.ifPresent(hall ->{
                Hibernate.initialize(hall.getImages());
                Hibernate.initialize(hall.getSessions());
            } );
            return optional;
        }
    }

    @Override
    public List<Hall> findAll() {
        try (Session session = sessionFactory.openSession()){
            return session.createQuery("SELECT h FROM Hall h",Hall.class).getResultList();
        }
    }
}
