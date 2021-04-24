package com.cinema.cinema.database.dao.classes;

import com.cinema.cinema.database.dao.interfaces.NewsImageDAO;
import com.cinema.cinema.database.entity.NewsImage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class NewsImageDAOImpl extends MainDAOImpl<NewsImage> implements NewsImageDAO {
    public NewsImageDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<NewsImage> getClassType() {
        return NewsImage.class;
    }

    @Override
    public void deleteByListOfId(List<Long> list) {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.createQuery("DELETE FROM NewsImage img WHERE img.id IN :list")
                    .setParameter("list",list)
                    .executeUpdate();
            transaction.commit();
        }
    }
}
