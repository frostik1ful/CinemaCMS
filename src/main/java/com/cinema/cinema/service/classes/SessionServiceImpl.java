package com.cinema.cinema.service.classes;

import com.cinema.cinema.database.dao.interfaces.HallDAO;
import com.cinema.cinema.database.dao.interfaces.MovieDAO;
import com.cinema.cinema.database.dao.interfaces.SessionDAO;
import com.cinema.cinema.database.dao.interfaces.TicketDAO;
import com.cinema.cinema.database.entity.Session;
import com.cinema.cinema.database.entity.Ticket;
import com.cinema.cinema.database.entity.User;
import com.cinema.cinema.service.interfaces.SessionService;
import com.cinema.cinema.util.DateTimeParser;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SessionServiceImpl extends MainServiceImpl<Session> implements SessionService {
    private final SessionDAO dao;
    private final HallDAO hallDAO;
    private final MovieDAO movieDAO;
    private final TicketDAO ticketDAO;

    public SessionServiceImpl(SessionDAO dao, HallDAO hallDAO, MovieDAO movieDAO, TicketDAO ticketDAO) {
        super(dao);
        this.dao = dao;
        this.hallDAO = hallDAO;
        this.movieDAO = movieDAO;
        this.ticketDAO = ticketDAO;
    }

    @Override
    public Optional<Session> findWithMovieById(long id) {
        return dao.findWithMovieById(id);
    }

    @Override
    public List<Session> findAll() {
        return dao.findAll();
    }

    @Override
    public void createAndSaveNewSession(long hallId, long movieId, String sessionDate) {
        hallDAO.findById(hallId).ifPresent(hall ->
                movieDAO.findById(movieId).ifPresent(movie -> {
                    LocalDateTime dateTime = null;
                    try {
                        dateTime = DateTimeParser.convertDateTimeStringToLocalDateTime(sessionDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Session session = new Session(dateTime, hall, movie);
                    for (int i = 0; i < hall.getCountOfTickets(); i++) {
                        session.addTicket(new Ticket(100));
                    }
                    dao.save(session);
                }));

    }

    @Override
    public void updateSession(long sessionId, long movieId, String sessionDate) {
        findWithMovieById(sessionId).ifPresent(session -> {
            movieDAO.findById(movieId).ifPresent(session::setMovie);
            try {
                session.setDate(DateTimeParser.convertDateTimeStringToLocalDateTime(sessionDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            update(session);
        });
    }

    @Override
    public List<Session> findAllWithHallAndCinemaByMovieId(long movieId) {
        return dao.findAllWithHallAndCinemaByMovieId(movieId);
    }

    @Override
    public Optional<Session> findWithTicketsById(long id) {
        return dao.findWithTicketsById(id);
    }

    @Override
    public void buyTicket(long ticketId, User user) {
        ticketDAO.findById(ticketId).ifPresent(ticket ->{
            if (!ticket.isPurchased()){
                ticket.setBuyer(user);
                ticket.setPurchased(true);
                ticketDAO.update(ticket);
            }

        });
    }
}
