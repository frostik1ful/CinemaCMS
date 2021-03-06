package com.cinema.cinema.service.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface MainService<T> {
    Optional<T> findById(long id);
    void delete(T object);
    void deleteById(long id);
    void update(T object);
    void save(T object);
    String saveImageAndGetName(MultipartFile file);
}
