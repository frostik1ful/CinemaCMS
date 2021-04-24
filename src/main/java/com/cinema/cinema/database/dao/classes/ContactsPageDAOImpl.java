package com.cinema.cinema.database.dao.classes;

import com.cinema.cinema.database.dao.interfaces.ContactsPageDAO;
import com.cinema.cinema.database.entity.ContactsPage;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class ContactsPageDAOImpl extends MainDAOImpl<ContactsPage> implements ContactsPageDAO {
    public ContactsPageDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<ContactsPage> getClassType() {
        return ContactsPage.class;
    }

    @Override
    public Optional<ContactsPage> findLastPage() {
        try(Session session = sessionFactory.openSession()) {
            ContactsPage page = (ContactsPage) session
                    .createSQLQuery("SELECT * FROM contactspage c  ORDER BY contactsPageId DESC LIMIT 1")
                    .addEntity(ContactsPage.class)
                    .uniqueResult();
            return Optional.ofNullable(page);
        }
    }

    @Override
    public Optional<ContactsPage> findSingle() {
        try (Session session = sessionFactory.openSession()){
            return Optional.ofNullable(session.createQuery("SELECT p FROM ContactsPage p",getClassType()).uniqueResult());
        }
    }

    @Override
    public Optional<ContactsPage> findSingleWithContacts() {
        try (Session session = sessionFactory.openSession()){
            Optional<ContactsPage> optionalContactsPage = Optional.ofNullable(session.createQuery("SELECT p FROM ContactsPage p",getClassType()).uniqueResult());
            optionalContactsPage.ifPresent(page -> Hibernate.initialize(page.getContacts()));
            return optionalContactsPage;
        }
    }

    @Override
    public List<ContactsPage> findAll() {
        return null;
    }
}
