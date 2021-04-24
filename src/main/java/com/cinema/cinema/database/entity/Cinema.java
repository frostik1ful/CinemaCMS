package com.cinema.cinema.database.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cinemaId;
    private String name;
    private String description;
    private String rules;
    private String mainImage;
    private String logoImage;
    private String upperBannerImage;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seo_seoId")
    private Seo seo;

    @OneToMany(mappedBy = "cinema",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<CinemaImage> images = new ArrayList<>();

    @OneToMany(mappedBy = "cinema",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Hall> halls = new ArrayList<>();

    public Cinema() {
    }

    public Cinema(String name, String description, String rules, String mainImage, String logoImage, String upperBannerImage) {
        this.name = name;
        this.description = description;
        this.rules = rules;
        this.mainImage = mainImage;
        this.logoImage = logoImage;
        this.upperBannerImage = upperBannerImage;
    }

    public Cinema(String name, String description, String rules, String mainImage, String logoImage, String upperBannerImage, Seo seo) {
        this.name = name;
        this.description = description;
        this.rules = rules;
        this.mainImage = mainImage;
        this.logoImage = logoImage;
        this.upperBannerImage = upperBannerImage;
        this.seo = seo;
    }
    public void addCinemaImage(CinemaImage image){
        images.add(image);
        image.setCinema(this);
    }
    public void addHall(Hall hall){
        halls.add(hall);
        hall.setCinema(this);
    }
    @Override
    public String toString() {
        return "Cinema{" +
                "cinemaId=" + cinemaId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", rules='" + rules + '\'' +
                ", mainImage='" + mainImage + '\'' +
                ", logoImage='" + logoImage + '\'' +
                ", upperBannerImage='" + upperBannerImage + '\'' +
                ", seo=" + seo +
                ", images=" + images +
                ", halls=" + halls +
                '}';
    }

    public long getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(long cinemaId) {
        this.cinemaId = cinemaId;
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

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public String getLogoImage() {
        return logoImage;
    }

    public void setLogoImage(String logoImage) {
        this.logoImage = logoImage;
    }

    public String getUpperBannerImage() {
        return upperBannerImage;
    }

    public void setUpperBannerImage(String upperBannerImage) {
        this.upperBannerImage = upperBannerImage;
    }

    public Seo getSeo() {
        return seo;
    }

    public void setSeo(Seo seo) {
        this.seo = seo;
    }

    public List<CinemaImage> getImages() {
        return images;
    }

    public void setImages(List<CinemaImage> images) {
        this.images = images;
    }

    public List<Hall> getHalls() {
        return halls;
    }

    public void setHalls(List<Hall> halls) {
        this.halls = halls;
    }
}
