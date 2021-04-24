package com.cinema.cinema.service.classes;

import com.cinema.cinema.database.dao.interfaces.MainDAO;
import com.cinema.cinema.service.interfaces.MainService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

public abstract class MainServiceImpl<T> implements MainService<T> {
    @Value("${imagesPathToSave}")
    private String imagesPath;
    private final MainDAO<T> dao;

    public MainServiceImpl(MainDAO<T> dao) {
        this.dao = dao;
    }

    @Override
    public Optional<T> findById(long id) {
        return dao.findById(id);
    }

    @Override
    public void delete(T object) {
        dao.delete(object);
    }

    @Override
    public void deleteById(long id) {
        dao.deleteById(id);
    }

    @Override
    public void update(T object) {
        dao.update(object);
    }

    @Override
    public void save(T object) {
        dao.save(object);
    }

    public String saveImageAndGetName(MultipartFile file) {
        if (!file.isEmpty()){
            String filename = getRandomUUID() + file.getOriginalFilename();
            saveFile(filename,file);
            return filename;
        }
        return null;
    }

    boolean saveFile(String fileName, MultipartFile file) {
        try {
            file.transferTo(new File(imagesPath + fileName));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    String getRandomUUID(){
        return UUID.randomUUID().toString();
    }
}
