package com.cinema.cinema.database.dao.classes;

import com.cinema.cinema.database.dao.interfaces.LanguageDAO;
import com.cinema.cinema.database.entity.Language;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class LanguageDAOImpl extends MainDAOImpl<Language> implements LanguageDAO {
    public LanguageDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Language> getClassType() {
        return Language.class;
    }

    @Override
    public List<Language> findAll() {
        try (Session session = sessionFactory.openSession()){
            return session.createQuery("SELECT l FROM Language l",Language.class).getResultList();
        }

    }
}
