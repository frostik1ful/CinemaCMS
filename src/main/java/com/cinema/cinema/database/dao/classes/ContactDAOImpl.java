package com.cinema.cinema.database.dao.classes;

import com.cinema.cinema.database.dao.interfaces.ContactDAO;
import com.cinema.cinema.database.entity.Contact;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ContactDAOImpl extends MainDAOImpl<Contact> implements ContactDAO {
    public ContactDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Contact> getClassType() {
        return Contact.class;
    }

    @Override
    public List<Contact> findAll() {
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("SELECT c FROM Contact c",getClassType())
                    .getResultList();
        }
    }
}
