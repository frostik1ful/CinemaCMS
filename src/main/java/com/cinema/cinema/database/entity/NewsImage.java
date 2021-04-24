package com.cinema.cinema.database.entity;

import javax.persistence.*;

@Entity
public class NewsImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long newsImageId;
    private String image;
    @ManyToOne
    @JoinColumn(name = "newsId")
    private News news;

    public NewsImage() {
    }

    public NewsImage(String image) {
        this.image = image;
    }

    public NewsImage(String image, News news) {
        this.image = image;
        this.news = news;
    }

    @Override
    public String toString() {
        return "NewsImage{" +
                "newsImageId=" + newsImageId +
                ", image='" + image + '\'' +
                '}';
    }

    public long getNewsImageId() {
        return newsImageId;
    }

    public void setNewsImageId(long newsImageId) {
        this.newsImageId = newsImageId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }
}
