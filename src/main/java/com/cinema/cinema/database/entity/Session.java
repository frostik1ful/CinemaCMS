package com.cinema.cinema.database.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long sessionId;
    private LocalDateTime date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hallId")
    private Hall hall;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movieId")
    private Movie movie;
    @OneToMany(mappedBy = "session",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Ticket> tickets = new ArrayList<>();

    public Session() {
    }

    public Session(LocalDateTime date, Hall hall, Movie movie) {
        this.date = date;
        this.hall = hall;
        this.movie = movie;

    }

    @Override
    public String toString() {
        return "Session{" +
                "sessionId=" + sessionId +
                ", date=" + date +
                '}';
    }

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
    public void addTicket(Ticket ticket){
        tickets.add(ticket);
        ticket.setSession(this);
    }
    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
