package com.cinema.cinema.database.dao.classes;

import com.cinema.cinema.database.dao.interfaces.CinemaImageDAO;
import com.cinema.cinema.database.entity.CinemaImage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CinemaImageDAOImpl extends MainDAOImpl<CinemaImage> implements CinemaImageDAO {
    public CinemaImageDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<CinemaImage> getClassType() {
        return CinemaImage.class;
    }

    @Override
    public List<CinemaImage> findAll() {
        return null;
    }

    @Override
    public void deleteByImageName(String name) {
        try (Session session = sessionFactory.openSession()){
            Transaction ts = session.beginTransaction();
            session.createQuery("DELETE FROM CinemaImage img WHERE img.image = :name")
                    .setParameter("name",name)
                    .executeUpdate();
            ts.commit();
        }
    }

    @Override
    public void deleteByListOfId(List<Long> deletedImages) {
        try (Session session = sessionFactory.openSession()){
            Transaction ts = session.beginTransaction();
            session.createQuery("DELETE FROM CinemaImage  img WHERE img.id IN :list")
                    .setParameter("list",deletedImages)
                    .executeUpdate();
            ts.commit();
        }
    }
}
