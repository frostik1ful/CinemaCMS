package com.cinema.cinema;

import com.cinema.cinema.database.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {
    private static com.cinema.cinema.HibernateSessionFactory hibernateSessionFactory;
    private final SessionFactory sessionFactory;
    private HibernateSessionFactory(){
        Configuration con = new Configuration().configure()
                .addAnnotatedClass(Seo.class)
                .addAnnotatedClass(Contact.class)
                .addAnnotatedClass(ContactsPage.class)
                .addAnnotatedClass(News.class)
                .addAnnotatedClass(NewsImage.class)
                .addAnnotatedClass(Promotion.class)
                .addAnnotatedClass(PromotionImage.class)
                .addAnnotatedClass(Advertisement.class)
                .addAnnotatedClass(AdvertisementImage.class)
                .addAnnotatedClass(Bar.class)
                .addAnnotatedClass(BarImage.class)
                .addAnnotatedClass(Cinema.class)
                .addAnnotatedClass(CinemaImage.class)
                .addAnnotatedClass(ChildRoom.class)
                .addAnnotatedClass(ChildRoomImage.class)
                .addAnnotatedClass(Hall.class)
                .addAnnotatedClass(HallImage.class)
                .addAnnotatedClass(VipHallPage.class)
                .addAnnotatedClass(VipHallImage.class)
                .addAnnotatedClass(Movie.class)
                .addAnnotatedClass(MovieType.class)
                .addAnnotatedClass(MovieImage.class)
                .addAnnotatedClass(Session.class)
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Ticket.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Language.class);
        sessionFactory = con.buildSessionFactory();

    }
    public static synchronized com.cinema.cinema.HibernateSessionFactory getInstance(){
        if (hibernateSessionFactory == null){
            hibernateSessionFactory = new com.cinema.cinema.HibernateSessionFactory();
        }
        return hibernateSessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
