package com.cinema.cinema.database.dao.classes;

import com.cinema.cinema.database.dao.interfaces.HallImageDAO;
import com.cinema.cinema.database.entity.HallImage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class HallImageDAOImpl extends MainDAOImpl<HallImage> implements HallImageDAO {
    public HallImageDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<HallImage> getClassType() {
        return HallImage.class;
    }

    @Override
    public void deleteImageByName(String name) {
        try (Session session = sessionFactory.openSession()){
            Transaction ts = session.beginTransaction();
            session.createQuery("DELETE FROM HallImage img WHERE img.image = :name")
                    .setParameter("name",name)
                    .executeUpdate();
            ts.commit();
        }
    }

    @Override
    public void deleteByListOfId(List<Long> deletedImages) {
        try (Session session = sessionFactory.openSession()){
            Transaction ts = session.beginTransaction();
            session.createQuery("DELETE FROM HallImage  img WHERE img.id IN :list")
                    .setParameter("list",deletedImages)
                    .executeUpdate();
            ts.commit();
        }
    }

    @Override
    public List<HallImage> findAll() {
        return null;
    }
}
