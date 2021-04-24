package com.cinema.cinema.service.classes;

import com.cinema.cinema.database.dao.interfaces.ContactsPageDAO;
import com.cinema.cinema.database.dao.interfaces.MainPageDAO;
import com.cinema.cinema.database.dao.interfaces.PageDAO;
import com.cinema.cinema.database.dao.interfaces.PageImageDAO;
import com.cinema.cinema.database.entity.*;
import com.cinema.cinema.service.interfaces.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PageServiceImpl extends MainServiceImpl<Page> implements PageService {
    private final PageDAO pageDAO;
    private final PageImageDAO pageImageDAO;
    private final MainPageDAO mainPageDAO;
    private final ContactsPageDAO contactsPageDAO;
    @Autowired
    public PageServiceImpl(PageDAO pageDAO, PageImageDAO pageImageDAO,MainPageDAO mainPageDAO, ContactsPageDAO contactsPageDAO) {
        super(pageDAO);
        this.pageDAO = pageDAO;
        this.pageImageDAO = pageImageDAO;
        this.mainPageDAO = mainPageDAO;
        this.contactsPageDAO = contactsPageDAO;
    }

    @Override
    public List<Page> findAllSimplePages() {
       return pageDAO.findAll();
    }

    @Override
    public Optional<MainPage> findMainPage() {
        return mainPageDAO.findSingle();
    }

    @Override
    public void updateMainPage(long mainPageId, String mainPageName, String firstPhone, String secondPhone, boolean pageStatus, String seoText, Seo seo) {
        mainPageDAO.findById(mainPageId).ifPresent(page -> {
            page.setName(mainPageName);
            page.setFirstPhone(firstPhone);
            page.setSecondPhone(secondPhone);
            page.setActive(pageStatus);
            page.setSEOText(seoText);
            seo.setSeoId(page.getSeo().getSeoId());
            page.setSeo(seo);
            mainPageDAO.update(page);
        });
    }

    @Override
    public void createAndSaveNewMainPage(String mainPageName, String firstPhone, String secondPhone, boolean pageStatus,
                                         String seoText, Seo seo) {
        MainPage mainPage = new MainPage(mainPageName, firstPhone, secondPhone,seoText,Date.valueOf(LocalDate.now()), pageStatus, seo);
        if (mainPageDAO.findSingle().isEmpty()){
            mainPageDAO.save(mainPage);
        }

    }

    @Override
    public Optional<ContactsPage> findContactsPage() {
        return contactsPageDAO.findSingle();
    }
    @Override
    public Optional<ContactsPage> findContactsPageWithContacts() {
        return contactsPageDAO.findSingleWithContacts();
    }

    @Override
    public void updateContactsPage(long contactsPageId, String pageName, boolean pageStatus, Seo seo) {
        contactsPageDAO.findById(contactsPageId).ifPresent(page -> {
            page.setName(pageName);
            page.setActive(pageStatus);
            seo.setSeoId(page.getSeo().getSeoId());
            page.setSeo(seo);
            contactsPageDAO.update(page);
        });
    }

    @Override
    public void createAndSaveNewContactsPage(String pageName, boolean pageStatus, Seo seo) {
        ContactsPage contactsPage = new ContactsPage(pageName,Date.valueOf(LocalDate.now()), pageStatus,seo);
        if (contactsPageDAO.findSingle().isEmpty()){
            contactsPageDAO.save(contactsPage);
        }
    }




    @Override
    public void updatePage(long pageId, String pageName, String pageDescription, boolean pageStatus, MultipartFile pageMainImage, List<MultipartFile> pageImages, List<Long> deletedImages, Seo seo) {
        findById(pageId).ifPresent(page -> {
            page.setName(pageName);
            page.setDescription(pageDescription);
            page.setActive(pageStatus);
            seo.setSeoId(page.getSeo().getSeoId());
            page.setSeo(seo);
            if (!pageMainImage.isEmpty()){
                page.setMainImage(saveImageAndGetName(pageMainImage));
            }
            if (pageImages != null){
                pageImages.forEach(image -> page.addPageImage(new PageImage(saveImageAndGetName(image))));
            }
            update(page);
            if (deletedImages != null){
                pageImageDAO.deleteByListOfId(deletedImages);
            }
        });
    }

    @Override
    public void createAndSaveNewPage(String pageName, String pageDescription, boolean pageStatus, MultipartFile pageMainImage, List<MultipartFile> pageImages, Seo seo) {
        Page page = new Page(pageName,Date.valueOf(LocalDate.now()),pageDescription,pageStatus,seo);
        if (!pageMainImage.isEmpty()){
            page.setMainImage(saveImageAndGetName(pageMainImage));
        }
        if (pageImages != null){
            pageImages.forEach(image -> page.addPageImage(new PageImage(saveImageAndGetName(image))));
        }
        save(page);
    }

    @Override
    public Optional<Page> findSimplePageWithImagesById(long pageId) {
       return pageDAO.findWithImagesById(pageId);
    }


}
