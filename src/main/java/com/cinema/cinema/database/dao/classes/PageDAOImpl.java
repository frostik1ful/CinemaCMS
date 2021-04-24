package com.cinema.cinema.database.dao.classes;

import com.cinema.cinema.database.dao.interfaces.PageDAO;
import com.cinema.cinema.database.entity.Page;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PageDAOImpl extends MainDAOImpl<Page> implements PageDAO {
    public PageDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Page> getClassType() {
        return Page.class;
    }

    @Override
    public List<Page> findAll() {
        try (Session session = sessionFactory.openSession()){
            return session.createQuery("SELECT p FROM Page p",getClassType()).getResultList();
        }
    }

    @Override
    public Optional<Page> findWithImagesById(long pageId) {
        try (Session session = sessionFactory.openSession()){
            Optional<Page> optionalPage = Optional.ofNullable(session.get(getClassType(),pageId));
            optionalPage.ifPresent(page -> Hibernate.initialize(page.getImages()));
            return optionalPage;
        }
    }
}
