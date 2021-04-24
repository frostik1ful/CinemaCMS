package com.cinema.cinema.database.dao.classes;

import com.cinema.cinema.database.dao.interfaces.MovieDAO;
import com.cinema.cinema.database.entity.Advertisement;
import com.cinema.cinema.database.entity.Movie;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Repository
public class MovieDAOImpl extends MainDAOImpl<Movie> implements MovieDAO {

    @Autowired
    public MovieDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Movie> getClassType() {
        return Movie.class;
    }

    @Override
    public Optional<Movie> findWithImagesById(long id) {
        try (Session session = sessionFactory.openSession()){
            Optional<Movie> optionalMovie = Optional.ofNullable(session.get(getClassType(),id));
            optionalMovie.ifPresent(movie -> Hibernate.initialize(movie.getImages()));
            return optionalMovie;
        }

    }

    @Override
    public Optional<Movie> findWithTypesById(long id) {
        try (Session session = sessionFactory.openSession()){
            Optional<Movie> optionalMovie = Optional.ofNullable(session.get(getClassType(),id));
            optionalMovie.ifPresent(movie -> Hibernate.initialize(movie.getTypes()));
            return optionalMovie;
        }

    }

    @Override
    public Optional<Movie> findWithImagesAndTypesById(long id) {
        try (Session session = sessionFactory.openSession()){
            Optional<Movie> optionalMovie = Optional.ofNullable(session.get(getClassType(),id));
            optionalMovie.ifPresent(movie ->{
                Hibernate.initialize(movie.getImages());
                Hibernate.initialize(movie.getTypes());
            });
            return optionalMovie;
        }
    }

    @Override
    public List<Movie> findAllWithTypes() {
        try (Session session = sessionFactory.openSession()){
            List<Movie> movies = session
                    .createQuery("SELECT m FROM Movie m", getClassType())
                    .getResultList();
            movies.forEach(movie -> Hibernate.initialize(movie.getTypes()));
            return movies;
        }
    }

    @Override
    public List<Movie> findAll() {
        try (Session session = sessionFactory.openSession()){
            return session
                    .createQuery("SELECT m FROM Movie m", Movie.class)
                    .getResultList();
        }
    }
}
