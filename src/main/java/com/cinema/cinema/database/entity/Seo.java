package com.cinema.cinema.database.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Seo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seoId;
    private String url;
    private String title;
    private String keywords;
    private String description;

    public Seo(){}
    public Seo( String url, String title, String keywords, String description) {
        this.url = url;
        this.title = title;
        this.keywords = keywords;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Seo{" +
                "seoID=" + seoId +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", keywords='" + keywords + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Seo)) return false;
        Seo seo = (Seo) o;
        return Objects.equals(url, seo.url) &&
                Objects.equals(title, seo.title) &&
                Objects.equals(keywords, seo.keywords) &&
                Objects.equals(description, seo.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, title, keywords, description);
    }

    public long getSeoId() {
        return seoId;
    }

    public void setSeoId(long seoId) {
        this.seoId = seoId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
