package com.cinema.cinema.database.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class VipHallPage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long vipHallPageId;
    private String description;
    private String mainImage;
    private String schemaImage;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seo_seoId")
    private Seo seo;
    @OneToMany(mappedBy = "vipHallPage",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<VipHallImage> images = new ArrayList<>();

    public VipHallPage() {
    }

    public VipHallPage(String description, String mainImage, String schemaImage) {
        this.description = description;
        this.mainImage = mainImage;
        this.schemaImage = schemaImage;
    }

    public VipHallPage(String description, String mainImage, String schemaImage, Seo seo) {
        this.description = description;
        this.mainImage = mainImage;
        this.schemaImage = schemaImage;
        this.seo = seo;
    }

    @Override
    public String toString() {
        return "VipHallPage{" +
                "vipHallPageId=" + vipHallPageId +
                ", description='" + description + '\'' +
                ", mainImage='" + mainImage + '\'' +
                ", schemaImage='" + schemaImage + '\'' +
                ", seo=" + seo + '\'' +
                ", images=" + images + '\'' +
                '}';
    }

    public long getVipHallPageId() {
        return vipHallPageId;
    }

    public void setVipHallPageId(long vipHallPageId) {
        this.vipHallPageId = vipHallPageId;
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

    public String getSchemaImage() {
        return schemaImage;
    }

    public void setSchemaImage(String schemaImage) {
        this.schemaImage = schemaImage;
    }

    public Seo getSeo() {
        return seo;
    }

    public void setSeo(Seo seo) {
        this.seo = seo;
    }

    public List<VipHallImage> getImages() {
        return images;
    }

    public void setImages(List<VipHallImage> images) {
        this.images = images;
    }

    public void addVipHallImage(VipHallImage image) {
        images.add(image);
        image.setVipHallPage(this);
    }
}
