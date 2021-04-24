package com.cinema.cinema.database.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long movieId;
    private String name;
    private String description;
    private String mainImage;
    private String trailerLink;
    private Date dateOfRelease;
    private Date dateOfFinish;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seo_seoId")
    private Seo seo;

    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<MovieImage> images = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "MovieTypeMovie",
                joinColumns = {@JoinColumn(name = "movieId")},
                inverseJoinColumns = {@JoinColumn(name = "movieTypeId")})
    private List<MovieType> types = new ArrayList<>();

    public Movie() {
    }

    public Movie(String name, String description, String mainImage, String trailerLink) {
        this.name = name;
        this.description = description;
        this.mainImage = mainImage;
        this.trailerLink = trailerLink;
    }
    public void addMovieImage(MovieImage image){
        images.add(image);
        image.setMovie(this);
    }
    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", mainImage='" + mainImage + '\'' +
                ", trailerLink='" + trailerLink + '\'' +
                ", seo=" + seo +
                ", images=" + images +
                ", types=" + types +
                '}';
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
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
        return  mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public String getTrailerLink() {
        return trailerLink;
    }

    public void setTrailerLink(String trailerLink) {
        this.trailerLink = trailerLink;
    }

    public Date getDateOfRelease() {
        return dateOfRelease;
    }

    public void setDateOfRelease(Date dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }

    public Date getDateOfFinish() {
        return dateOfFinish;
    }

    public void setDateOfFinish(Date dateOfFinish) {
        this.dateOfFinish = dateOfFinish;
    }

    public Seo getSeo() {
        return seo;
    }

    public void setSeo(Seo seo) {
        this.seo = seo;
    }

    public List<MovieImage> getImages() {
        return images;
    }

    public void setImages(List<MovieImage> images) {
        this.images = images;
    }

    public List<MovieType> getTypes() {
        return types;
    }

    public void setTypes(List<MovieType> types) {
        this.types = types;
    }
}
