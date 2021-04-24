package com.cinema.cinema.database.dao.classes;

import com.cinema.cinema.database.dao.interfaces.PageImageDAO;
import com.cinema.cinema.database.entity.PageImage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PageImageDAOImpl extends MainDAOImpl<PageImage> implements PageImageDAO {
    public PageImageDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<PageImage> getClassType() {
        return PageImage.class;
    }

    @Override
    public void deleteByListOfId(List<Long> deletedImages) {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.createQuery("DELETE FROM PageImage img WHERE img.id IN :list")
                    .setParameter("list",deletedImages)
                    .executeUpdate();
            transaction.commit();
        }
    }
}
