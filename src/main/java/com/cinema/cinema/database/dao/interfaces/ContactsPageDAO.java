package com.cinema.cinema.database.dao.interfaces;

import com.cinema.cinema.database.entity.ContactsPage;

import java.util.Optional;

public interface ContactsPageDAO extends MainDAO<ContactsPage> {
    Optional<ContactsPage> findLastPage();

    Optional<ContactsPage> findSingle();

    Optional<ContactsPage> findSingleWithContacts();
}
