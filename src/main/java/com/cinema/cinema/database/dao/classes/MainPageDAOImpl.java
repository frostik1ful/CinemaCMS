package com.cinema.cinema.database.dao.classes;

import com.cinema.cinema.database.dao.interfaces.MainPageDAO;
import com.cinema.cinema.database.entity.MainPage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MainPageDAOImpl extends MainDAOImpl<MainPage> implements MainPageDAO {
    public MainPageDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<MainPage> getClassType() {
        return MainPage.class;
    }

    @Override
    public Optional<MainPage> findSingle() {
        try(Session session = sessionFactory.openSession()){
            return Optional.ofNullable(session.createQuery("SELECT p FROM MainPage p",getClassType()).uniqueResult());
        }
    }
}
