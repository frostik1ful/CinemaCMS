package com.cinema.cinema.database.dao.classes;

import com.cinema.cinema.database.dao.interfaces.NewsDAO;
import com.cinema.cinema.database.entity.News;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class NewsDAOImpl extends MainDAOImpl<News> implements NewsDAO {
    public NewsDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<News> getClassType() {
        return News.class;
    }

    @Override
    public List<News> findAll() {
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("SELECT n FROM News n",getClassType()).getResultList();
        }

    }

    @Override
    public Optional<News> findWithImagesById(long id) {
        try(Session session = sessionFactory.openSession()){
            Optional<News> optionalNews = Optional.ofNullable(session.find(getClassType(),id));
            optionalNews.ifPresent(news -> Hibernate.initialize(news.getImages()));
            return optionalNews;
        }
    }
}
