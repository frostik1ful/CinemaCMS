package com.cinema.cinema.database.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long hallId;
    private String name;
    private String description;
    private int countOfTickets;
    private String schemaImage;
    private String bannerImage;
    @ManyToOne
    @JoinColumn(name = "cinemaId")
    private Cinema cinema;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seo_seoId")
    private Seo seo;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "hall",fetch = FetchType.LAZY)
    private List<HallImage> images = new ArrayList<>();
    @OneToMany(mappedBy = "hall",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Session> sessions = new ArrayList<>();

    public Hall() {
    }

    public Hall(String name, String description, int countOfTickets) {
        this.name = name;
        this.description = description;
        this.countOfTickets = countOfTickets;
    }

    public Hall(String name, String description, int countOfTickets, String schemaImage, String bannerImage) {
        this.name = name;
        this.description = description;
        this.countOfTickets = countOfTickets;
        this.schemaImage = schemaImage;
        this.bannerImage = bannerImage;
    }

    @Override
    public String toString() {
        return "Hall{" +
                "hallId=" + hallId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", countOfTickets= '"+ countOfTickets + '\'' +
                //", seo=" + seo +
                //", images=" + images +
                '}';
    }
    public void addImage(HallImage image){
        images.add(image);
        image.setHall(this);
    }
    public long getHallId() {
        return hallId;
    }

    public void setHallId(long hallId) {
        this.hallId = hallId;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
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

    public int getCountOfTickets() {
        return countOfTickets;
    }

    public void setCountOfTickets(int countOfTickets) {
        this.countOfTickets = countOfTickets;
    }

    public String getSchemaImage() {
        return schemaImage;
    }

    public void setSchemaImage(String schemaImage) {
        this.schemaImage = schemaImage;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public Seo getSeo() {
        return seo;
    }

    public void setSeo(Seo seo) {
        this.seo = seo;
    }

    public List<HallImage> getImages() {
        return images;
    }

    public void setImages(List<HallImage> images) {
        this.images = images;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }
}
