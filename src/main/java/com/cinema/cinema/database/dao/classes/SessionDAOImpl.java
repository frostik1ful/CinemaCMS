package com.cinema.cinema.database.dao.classes;

import com.cinema.cinema.database.dao.interfaces.SessionDAO;
import com.cinema.cinema.database.entity.Session;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class SessionDAOImpl extends MainDAOImpl<Session> implements SessionDAO {
    public SessionDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Session> getClassType() {
        return Session.class;
    }

    @Override
    public Optional<Session> findWithMovieById(long id) {
        try (org.hibernate.Session session = sessionFactory.openSession()){
            Optional<Session> optionalSession = Optional.ofNullable(session.get(getClassType(),id));
            optionalSession.ifPresent(session1 -> Hibernate.initialize(session1.getMovie()));
            return optionalSession;
        }
    }

    @Override
    public List<Session> findAllWithHallAndCinemaByMovieId(long movieId) {
        try (org.hibernate.Session session = sessionFactory.openSession()){
            List<Session> list =  session.createQuery("SELECT s FROM Session s WHERE s.movie.id =:movieId",Session.class)
                    .setParameter("movieId",movieId).getResultList();
            list.forEach(s -> Hibernate.initialize(s.getHall().getCinema()));
            return list;
        }
    }

    @Override
    public Optional<Session> findWithTicketsById(long id) {
        try (org.hibernate.Session session = sessionFactory.openSession()){
            Optional<Session> optionalSession = Optional.ofNullable(session.get(getClassType(),id));
            optionalSession.ifPresent(cinemaSession -> Hibernate.initialize(cinemaSession.getTickets()));
            return optionalSession;
        }

    }

    @Override
    public List<Session> findAll() {
        try (org.hibernate.Session session = sessionFactory.openSession()){
            List<Session> list =  session.createQuery("SELECT s FROM Session s",Session.class).getResultList();
            list.forEach(s -> Hibernate.initialize(s.getMovie()));
            return list;
        }
    }
}
