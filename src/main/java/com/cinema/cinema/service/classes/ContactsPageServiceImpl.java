package com.cinema.cinema.service.classes;

import com.cinema.cinema.database.dao.interfaces.ContactsPageDAO;
import com.cinema.cinema.database.entity.ContactsPage;
import com.cinema.cinema.service.interfaces.ContactsPageService;

import java.util.Optional;

public class ContactsPageServiceImpl extends MainServiceImpl<ContactsPage> implements ContactsPageService {
    private final ContactsPageDAO contactsPageDAO;

    public ContactsPageServiceImpl(ContactsPageDAO dao) {
        super(dao);
        this.contactsPageDAO = dao;
    }


    @Override
    public Optional<ContactsPage> findLastPage() {
        return contactsPageDAO.findLastPage();
    }
}
