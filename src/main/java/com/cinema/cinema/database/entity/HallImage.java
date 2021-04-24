package com.cinema.cinema.database.entity;

import javax.persistence.*;

@Entity
public class HallImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long hallImageId;
    private String image;
    @ManyToOne
    @JoinColumn(name = "hallId")
    private Hall hall;

    public HallImage() {
    }

    public HallImage(String image) {
        this.image = image;
    }

    public HallImage(String image, Hall hall) {
        this.image = image;
        this.hall = hall;
    }

    @Override
    public String toString() {
        return "HallImage{" +
                "hallImageId=" + hallImageId +
                ", image='" + image + '\'' +
                '}';
    }

    public long getHallImageId() {
        return hallImageId;
    }

    public void setHallImageId(long hallImageId) {
        this.hallImageId = hallImageId;
    }

    public String getImage() {
        return  image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }
}
