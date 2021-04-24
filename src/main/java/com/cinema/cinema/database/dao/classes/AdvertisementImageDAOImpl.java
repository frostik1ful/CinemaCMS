package com.cinema.cinema.database.dao.classes;

import com.cinema.cinema.database.dao.interfaces.AdvertisementImageDAO;
import com.cinema.cinema.database.entity.AdvertisementImage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class AdvertisementImageDAOImpl extends MainDAOImpl<AdvertisementImage> implements AdvertisementImageDAO {
    public AdvertisementImageDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<AdvertisementImage> getClassType() {
        return AdvertisementImage.class;
    }

    @Override
    public List<AdvertisementImage> findAll() {
        try (Session session = sessionFactory.openSession()){
            return session.createQuery("SELECT img FROM AdvertisementImage img",getClassType())
                    .getResultList();
        }
    }

    @Override
    public void deleteByListOfId(List<Long> list) {
        try (Session session = sessionFactory.openSession()){
            Transaction tr = session.beginTransaction();
            session.createQuery("DELETE FROM AdvertisementImage img WHERE img.id IN :list")
                    .setParameter("list",list)
                    .executeUpdate();
            tr.commit();
        }
    }
}
