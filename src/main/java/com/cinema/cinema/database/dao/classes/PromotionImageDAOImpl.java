package com.cinema.cinema.database.dao.classes;

import com.cinema.cinema.database.dao.interfaces.PromotionImageDAO;
import com.cinema.cinema.database.entity.Promotion;
import com.cinema.cinema.database.entity.PromotionImage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class PromotionImageDAOImpl extends MainDAOImpl<PromotionImage> implements PromotionImageDAO {
    public PromotionImageDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<PromotionImage> getClassType() {
        return PromotionImage.class;
    }

    @Override
    public void deleteByListOdId(List<Long> list) {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.createQuery("DELETE FROM PromotionImage img WHERE img.id IN :list")
                    .setParameter("list",list)
                    .executeUpdate();
            transaction.commit();
        }
    }
}
