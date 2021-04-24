package com.cinema.cinema.database.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ContactsPage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long contactsPageId;
    private String name;
    private Date creationDate;
    private boolean active;
    @OneToMany(mappedBy = "contactsPage", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Contact> contacts = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seo_seoId")
    private Seo seo;

    public ContactsPage() {
    }

    public ContactsPage(String name, Date creationDate, boolean active, Seo seo) {
        this.name = name;
        this.creationDate = creationDate;
        this.active = active;
        this.seo = seo;
    }

    public ContactsPage(List<Contact> contacts, Seo seo) {
        this.contacts = contacts;
        this.seo = seo;
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
        contact.setContactsPage(this);
    }

    public long getContactsPageId() {
        return contactsPageId;
    }

    public void setContactsPageId(long contactsPageId) {
        this.contactsPageId = contactsPageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public Seo getSeo() {
        return seo;
    }

    public void setSeo(Seo seo) {
        this.seo = seo;
    }
}
