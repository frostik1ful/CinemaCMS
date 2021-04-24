package com.cinema.cinema.database.dao.classes;

import com.cinema.cinema.database.dao.interfaces.PromotionDAO;
import com.cinema.cinema.database.entity.Promotion;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class PromotionDAOImpl extends MainDAOImpl<Promotion> implements PromotionDAO {
    public PromotionDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Promotion> getClassType() {
        return Promotion.class;
    }

    @Override
    public List<Promotion> findAll() {
        try (Session session = sessionFactory.openSession()){
            return session.createQuery("SELECT p FROM Promotion p",getClassType())
                    .getResultList();
        }
    }
}
