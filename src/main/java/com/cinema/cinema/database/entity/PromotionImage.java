package com.cinema.cinema.database.entity;

import javax.persistence.*;

@Entity
public class PromotionImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long promotionImageId;
    private String image;
    @ManyToOne
    @JoinColumn(name = "promotionId")
    private Promotion promotion;

    public PromotionImage() {
    }

    public PromotionImage(String image) {
        this.image = image;
    }

    public long getPromotionImageId() {
        return promotionImageId;
    }

    public void setPromotionImageId(long promotionImageId) {
        this.promotionImageId = promotionImageId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }
}
