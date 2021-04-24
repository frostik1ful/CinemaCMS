package com.cinema.cinema.database.dao.classes;

import com.cinema.cinema.database.dao.interfaces.UserDAO;
import com.cinema.cinema.database.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDAOImpl extends MainDAOImpl<User> implements UserDAO {

    public UserDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<User> getClassType() {
        return User.class;
    }

    @Override
    public Optional<User> findUserByNickName(String name) {
        try (Session session = sessionFactory.openSession()){
            User user = session
                    .createQuery("SELECT u FROM User u WHERE u.nickName = :name",User.class)
                    .setParameter("name",name)
                    .uniqueResult();
            return Optional.ofNullable(user);
        }

    }

    @Override
    public List<User> findAll() {
        try (Session session = sessionFactory.openSession()){
            return session.createQuery("SELECT u FROM User u",User.class).getResultList();
        }
    }
}
