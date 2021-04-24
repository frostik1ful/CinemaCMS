package com.cinema.cinema.database.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ChildRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long childRoomId;
    private String name;
    private String description;
    private String mainImage;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seo_seoId")
    private Seo seo;

    @OneToMany(mappedBy = "childRoom",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<ChildRoomImage> images = new ArrayList<>();

    public ChildRoom() {
    }

    public ChildRoom(String name, String description, String mainImage) {
        this.name = name;
        this.description = description;
        this.mainImage = mainImage;
    }



    public ChildRoom(String name, String description, String mainImage, Seo seo) {
        this.name = name;
        this.description = description;
        this.mainImage = mainImage;
        this.seo = seo;
    }
    public void addChildRoomImage(ChildRoomImage image){
        images.add(image);
        image.setChildRoom(this);
    }
    @Override
    public String toString() {
        return "ChildRoom{" +
                "childRoomId=" + childRoomId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", mainImage='" + mainImage + '\'' +
                ", seo=" + seo +
                ", images=" + images +
                '}';
    }

    public long getChildRoomId() {
        return childRoomId;
    }

    public void setChildRoomId(long childRoomId) {
        this.childRoomId = childRoomId;
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

    public List<ChildRoomImage> getImages() {
        return images;
    }

    public void setImages(List<ChildRoomImage> images) {
        this.images = images;
    }
}
