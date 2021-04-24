package com.cinema.cinema.database.entity;

import javax.persistence.*;

@Entity
public class AdvertisementImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long advertisementImageId;
    private String image;
    @ManyToOne
    @JoinColumn(name = "advertisementId")
    private Advertisement advertisement;

    public AdvertisementImage() {
    }

    public AdvertisementImage(String image) {
        this.image = image;
    }

    public AdvertisementImage(String image, Advertisement advertisement) {
        this.image = image;
        this.advertisement = advertisement;
    }

    @Override
    public String toString() {
        return "AdvertisementImage{" +
                "advertisementImageId=" + advertisementImageId +
                ", image='" + image + '\'' +
                ", advertisement=" + advertisement +
                '}';
    }

    public long getAdvertisementImageId() {
        return advertisementImageId;
    }

    public void setAdvertisementImageId(long advertisementImageId) {
        this.advertisementImageId = advertisementImageId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }
}
