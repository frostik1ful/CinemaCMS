package com.cinema.cinema.database.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Bar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long barId;
    private String name;
    private String description;
    private String mainImage;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seo_seoId")
    private Seo seo;
    @OneToMany(mappedBy = "bar",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<BarImage> images = new ArrayList<>();

    public Bar() {
    }

    public Bar(String name, String description, String mainImage) {
        this.name = name;
        this.description = description;
        this.mainImage = mainImage;
    }

    public Bar(String name, String description, String mainImage, Seo seo) {
        this.name = name;
        this.description = description;
        this.mainImage = mainImage;
        this.seo = seo;
    }
    public void addBarImage(BarImage image){
        images.add(image);
        image.setBar(this);
    }
    @Override
    public String toString() {
        return "Bar{" +
                "barId=" + barId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", mainImage='" + mainImage + '\'' +
                ", seo=" + seo +
                ", images=" + images +
                '}';
    }

    public long getBarId() {
        return barId;
    }

    public void setBarId(long barId) {
        this.barId = barId;
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

    public List<BarImage> getImages() {
        return images;
    }

    public void setImages(List<BarImage> images) {
        this.images = images;
    }
}
