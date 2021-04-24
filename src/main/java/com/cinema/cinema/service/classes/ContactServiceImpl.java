package com.cinema.cinema.service.classes;

import com.cinema.cinema.database.dao.interfaces.ContactDAO;
import com.cinema.cinema.database.dao.interfaces.ContactsPageDAO;
import com.cinema.cinema.database.entity.Contact;
import com.cinema.cinema.service.interfaces.ContactService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl extends MainServiceImpl<Contact> implements ContactService {
    private final ContactDAO contactDAO;
    private final ContactsPageDAO contactsPageDAO;
    public ContactServiceImpl(ContactDAO contactDAO, ContactsPageDAO contactsPageDAO) {
        super(contactDAO);
        this.contactDAO = contactDAO;
        this.contactsPageDAO = contactsPageDAO;
    }

    @Override
    public List<Contact> findAll() {
        return contactDAO.findAll();
    }

    @Override
    public void updateContact(long contactId, String contactCinemaName, String contactAddress, String contactMapCoordinates,
                              MultipartFile contactLogoImage) {
        contactDAO.findById(contactId).ifPresent(contact -> {
            contact.setCinemaName(contactCinemaName);
            contact.setAddress(contactAddress);
            contact.setMapCoordinates(contactMapCoordinates);
            if (!contactLogoImage.isEmpty()){
                contact.setLogoImage(saveImageAndGetName(contactLogoImage));
            }
            update(contact);
        });
    }

    @Override
    public void createAndSaveNewContact(long contactsPageId, String contactCinemaName, String contactAddress, String contactMapCoordinates, MultipartFile contactLogoImage) {
        contactsPageDAO.findSingleWithContacts().ifPresent(page -> {
            if (page.getContactsPageId() == contactsPageId){
                Contact contact = new Contact(contactCinemaName,contactAddress,contactMapCoordinates);
                if (!contactLogoImage.isEmpty()){
                    contact.setLogoImage(saveImageAndGetName(contactLogoImage));
                }
                contact.setContactsPage(page);
                save(contact);
            }
        });

    }


}
