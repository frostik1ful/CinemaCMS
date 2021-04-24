package com.cinema.cinema.database.dao.classes;

import com.cinema.cinema.database.dao.interfaces.MovieTypeDAO;
import com.cinema.cinema.database.entity.MovieType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MovieTypeDAOImpl extends MainDAOImpl<MovieType> implements MovieTypeDAO {
    public MovieTypeDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<MovieType> getClassType() {
        return MovieType.class;
    }

    @Override
    public List<MovieType> findAll() {
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("SELECT t FROM MovieType t",MovieType.class).getResultList();
        }
    }

    @Override
    public Optional<MovieType> findByName(String name) {
        try(Session session = sessionFactory.openSession()){
            return Optional.ofNullable((MovieType) session
                    .createQuery("SELECT t FROM MovieType t WHERE t.name = :name")
                    .setParameter("name",name).uniqueResult());
        }
    }

    @Override
    public List<MovieType> findByListOfNames(List<String> names) {
        try(Session session = sessionFactory.openSession()){
            return  session
                    .createQuery("SELECT t FROM MovieType t WHERE t.name IN (:names)",MovieType.class)
                    .setParameter("names",names).getResultList();
        }
    }

    @Override
    public List<MovieType> findByListOfIds(List<Long> ids) {
        try(Session session = sessionFactory.openSession()){
            return  session
                    .createQuery("SELECT t FROM MovieType t WHERE t.movieTypeId IN (:ids)",MovieType.class)
                    .setParameter("ids",ids).getResultList();
        }
    }
}
