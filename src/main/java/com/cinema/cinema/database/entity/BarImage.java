package com.cinema.cinema.database.entity;

import javax.persistence.*;

@Entity
public class BarImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long barImageId;
    private String image;
    @ManyToOne
    @JoinColumn(name = "barId")
    private Bar bar;

    public BarImage() {
    }

    public BarImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "BarImage{" +
                "barImageId=" + barImageId +
                ", image='" + image + '\'' +
                '}';
    }

    public long getBarImageId() {
        return barImageId;
    }

    public void setBarImageId(long barImageId) {
        this.barImageId = barImageId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Bar getBar() {
        return bar;
    }

    public void setBar(Bar bar) {
        this.bar = bar;
    }
}

