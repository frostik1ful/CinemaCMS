package com.cinema.cinema.database.entity;

import javax.persistence.*;

@Entity
public class CinemaImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cinemaImageId;
    private String image;
    @ManyToOne
    @JoinColumn(name = "cinemaId")
    private Cinema cinema;

    public CinemaImage() {
    }

    public CinemaImage(String image) {
        this.image = image;
    }

    public CinemaImage(String image, Cinema cinema) {
        this.image = image;
        this.cinema = cinema;
    }

    @Override
    public String toString() {
        return "CinemaImage{" +
                "cinemaImageId=" + cinemaImageId +
                ", image='" + image + '\'' +
                '}';
    }

    public long getCinemaImageId() {
        return cinemaImageId;
    }

    public void setCinemaImageId(long cinemaImageId) {
        this.cinemaImageId = cinemaImageId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }
}
