package com.cinema.cinema.database.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long advertisementId;
    private String name;
    private String description;
    private String mainImage;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seo_seoID")
    private Seo seo;
    @OneToMany(mappedBy = "advertisement",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<AdvertisementImage> images = new ArrayList<>();

    public Advertisement() {
    }

    public Advertisement(String name, String description, String mainImage, Seo seo) {
        this.name = name;
        this.description = description;
        this.mainImage = mainImage;
        this.seo = seo;
    }
    public void addAdvertisementImage(AdvertisementImage image){
        images.add(image);
        image.setAdvertisement(this);
    }
    @Override
    public String toString() {
        return "Advertisement{" +
                "advertisementId=" + advertisementId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", mainImage='" + mainImage + '\'' +
                ", seo=" + seo +
                ", images=" + images +
                '}';
    }

    public long getAdvertisementId() {
        return advertisementId;
    }

    public void setAdvertisementId(long advertisementId) {
        this.advertisementId = advertisementId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public Seo getSeo() {
        return seo;
    }

    public void setSeo(Seo seo) {
        this.seo = seo;
    }

    public List<AdvertisementImage> getImages() {
        return images;
    }

    public void setImages(List<AdvertisementImage> images) {
        this.images = images;
    }
}
