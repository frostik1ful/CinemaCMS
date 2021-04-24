package com.cinema.cinema.database.entity;

import javax.persistence.*;

@Entity
public class MovieImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long movieImageId;
    private String image;
    @ManyToOne
    @JoinColumn(name = "movieId")
    private Movie movie;

    public MovieImage() {
    }

    public MovieImage(String image) {
        this.image = image;
    }

    public MovieImage(String image, Movie movie) {
        this.image = image;
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "MovieImage{" +
                "movieImageId=" + movieImageId +
                ", image='" + image + '\'' +
                '}';
    }

    public long getMovieImageId() {
        return movieImageId;
    }

    public void setMovieImageId(long movieImageId) {
        this.movieImageId = movieImageId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
