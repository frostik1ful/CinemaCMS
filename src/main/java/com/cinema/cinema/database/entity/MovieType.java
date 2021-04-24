package com.cinema.cinema.database.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class MovieType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long movieTypeId;
    private String name;
    @Transient
    private boolean isSelected;

    public MovieType() {
    }

    public MovieType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MovieType{" +
                "movieTypeId=" + movieTypeId +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieType)) return false;
        MovieType type = (MovieType) o;
        return movieTypeId == type.movieTypeId &&
                Objects.equals(name, type.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieTypeId, name);
    }

    public long getMovieTypeId() {
        return movieTypeId;
    }

    public void setMovieTypeId(long movieTypeId) {
        this.movieTypeId = movieTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
