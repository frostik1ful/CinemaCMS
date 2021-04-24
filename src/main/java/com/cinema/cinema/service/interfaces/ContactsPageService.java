package com.cinema.cinema.service.interfaces;

import com.cinema.cinema.database.entity.ContactsPage;

import java.util.Optional;

public interface ContactsPageService extends MainService<ContactsPage>{
    Optional<ContactsPage> findLastPage();
}
