package com.cinema.cinema.service.interfaces;

import com.cinema.cinema.database.entity.Contact;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ContactService extends MainService<Contact>{
    List<Contact> findAll();

    void updateContact(long contactId, String contactCinemaName, String contactAddress, String contactMapCoordinates, MultipartFile contactLogoImage);

    void createAndSaveNewContact(long contactsPageId, String contactCinemaName, String contactAddress, String contactMapCoordinates, MultipartFile contactLogoImage);


}
