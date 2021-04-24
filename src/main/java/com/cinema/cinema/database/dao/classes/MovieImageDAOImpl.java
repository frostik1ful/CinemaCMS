package com.cinema.cinema.database.dao.classes;

import com.cinema.cinema.database.dao.interfaces.MovieImageDao;
import com.cinema.cinema.database.entity.MovieImage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MovieImageDAOImpl extends MainDAOImpl<MovieImage> implements MovieImageDao {
    public MovieImageDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<MovieImage> getClassType() {
        return MovieImage.class;
    }


    @Override
    public List<MovieImage> findAll() {
        try (Session session = sessionFactory.openSession()){
            return session.createQuery("SELECT m FROM MovieImage m",MovieImage.class).getResultList();
        }
    }

    @Override
    public Optional<MovieImage> findByImageName(String name) {
        try (Session session = sessionFactory.openSession()){
            return Optional.ofNullable(session
                    .createQuery("SELECT img FROM MovieImage img WHERE img.image =:name",MovieImage.class)
                    .setParameter("name",name)
                    .uniqueResult());
        }
    }

    @Override
    public void deleteByImageName(String name) {
        try (Session session = sessionFactory.openSession()){
            Transaction tr = session.beginTransaction();
            session.createQuery("DELETE FROM MovieImage img WHERE img.image =:name")
                    .setParameter("name",name)
                    .executeUpdate();
            tr.commit();
        }
    }

    @Override
    public void deleteByListOfId(List<Long> list) {
        try (Session session = sessionFactory.openSession()){
            Transaction tr = session.beginTransaction();
            session.createQuery("DELETE FROM MovieImage img WHERE img.id IN :list")
                    .setParameter("list",list)
                    .executeUpdate();
            tr.commit();
        }
    }
}
