package com.cinema.cinema.service.interfaces;

import com.cinema.cinema.database.entity.Session;
import com.cinema.cinema.database.entity.User;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SessionService extends MainService<Session>{
    Optional<Session> findWithMovieById(long id);
    List<Session> findAll();

    void createAndSaveNewSession(long hallId,long movieId, String sessionDate);

    void updateSession(long sessionId,long movieId, String sessionDate);

    List<Session> findAllWithHallAndCinemaByMovieId(long movieId);

    Optional<Session> findWithTicketsById(long id);

    void buyTicket(long ticketId, User user);
}
