package com.cinema.cinema.database.dao.classes;

import com.cinema.cinema.database.dao.interfaces.TicketDAO;
import com.cinema.cinema.database.entity.Ticket;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDAOImpl extends MainDAOImpl<Ticket> implements TicketDAO {
    public TicketDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Ticket> getClassType() {
        return Ticket.class;
    }
}
