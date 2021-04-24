package com.cinema.cinema.database.dao.interfaces;

import com.cinema.cinema.database.entity.User;

import java.util.Optional;

public interface UserDAO extends MainDAO<User>{
    Optional<User> findUserByNickName(String name);
}
