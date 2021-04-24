package com.cinema.cinema.database.entity;

import javax.persistence.*;

@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long contactId;
    private String cinemaName;
    private String address;
    private String mapCoordinates;
    private String logoImage;
    @ManyToOne
    @JoinColumn(name = "contactsPageId")
    private ContactsPage contactsPage;

    public Contact(){}

    public Contact(String cinemaName, String address, String mapCoordinates) {
        this.cinemaName = cinemaName;
        this.address = address;
        this.mapCoordinates = mapCoordinates;
    }

    public Contact(String cinemaName, String address, String mapCoordinates, String logoImage) {
        this.cinemaName = cinemaName;
        this.address = address;
        this.mapCoordinates = mapCoordinates;
        this.logoImage = logoImage;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contactId=" + contactId +
                ", cinemaName='" + cinemaName + '\'' +
                ", address='" + address + '\'' +
                ", mapCoordinates='" + mapCoordinates + '\'' +
                ", logoImage='" + logoImage + '\'' +
                ", contactsPage=" + contactsPage +
                '}';
    }

    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactID) {
        this.contactId = contactID;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMapCoordinates() {
        return mapCoordinates;
    }

    public void setMapCoordinates(String mapCoordinates) {
        this.mapCoordinates = mapCoordinates;
    }

    public String getLogoImage() {
        return logoImage;
    }

    public void setLogoImage(String logoImage) {
        this.logoImage = logoImage;
    }

    public ContactsPage getContactsPage() {
        return contactsPage;
    }

    public void setContactsPage(ContactsPage contactsPage) {
        this.contactsPage = contactsPage;
    }
}
