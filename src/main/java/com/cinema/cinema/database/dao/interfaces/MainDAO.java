package com.cinema.cinema.database.dao.interfaces;

import java.util.List;
import java.util.Optional;

public interface MainDAO<T> {
    List<T> findAll();
    Optional<T> findById(long id);
    void delete(T object);
    void deleteById(long id);
    void update(T object);
    void save(T object);
}
