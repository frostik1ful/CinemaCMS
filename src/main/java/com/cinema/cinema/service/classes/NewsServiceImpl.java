package com.cinema.cinema.service.classes;

import com.cinema.cinema.database.dao.interfaces.NewsDAO;
import com.cinema.cinema.database.dao.interfaces.NewsImageDAO;
import com.cinema.cinema.database.entity.News;
import com.cinema.cinema.database.entity.NewsImage;
import com.cinema.cinema.database.entity.Seo;
import com.cinema.cinema.service.interfaces.NewsService;
import com.cinema.cinema.util.DateTimeParser;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class NewsServiceImpl extends MainServiceImpl<News> implements NewsService {
    private final NewsDAO newsDAO;
    private final NewsImageDAO newsImageDAO;
    public NewsServiceImpl(NewsDAO newsDAO, NewsImageDAO newsImageDAO) {
        super(newsDAO);
        this.newsDAO = newsDAO;
        this.newsImageDAO = newsImageDAO;
    }

    @Override
    public List<News> findAll() {
        return newsDAO.findAll();
    }

    @Override
    public Optional<News> findWithImagesById(long id) {
        return newsDAO.findWithImagesById(id);
    }

    @Override
    public void createAndSaveNewNews(String newsName, String newsDescription, String newsVideoLink, boolean newsStatus, String newsPublishDate, MultipartFile newsMainImage,List<MultipartFile> newsImages, Seo seo) {
        Date publishDate = null;
        try {
            publishDate = DateTimeParser.convertStringToDate(newsPublishDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        News news =new News(newsName,Date.valueOf(LocalDate.now()),publishDate,newsDescription,newsVideoLink,newsStatus,seo);
        if (!newsMainImage.isEmpty()){
            news.setMainImage(saveImageAndGetName(newsMainImage));
        }
        if (newsImages != null){
            newsImages.forEach(image -> news.addNewsImage(new NewsImage(saveImageAndGetName(image))));
        }
        save(news);

    }

    @Override
    public void updateNews(long newsId, String newsName, String newsDescription, String newsVideoLink, boolean newsStatus,
                           String newsPublishDate, MultipartFile newsMainImage, List<MultipartFile> newsImages, List<Long> deletedImages, Seo seo) {
        findWithImagesById(newsId).ifPresent(news -> {
           news.setName(newsName);
           news.setDescription(newsDescription);
           news.setVideoLink(newsVideoLink);
           news.setActive(newsStatus);
            try {
                news.setPublishDate(DateTimeParser.convertStringToDate(newsPublishDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            seo.setSeoId(news.getSeo().getSeoId());
            news.setSeo(seo);
           if (!newsMainImage.isEmpty()){
               news.setMainImage(saveImageAndGetName(newsMainImage));
           }
           if (newsImages != null){
               newsImages.forEach(image -> news.addNewsImage(new NewsImage(saveImageAndGetName(image))));
           }
           update(news);
           if (deletedImages != null){
               newsImageDAO.deleteByListOfId(deletedImages);
           }
        });
    }
}
