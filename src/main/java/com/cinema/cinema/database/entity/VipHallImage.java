package com.cinema.cinema.database.entity;

import javax.persistence.*;

@Entity
public class VipHallImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long vipHallImageId;
    private String image;
    @ManyToOne
    @JoinColumn(name = "vipHallPageId")
    private VipHallPage vipHallPage;

    public VipHallImage() {
    }

    public VipHallImage(String image) {
        this.image = image;
    }

    public VipHallImage(String image, VipHallPage vipHallPage) {
        this.image = image;
        this.vipHallPage = vipHallPage;
    }

    @Override
    public String toString() {
        return "VipHallImage{" +
                "vipHallImageId=" + vipHallImageId +
                ", image='" + image + '\'' +
                '}';
    }

    public long getVipHallImageId() {
        return vipHallImageId;
    }

    public void setVipHallImageId(long vipHallImageId) {
        this.vipHallImageId = vipHallImageId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public VipHallPage getVipHallPage() {
        return vipHallPage;
    }

    public void setVipHallPage(VipHallPage vipHallPage) {
        this.vipHallPage = vipHallPage;
    }
}
