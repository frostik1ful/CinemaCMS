package com.cinema.cinema.service.interfaces;

import com.cinema.cinema.database.entity.ContactsPage;
import com.cinema.cinema.database.entity.MainPage;
import com.cinema.cinema.database.entity.Page;
import com.cinema.cinema.database.entity.Seo;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface PageService extends MainService<Page>{
    List<Page> findAllSimplePages();

    void updatePage(long pageId, String pageName, String pageDescription, boolean pageStatus, MultipartFile pageMainImage, List<MultipartFile> pageImages, List<Long> deletedImages, Seo seo);

    void createAndSaveNewPage(String pageName, String pageDescription, boolean pageStatus, MultipartFile pageMainImage, List<MultipartFile> pageImages, Seo seo);

    Optional<Page> findSimplePageWithImagesById(long pageId);

    Optional<MainPage> findMainPage();

    void updateMainPage(long mainPageId, String mainPageName, String firstPhone, String secondPhone, boolean pageStatus, String seoText, Seo seo);

    void createAndSaveNewMainPage(String mainPageName, String firstPhone, String secondPhone, boolean pageStatus, String seoText, Seo seo);


    Optional<ContactsPage> findContactsPage();

    void updateContactsPage(long contactsPageId, String pageName, boolean pageStatus, Seo seo);

    void createAndSaveNewContactsPage(String pageName, boolean pageStatus, Seo seo);


    Optional<ContactsPage> findContactsPageWithContacts();
}
