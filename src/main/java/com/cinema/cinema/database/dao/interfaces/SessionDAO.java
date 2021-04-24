package com.cinema.cinema.database.dao.interfaces;

import com.cinema.cinema.database.entity.Session;

import java.util.List;
import java.util.Optional;

public interface SessionDAO extends MainDAO<Session> {
    Optional<Session> findWithMovieById(long id);

    List<Session> findAllWithHallAndCinemaByMovieId(long movieId);

    Optional<Session> findWithTicketsById(long id);
}
