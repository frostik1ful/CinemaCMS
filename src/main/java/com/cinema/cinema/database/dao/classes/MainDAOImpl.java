package com.cinema.cinema.database.dao.classes;

import com.cinema.cinema.database.dao.interfaces.MainDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public abstract class MainDAOImpl<T> implements MainDAO<T> {
    protected final SessionFactory sessionFactory;
    @Autowired
    public MainDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public abstract Class<T> getClassType();

    @Override
    public List<T> findAll() {
       return null;
    }

    @Override
    public Optional<T> findById(long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(getClassType(), id));
        }

    }

    @Override
    public void delete(T object) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tr = session.beginTransaction();
            session.delete(object);
            tr.commit();
        }
    }

    @Override
    public void deleteById(long id) {
        try (Session session = sessionFactory.openSession()) {
            T object = session.get(getClassType(), id);
            if (object != null) {
                Transaction tr = session.beginTransaction();
                session.delete(object);
                tr.commit();
            }
        }
    }

    @Override
    public void update(T object) {
        try(Session session = sessionFactory.openSession()) {
            Transaction tr = session.beginTransaction();
            session.update(object);
            tr.commit();
        }
    }

    @Override
    public void save(T object) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tr = session.beginTransaction();
            session.save(object);
            tr.commit();
        }
    }

}
