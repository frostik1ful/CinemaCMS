package com.cinema.cinema.service.interfaces;

import com.cinema.cinema.database.entity.News;
import com.cinema.cinema.database.entity.Seo;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface NewsService extends MainService<News>{
    List<News> findAll();
    Optional<News> findWithImagesById(long id);
    void createAndSaveNewNews(String newsName, String newsDescription, String newsVideoLink, boolean newsStatus, String newsPublishDate, MultipartFile newsMainImage,List<MultipartFile> newsImages, Seo seo);

    void updateNews(long newsId, String newsName, String newsDescription, String newsVideoLink, boolean newsStatus, String newsPublishDate, MultipartFile newsMainImage, List<MultipartFile> newsImages, List<Long> deletedImages, Seo seo);
}
