package com.cinema.cinema.database.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long newsId;
    private String name;
    private Date creationDate;
    private Date publishDate;
    private String description;
    private String mainImage;
    private String videoLink;
    private boolean active;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seo_seoId")
    private Seo seo;
    @OneToMany (mappedBy = "news", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<NewsImage> images = new ArrayList<>();

    public News() {
    }

    public News(String name,Date creationDate, Date publishDate, String description,String videoLink,boolean active, Seo seo) {
        this.name = name;
        this.creationDate = creationDate;
        this.publishDate = publishDate;
        this.description = description;
        this.videoLink = videoLink;
        this.active = active;
        this.seo = seo;
    }

    @Override
    public String toString() {
        return "News{" +
                "newsId=" + newsId +
                ", name='" + name + '\'' +
                ", publishDate=" + publishDate +
                ", description='" + description + '\'' +
                ", mainImage='" + mainImage + '\'' +
                ", videoLink='" + videoLink + '\'' +
                ", seo=" + seo +
                ", images=" + images +
                '}';
    }

    public long getNewsId() {
        return newsId;
    }

    public void setNewsId(long newsId) {
        this.newsId = newsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
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

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Seo getSeo() {
        return seo;
    }

    public void setSeo(Seo seo) {
        this.seo = seo;
    }

    public List<NewsImage> getImages() {
        return images;
    }

    public void setImages(List<NewsImage> images) {
        this.images = images;
    }

    public void addNewsImage(NewsImage image) {
        images.add(image);
        image.setNews(this);
    }
}
