package com.cinema.cinema.controller;

import com.cinema.cinema.database.entity.*;
import com.cinema.cinema.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final MovieService movieService;
    private final UserService userService;
    private final CinemaService cinemaService;
    private final SessionService sessionService;
    private final HallService hallService;
    private final AdvertisementService advertisementService;
    private final CityService cityService;
    private final ContactService contactService;
    private final NewsService newsService;
    private final PromotionService promotionService;
    private PageService pageService;

    @Autowired
    public AdminController(MovieService movieService, UserService userService, CinemaService cinemaService,
                           SessionService sessionService, HallService hallService, AdvertisementService advertisementService,
                           CityService cityService, ContactService contactService, NewsService newsService, PromotionService promotionService,
                           PageService pageService) {
        this.movieService = movieService;
        this.userService = userService;
        this.cinemaService = cinemaService;
        this.sessionService = sessionService;
        this.hallService = hallService;
        this.advertisementService = advertisementService;
        this.cityService = cityService;
        this.contactService = contactService;
        this.newsService = newsService;
        this.promotionService = promotionService;
        this.pageService = pageService;
    }

    @GetMapping("/")
    public String admin(Model model) {
        model.addAttribute("userName", getUserName());
        return "admin/dashboard";
    }

    @GetMapping("/editMainPage")
    public String editMainPage(Model model) {
        Optional<MainPage> optionalMainPage = pageService.findMainPage();
        if (optionalMainPage.isPresent()) {
            model.addAttribute("mainPage", optionalMainPage.get());
            return "admin/editMainPage";
        }
        return "redirect:/admin/pages";
    }

    @GetMapping("/updateMainPage")
    public String updateMainPage(@RequestParam long mainPageId,
                                 @RequestParam String mainPageName,
                                 @RequestParam String firstPhone,
                                 @RequestParam String secondPhone,
                                 @RequestParam boolean pageStatus,
                                 @RequestParam String SEOText,
                                 @RequestParam String seoURL,
                                 @RequestParam String seoTitle,
                                 @RequestParam String seoKeyWords,
                                 @RequestParam String seoDescription) {
        pageService.updateMainPage(mainPageId, mainPageName, firstPhone, secondPhone, pageStatus, SEOText, new Seo(seoURL, seoTitle, seoKeyWords, seoDescription));
        return "redirect:/admin/pages";
    }

    @GetMapping("/createMainPage")
    public String createMainPage(Model model) {
        if (pageService.findMainPage().isEmpty()) {
            model.addAttribute("userName", getUserName());
            return "admin/createMainPage";
        }
        return "redirect:/admin/pages";
    }

    @GetMapping("/saveMainPage")
    public String saveMainPage(@RequestParam String mainPageName,
                               @RequestParam String firstPhone,
                               @RequestParam String secondPhone,
                               @RequestParam boolean pageStatus,
                               @RequestParam String SEOText,
                               @RequestParam String seoURL,
                               @RequestParam String seoTitle,
                               @RequestParam String seoKeyWords,
                               @RequestParam String seoDescription) {
        pageService.createAndSaveNewMainPage(mainPageName, firstPhone, secondPhone, pageStatus, SEOText, new Seo(seoURL, seoTitle, seoKeyWords, seoDescription));
        return "redirect:/admin/pages";
    }

    @GetMapping("/editContactsPage")
    public String editContactsPage(Model model) {
        Optional<ContactsPage> optionalContactsPage = pageService.findContactsPageWithContacts();
        if (optionalContactsPage.isPresent()) {
            model.addAttribute("contactsPage", optionalContactsPage.get());
            return "admin/editContactsPage";
        }
        return "redirect:/admin/pages";
    }

    @GetMapping("/updateContactsPage")
    public String updateContactsPage(@RequestParam long contactsPageId,
                                     @RequestParam String contactsPageName,
                                     @RequestParam boolean pageStatus,
                                     @RequestParam String seoURL,
                                     @RequestParam String seoTitle,
                                     @RequestParam String seoKeyWords,
                                     @RequestParam String seoDescription) {
        pageService.updateContactsPage(contactsPageId, contactsPageName, pageStatus, new Seo(seoURL, seoTitle, seoKeyWords, seoDescription));
        return "redirect:/admin/pages";
    }

    @GetMapping("/createContactsPage")
    public String createContactsPage(Model model) {
        if (pageService.findContactsPage().isEmpty()) {
            model.addAttribute("userName", getUserName());
            return "admin/createContactsPage";
        }
        return "redirect:/admin/pages";
    }

    @GetMapping("/saveContactsPage")
    public String saveContactsPage(@RequestParam String contactsPageName,
                                   @RequestParam boolean pageStatus,
                                   @RequestParam String seoURL,
                                   @RequestParam String seoTitle,
                                   @RequestParam String seoKeyWords,
                                   @RequestParam String seoDescription) {
        pageService.createAndSaveNewContactsPage(contactsPageName, pageStatus, new Seo(seoURL, seoTitle, seoKeyWords, seoDescription));
        return "redirect:/admin/pages";
    }

    @GetMapping("/pages")
    public String pages(Model model) {
        model.addAttribute("userName", getUserName());
        pageService.findMainPage().ifPresent(mainPage -> model.addAttribute("mainPage", mainPage));
        pageService.findContactsPage().ifPresent(contactsPage -> model.addAttribute("contactsPage", contactsPage));
        model.addAttribute("pages", pageService.findAllSimplePages());
        return "admin/adminPages";
    }

    @GetMapping("/editPage")
    public String editPage(Model model, @RequestParam long pageId) {
        Optional<Page> optionalPage = pageService.findSimplePageWithImagesById(pageId);
        if (optionalPage.isPresent()) {
            model.addAttribute("userName", getUserName());
            model.addAttribute("page", optionalPage.get());
            return "admin/editPage";
        }
        return "redirect:/admin/pages";
    }

    @RequestMapping("/updatePage")
    public String updatePage(@RequestParam long pageId,
                             @RequestParam String pageName,
                             @RequestParam String pageDescription,
                             @RequestParam boolean pageStatus,
                             @RequestParam(required = false) MultipartFile pageMainImage,
                             @RequestParam(required = false) List<MultipartFile> pageImages,
                             @RequestParam(required = false) List<Long> deletedImages,
                             @RequestParam String seoURL,
                             @RequestParam String seoTitle,
                             @RequestParam String seoKeyWords,
                             @RequestParam String seoDescription) {
        pageService.updatePage(pageId, pageName, pageDescription, pageStatus,
                pageMainImage, pageImages, deletedImages, new Seo(seoURL, seoTitle, seoKeyWords, seoDescription));
        return "redirect:/admin/pages";
    }

    @GetMapping("/createPage")
    public String createPage(Model model) {
        model.addAttribute("userName", getUserName());
        return "admin/createPage";
    }

    @RequestMapping("/savePage")
    public String savePage(@RequestParam String pageName,
                           @RequestParam String pageDescription,
                           @RequestParam boolean pageStatus,
                           @RequestParam(required = false) MultipartFile pageMainImage,
                           @RequestParam(required = false) List<MultipartFile> pageImages,
                           @RequestParam String seoURL,
                           @RequestParam String seoTitle,
                           @RequestParam String seoKeyWords,
                           @RequestParam String seoDescription) {

        pageService.createAndSaveNewPage(pageName, pageDescription, pageStatus,
                pageMainImage, pageImages, new Seo(seoURL, seoTitle, seoKeyWords, seoDescription));
        return "redirect:/admin/pages";
    }

    @GetMapping("/deletePage")
    @ResponseBody
    public void deletePage(@RequestParam long pageId) {
        pageService.deleteById(pageId);
    }


    @GetMapping("/promotions")
    public String promotions(Model model) {
        model.addAttribute("userName", getUserName());
        model.addAttribute("promotions", promotionService.findAll());
        return "admin/adminPromotions";
    }

    @GetMapping("/editPromotion")
    public String editPromotion(Model model, @RequestParam long promotionId) {
        Optional<Promotion> optionalPromotion = promotionService.findById(promotionId);
        if (optionalPromotion.isPresent()) {
            model.addAttribute("userName", getUserName());
            model.addAttribute("promotion", optionalPromotion.get());
            return "admin/editPromotion";
        }
        return "redirect:/admin/promotions";
    }

    @RequestMapping("/updatePromotion")
    public String updatePromotion(@RequestParam long promotionId,
                                  @RequestParam String promotionName,
                                  @RequestParam String promotionDescription,
                                  @RequestParam String promotionVideoLink,
                                  @RequestParam boolean promotionStatus,
                                  @RequestParam String promotionPublishDate,
                                  @RequestParam(required = false) MultipartFile promotionMainImage,
                                  @RequestParam(required = false) List<MultipartFile> promotionImages,
                                  @RequestParam(required = false) List<Long> deletedImages,
                                  @RequestParam String seoURL,
                                  @RequestParam String seoTitle,
                                  @RequestParam String seoKeyWords,
                                  @RequestParam String seoDescription) {
        promotionService.updatePromotion(promotionId, promotionName, promotionDescription, promotionVideoLink, promotionStatus, promotionPublishDate,
                promotionMainImage, promotionImages, deletedImages, new Seo(seoURL, seoTitle, seoKeyWords, seoDescription));
        return "redirect:/admin/promotions";
    }

    @GetMapping("/createPromotion")
    public String createPromotion(Model model) {
        model.addAttribute("userName", getUserName());
        return "admin/createPromotion";
    }

    @RequestMapping("/savePromotion")
    public String savePromotion(@RequestParam String promotionName,
                                @RequestParam String promotionDescription,
                                @RequestParam String promotionVideoLink,
                                @RequestParam boolean promotionStatus,
                                @RequestParam String promotionPublishDate,
                                @RequestParam(required = false) MultipartFile promotionMainImage,
                                @RequestParam(required = false) List<MultipartFile> promotionImages,
                                @RequestParam String seoURL,
                                @RequestParam String seoTitle,
                                @RequestParam String seoKeyWords,
                                @RequestParam String seoDescription) {

        promotionService.createAndSaveNewPromotion(promotionName, promotionDescription, promotionVideoLink, promotionStatus, promotionPublishDate,
                promotionMainImage, promotionImages, new Seo(seoURL, seoTitle, seoKeyWords, seoDescription));
        return "redirect:/admin/promotions";
    }

    @GetMapping("/deletePromotion")
    @ResponseBody
    public void deletePromotion(@RequestParam long promotionId) {
        promotionService.deleteById(promotionId);
    }


    @GetMapping("/news")
    public String news(Model model) {
        model.addAttribute("userName", getUserName());
        model.addAttribute("news", newsService.findAll());
        return "admin/adminNews";
    }

    @GetMapping("/editNews")
    public String editNews(Model model, @RequestParam long newsId) {
        Optional<News> optionalNews = newsService.findWithImagesById(newsId);
        if (optionalNews.isPresent()) {
            model.addAttribute("userName", getUserName());
            model.addAttribute("news", optionalNews.get());
            return "admin/editNews";
        }
        return "redirect:/admin/news";
    }

    @RequestMapping("/updateNews")
    public String updateNews(@RequestParam long newsId,
                             @RequestParam String newsName,
                             @RequestParam String newsDescription,
                             @RequestParam String newsVideoLink,
                             @RequestParam boolean newsStatus,
                             @RequestParam String newsPublishDate,
                             @RequestParam(required = false) MultipartFile newsMainImage,
                             @RequestParam(required = false) List<MultipartFile> newsImages,
                             @RequestParam(required = false) List<Long> deletedImages,
                             @RequestParam String seoURL,
                             @RequestParam String seoTitle,
                             @RequestParam String seoKeyWords,
                             @RequestParam String seoDescription) {
        newsService.updateNews(newsId, newsName, newsDescription, newsVideoLink, newsStatus, newsPublishDate,
                newsMainImage, newsImages, deletedImages, new Seo(seoURL, seoTitle, seoKeyWords, seoDescription));
        return "redirect:/admin/news";
    }

    @GetMapping("/createNews")
    public String createNews(Model model) {
        model.addAttribute("userName", getUserName());
        return "admin/createNews";
    }

    @RequestMapping("/saveNews")
    public String saveNews(@RequestParam String newsName,
                           @RequestParam String newsDescription,
                           @RequestParam String newsVideoLink,
                           @RequestParam boolean newsStatus,
                           @RequestParam String newsPublishDate,
                           @RequestParam(required = false) MultipartFile newsMainImage,
                           @RequestParam(required = false) List<MultipartFile> newsImages,
                           @RequestParam String seoURL,
                           @RequestParam String seoTitle,
                           @RequestParam String seoKeyWords,
                           @RequestParam String seoDescription) {

        newsService.createAndSaveNewNews(newsName, newsDescription, newsVideoLink, newsStatus, newsPublishDate,
                newsMainImage, newsImages, new Seo(seoURL, seoTitle, seoKeyWords, seoDescription));
        return "redirect:/admin/news";
    }

    @GetMapping("/deleteNews")
    @ResponseBody
    public void deleteNews(@RequestParam long newsId) {
        newsService.deleteById(newsId);
    }

    @GetMapping("/contacts")
    public String contacts(Model model) {
        model.addAttribute("userName", getUserName());
        model.addAttribute("contacts", contactService.findAll());
        return "admin/adminContacts";
    }

    @GetMapping("/editContact")
    public String editContact(Model model, @RequestParam long contactId) {
        Optional<Contact> optionalContact = contactService.findById(contactId);
        if (optionalContact.isPresent()) {
            model.addAttribute("userName", getUserName());
            model.addAttribute("contact", optionalContact.get());
            return "admin/editContact";
        }
        return "redirect:/admin/editContactsPage";
    }

    @RequestMapping(value = "/updateContact")
    public String updateContact(@RequestParam long contactId,
                                @RequestParam String contactCinemaName,
                                @RequestParam String contactAddress,
                                @RequestParam String contactMapCoordinates,
                                @RequestParam(required = false) MultipartFile contactLogoImage) {
        contactService.updateContact(contactId, contactCinemaName, contactAddress, contactMapCoordinates, contactLogoImage);
        return "redirect:/admin/editContactsPage";
    }

    @GetMapping("/createContact")
    public String createContact(Model model, @RequestParam long contactsPageId) {
        model.addAttribute("userName", getUserName());
        model.addAttribute("contactsPageId",contactsPageId);
        return "admin/createContact";
    }

    @RequestMapping("/saveContact")
    public String saveContact(@RequestParam long contactsPageId,
                              @RequestParam String contactCinemaName,
                              @RequestParam String contactAddress,
                              @RequestParam String contactMapCoordinates,
                              @RequestParam(required = false) MultipartFile contactLogoImage) {
        contactService.createAndSaveNewContact(contactsPageId, contactCinemaName, contactAddress, contactMapCoordinates, contactLogoImage);
        return "redirect:/admin/editContactsPage";
    }

    @GetMapping("/deleteContact")
    @ResponseBody
    public void deleteContact(@RequestParam long contactId) {
        contactService.deleteById(contactId);
    }

    @GetMapping("/cities")
    public String cities(Model model) {
        model.addAttribute("userName", getUserName());
        model.addAttribute("cities", cityService.findAll());
        return "admin/adminCities";
    }

    @GetMapping("/editCity")
    public String editCity(Model model, @RequestParam long cityId) {
        Optional<City> optionalCity = cityService.findById(cityId);
        if (optionalCity.isPresent()) {
            model.addAttribute("userName", getUserName());
            model.addAttribute("city", optionalCity.get());
            return "admin/editCity";
        }
        return "redirect:/admin/cities";
    }

    @GetMapping("/updateCity")
    public String updateCity(@RequestParam long cityId,
                             @RequestParam String cityName) {
        cityService.updateCity(cityId, cityName);
        return "redirect:/admin/cities";
    }

    @GetMapping("/createCity")
    public String createCity(Model model) {
        model.addAttribute("userName", getUserName());
        return "admin/createCity";
    }

    @GetMapping("/saveCity")
    public String saveCity(@RequestParam String cityName) {
        cityService.createAndSaveNewCity(cityName);
        return "redirect:/admin/cities";
    }

    @GetMapping("/deleteCity")
    public String deleteCity(@RequestParam long cityId) {
        cityService.deleteById(cityId);
        return "redirect:/admin/cities";
    }


    @GetMapping("/advertisements")
    public String advertisements(Model model) {
        model.addAttribute("userName", getUserName());
        model.addAttribute("advertisements", advertisementService.findAll());
        return "admin/adminAdvertisements";
    }

    @GetMapping("/editAdvertisement")
    public String editAdvertisement(Model model, @RequestParam long advertisementId) {
        Optional<Advertisement> optionalAdvertisement = advertisementService.findById(advertisementId);
        if (optionalAdvertisement.isPresent()) {
            model.addAttribute("userName", getUserName());
            model.addAttribute("advertisement", optionalAdvertisement.get());
            return "admin/editAdvertisement";
        }
        return "redirect:/admin/advertisements";
    }

    @RequestMapping(value = "/updateAdvertisement")
    public String updateAdvertisement(@RequestParam long advertisementId,
                                      @RequestParam String advertisementName,
                                      @RequestParam String advertisementDescription,
                                      @RequestParam(required = false) MultipartFile advertisementMainImage,
                                      @RequestParam(required = false) List<MultipartFile> advertisementImages,
                                      @RequestParam(required = false) List<Long> deletedImages,
                                      @RequestParam String seoURL,
                                      @RequestParam String seoTitle,
                                      @RequestParam String seoKeyWords,
                                      @RequestParam String seoDescription) {
        advertisementService.updateAdvertisement(advertisementId, advertisementName, advertisementDescription,
                advertisementMainImage, advertisementImages, deletedImages,
                new Seo(seoURL, seoTitle, seoKeyWords, seoDescription));
        return "redirect:/admin/advertisements";
    }

    @GetMapping("/createAdvertisement")
    public String createAdvertisement(Model model) {
        model.addAttribute("userName", getUserName());
        return "admin/createAdvertisement";
    }

    @RequestMapping("/saveAdvertisement")
    public String saveAdvertisement(@RequestParam String advertisementName,
                                    @RequestParam String advertisementDescription,
                                    @RequestParam(required = false) MultipartFile advertisementMainImage,
                                    @RequestParam(required = false) List<MultipartFile> advertisementImages,
                                    @RequestParam String seoURL,
                                    @RequestParam String seoTitle,
                                    @RequestParam String seoKeyWords,
                                    @RequestParam String seoDescription) {
        advertisementService.createAndSaveNewAdvertisement(advertisementName, advertisementDescription, advertisementMainImage, advertisementImages,
                new Seo(seoURL, seoTitle, seoKeyWords, seoDescription));
        return "redirect:/admin/advertisements";
    }

    @GetMapping("/deleteAdvertisement")
    @ResponseBody
    public void deleteAdvertisement(@RequestParam long advertisementId) {
        advertisementService.deleteById(advertisementId);
    }


    @GetMapping("/sessions")
    public String sessions(Model model) {
        model.addAttribute("userName", getUserName());
        model.addAttribute("sessions", sessionService.findAll());
        return "admin/adminSessions";
    }

    @GetMapping("/editSession")
    public String editSession(Model model,
                              @RequestParam long hallId,
                              @RequestParam long sessionId) {
        Optional<Session> optionalSession = sessionService.findWithMovieById(sessionId);
        if (optionalSession.isPresent()) {
            Session session = optionalSession.get();
            model.addAttribute("sessionn", session);
            model.addAttribute("hallId",hallId);
            model.addAttribute("movies", movieService.findAll());
            return "admin/editSession";
        }
        return "redirect:/admin/sessions";
    }

    @GetMapping("/updateSession")
    public ModelAndView updateSession(ModelMap model,
                                @RequestParam long hallId,
                                @RequestParam long sessionId,
                                @RequestParam long movieId,
                                @RequestParam String sessionDate) {
        model.addAttribute("hallId",hallId);
        sessionService.updateSession(sessionId, movieId, sessionDate);
        //return new ModelAndView("redirect:/admin/editHall", model);
        return new ModelAndView("redirect:/admin/cinemas", model);
    }

    @GetMapping("/createSession")
    public String createSession(Model model, @RequestParam long hallId) {
        model.addAttribute("hallId", hallId);
        model.addAttribute("movies", movieService.findAll());
        return "admin/createSession";
    }

    @GetMapping("/saveSession")
    public ModelAndView saveSession(ModelMap model,
                              @RequestParam long movieId,
                              @RequestParam long hallId,
                              @RequestParam String sessionDate) {
        model.addAttribute("hallId",hallId);
        sessionService.createAndSaveNewSession(hallId, movieId, sessionDate);
        return new ModelAndView("redirect:/admin/cinemas", model);
    }



    @GetMapping("/deleteSession")
    @ResponseBody
    public void deleteSession(@RequestParam long sessionId) {
        sessionService.deleteById(sessionId);
    }


    @GetMapping("/editHall")
    public String editHall(Model model,
                                 @RequestParam long cinemaId,
                                 @RequestParam long hallId) {
        Optional<Hall> optionalHall = hallService.findWithImagesAndSessionsById(hallId);
        if (optionalHall.isPresent()) {
            model.addAttribute("userName", getUserName());
            model.addAttribute("cinemaId", cinemaId);
            model.addAttribute("hall", optionalHall.get());
            return "admin/editHall";
        }
        return "redirect:/admin/cinemas";
    }

    @RequestMapping(value = "/updateHall")
    public ModelAndView updateHall(ModelMap model,
            @RequestParam long cinemaId,
            @RequestParam Long hallId,
            @RequestParam String hallName,
            @RequestParam String hallDescription,
            @RequestParam(required = false) MultipartFile hallSchemaImage,
            @RequestParam(required = false) MultipartFile hallBannerImage,
            @RequestParam(required = false) List<MultipartFile> hallImages,
            @RequestParam(required = false, name = "deletedImage") List<Long> deletedImages,
            @RequestParam String seoURL,
            @RequestParam String seoTitle,
            @RequestParam String seoKeyWords,
            @RequestParam String seoDescription) {
        model.addAttribute("cinemaId",cinemaId);
        hallService.updateHall(hallId, hallName, hallDescription, hallSchemaImage,
                hallBannerImage, hallImages, deletedImages, new Seo(seoURL, seoTitle, seoKeyWords, seoDescription));
        return new ModelAndView("redirect:/admin/editCinema", model);

        //return "redirect:/admin/cinemas";
    }

    @GetMapping("/createHall")
    public ModelAndView createHall(ModelMap model, @RequestParam long cinemaId) {
        model.addAttribute("userName", getUserName());
        model.addAttribute("cinemaId", cinemaId);
        return new ModelAndView("admin/createHall", model);
    }

    @RequestMapping("/saveHall")
    public ModelAndView saveHall(ModelMap model,
            @RequestParam String hallName,
            @RequestParam String hallDescription,
            @RequestParam int countOfTickets,
            @RequestParam long cinemaId,
            @RequestParam(required = false) MultipartFile hallSchemaImage,
            @RequestParam(required = false) MultipartFile hallBannerImage,
            @RequestParam(required = false) List<MultipartFile> hallImages,
            @RequestParam String seoURL,
            @RequestParam String seoTitle,
            @RequestParam String seoKeyWords,
            @RequestParam String seoDescription
    ) {
        model.addAttribute("cinemaId",cinemaId);
        hallService.createAndSaveNewHall(hallName, hallDescription, countOfTickets, cinemaId, hallSchemaImage, hallBannerImage, hallImages,
                new Seo(seoURL, seoTitle, seoKeyWords, seoDescription));
        return new ModelAndView("redirect:/admin/editCinema", model);
    }

    @GetMapping("/deleteHall")
    @ResponseBody
    public void deleteHall(@RequestParam Long hallId) {
        hallService.deleteById(hallId);
    }


    @RequestMapping("/setHallSchemaImage")
    @ResponseBody
    public String setHallSchemaImage(@RequestParam MultipartFile file, @RequestParam long hallId) {
        return hallService.setSchemaImageToHall(hallId, file);
    }

    @RequestMapping("/setHallBannerImage")
    @ResponseBody
    public String setHallBannerImage(@RequestParam MultipartFile file, @RequestParam long hallId) {
        return hallService.setBannerImageToHall(hallId, file);
    }

    @RequestMapping("/addImageInHall")
    @ResponseBody
    public String addImageInHall(@RequestParam MultipartFile file, @RequestParam long hallId) {
        return hallService.addImageToHall(hallId, file).getImage();
    }

    @RequestMapping("/deleteImageInHall")
    @ResponseBody
    public boolean deleteImageInHall(@RequestParam String hallImageName) {
        hallService.deleteHallImage(hallImageName);
        return true;
    }


    @GetMapping("/cinemas")
    public String cinemas(Model model) {
        model.addAttribute("userName", getUserName());
        model.addAttribute("cinemas", cinemaService.findAll());
        return "admin/adminCinemas";
    }

    @GetMapping("/editCinema")
    public String editCinema(Model model, @RequestParam long cinemaId) {
        Optional<Cinema> optionalCinema = cinemaService.findWithImagesAndHallsById(cinemaId);
        if (optionalCinema.isPresent()) {
            model.addAttribute("userName", getUserName());
            model.addAttribute("cinema", optionalCinema.get());
            return "admin/editCinema";
        }
        return "redirect:/admin/cinemas";

    }

    @RequestMapping("/setCinemaMainImage")
    @ResponseBody
    public String setCinemaMainImage(@RequestParam MultipartFile file, @RequestParam long cinemaId) {
        return cinemaService.setMainImageToCinema(cinemaId, file);
    }

    @RequestMapping("/setCinemaLogoImage")
    @ResponseBody
    public String setCinemaLogoImage(@RequestParam MultipartFile file, @RequestParam long cinemaId) {
        return cinemaService.setLogoImageToCinema(cinemaId, file);
    }

    @RequestMapping("/setCinemaUpperBannerImage")
    @ResponseBody
    public String setCinemaUpperBannerImage(@RequestParam MultipartFile file, @RequestParam long cinemaId) {
        return cinemaService.setUpperBannerImageToCinema(cinemaId, file);
    }

    @RequestMapping(value = "/addImageInCinema", method = RequestMethod.POST)
    @ResponseBody
    public String addImageInCinema(@RequestParam MultipartFile file, @RequestParam long cinemaId) {
        return cinemaService.addImageToCinema(cinemaId, file).getImage();
    }

    @GetMapping("/deleteImageInCinema")
    @ResponseBody
    public boolean deleteImageInCinema(@RequestParam String cinemaImageName) {
        cinemaService.deleteCinemaImage(cinemaImageName);
        return true;
    }

    @RequestMapping(value = "/updateCinema")
    public String updateCinema(
            @RequestParam Long cinemaId,
            @RequestParam String cinemaName,
            @RequestParam String cinemaDescription,
            @RequestParam String cinemaRules,
            @RequestParam(required = false) MultipartFile mainImageFile,
            @RequestParam(required = false) MultipartFile logoImageFile,
            @RequestParam(required = false) MultipartFile UpperBannerImageFile,
            @RequestParam(required = false) List<MultipartFile> cinemaImages,
            @RequestParam(required = false, name = "deletedImage") List<Long> deletedImages,
            @RequestParam String seoURL,
            @RequestParam String seoTitle,
            @RequestParam String seoKeyWords,
            @RequestParam String seoDescription) {
        cinemaService.updateCinema(cinemaId, cinemaName, cinemaDescription, cinemaRules,
                mainImageFile, logoImageFile, UpperBannerImageFile, cinemaImages, deletedImages,
                new Seo(seoURL, seoTitle, seoKeyWords, seoDescription));

        return "redirect:/admin/cinemas";
    }

    @GetMapping("/createCinema")
    public String createCinema(Model model) {
        model.addAttribute("userName", getUserName());
        return "admin/createCinema";
    }

    @RequestMapping(value = "/saveCinema", method = RequestMethod.POST)
    public String saveCinema(
            @RequestParam String cinemaName,
            @RequestParam String cinemaDescription,
            @RequestParam String cinemaRules,
            @RequestParam(required = false) MultipartFile mainImageFile,
            @RequestParam(required = false) MultipartFile logoImageFile,
            @RequestParam(required = false) MultipartFile UpperBannerImageFile,
            @RequestParam(required = false) List<MultipartFile> cinemaImages,
            @RequestParam String seoURL,
            @RequestParam String seoTitle,
            @RequestParam String seoKeyWords,
            @RequestParam String seoDescription
    ) {
        cinemaService.createAndSaveNewCinema(cinemaName, cinemaDescription, cinemaRules, mainImageFile, logoImageFile, UpperBannerImageFile, cinemaImages,
                new Seo(seoURL, seoTitle, seoKeyWords, seoDescription));
        return "redirect:/admin/cinemas";
    }

    @GetMapping("/deleteCinema")
    @ResponseBody
    public void deleteCinema(@RequestParam Long cinemaId) {
        cinemaService.deleteById(cinemaId);
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("userName", getUserName());
        model.addAttribute("users", userService.findAll());
        return "admin/adminUsers";
    }

    @GetMapping("/editUser")
    public String editUser(Model model, @RequestParam long userId) {
        Optional<User> optionalUser = userService.findById(userId);
        if (optionalUser.isPresent()) {
            model.addAttribute("user", optionalUser.get());
            model.addAttribute("userName", getUserName());
            model.addAttribute("cities", userService.getAllCities());
            model.addAttribute("languages", userService.getAllLanguages());
            return "admin/editUser";
        }
        return "redirect:/admin/users";

    }

    @GetMapping("/updateUser")
    public String updateUser(
            @RequestParam long userId,
            @RequestParam(required = false) String userFirstName,
            @RequestParam(required = false) String userLastName,
            @RequestParam(required = false) String userNickName,
            @RequestParam(required = false) String userEmail,
            @RequestParam(required = false) String userAddress,
            @RequestParam(required = false) String userPhone,
            @RequestParam(required = false) String userCardNumber,
            @RequestParam(required = false) int userGender,
            @RequestParam(required = false) String userDOB,
            @RequestParam(required = false) int userType,
            @RequestParam long userCityId,
            @RequestParam long userLanguageId
    ) {
        userService.updateUser(userId, userFirstName, userLastName, userNickName, userEmail
                , userAddress, userPhone, userCardNumber, userGender, userDOB, userType, userCityId, userLanguageId);
        //System.out.println(userDOB);
        return "redirect:/admin/users";
    }

    @GetMapping("/deleteUser")
    @ResponseBody
    public void deleteUser(@RequestParam Long userId) {
        userService.deleteById(userId);
    }

    @GetMapping("/movies")
    public String movies(Model model) {
        model.addAttribute("userName", getUserName());
        model.addAttribute("movies", movieService.findAll());
        return "admin/adminMovies";
    }


    @GetMapping("/createMovie")
    public String createMovie(Model model) {
        model.addAttribute("movieTypes", movieService.findAllMovieTypes());
        return "admin/createMovie";
    }


    @RequestMapping(value = "/saveMovie", method = RequestMethod.POST)
    public String saveMovie(
            @RequestParam String movieName,
            @RequestParam String description,
            @RequestParam String dateRange,
            @RequestParam(name = "type", required = false) List<Long> movieTypeIds,
            @RequestParam(required = false, name = "mainImageFile") MultipartFile mainMovieImage,
            @RequestParam(required = false) List<MultipartFile> movieImages,
            @RequestParam String trailerLink,
            @RequestParam String seoURL,
            @RequestParam String seoTitle,
            @RequestParam String seoKeyWords,
            @RequestParam String seoDescription) {

        movieService.createAndSaveNewMovie(movieName, description, dateRange, movieTypeIds, mainMovieImage, movieImages, trailerLink, new Seo(seoURL, seoTitle, seoKeyWords, seoDescription));

        return "redirect:/admin/movies";
    }

    @RequestMapping(value = "/editMovie", method = RequestMethod.POST)
    public String editMovie(Model model, @RequestParam Long id) {
        movieService.findWithImagesAndTypesById(id).ifPresent(movie -> {
            model.addAttribute("userName", getUserName());
            model.addAttribute("movie", movie);
            model.addAttribute("movieImage", movie.getMainImage());
            List<MovieType> types = movieService.findAllMovieTypes();
            types.forEach(type -> {
                if (movie.getTypes().contains(type)) {
                    type.setSelected(true);
                }
            });
            model.addAttribute("movieTypes", types);
        });

        return "admin/editMovie";
    }

    /*@RequestMapping(value = "/addImageInMove", method = RequestMethod.POST)
    @ResponseBody
    public String addImageInMove(@RequestParam MultipartFile file, @RequestParam long movieId) {
        return movieService.addImageToMovie(movieId, file).getImage();
    }

    @GetMapping("/deleteImageInMove")
    @ResponseBody
    public boolean deleteImageInMove(@RequestParam String movieImageName) {
        movieService.deleteMovieImage(movieImageName);
        return true;
    }

    @RequestMapping("/setMovieMainImage")
    @ResponseBody
    public String setMovieMainImage(@RequestParam MultipartFile file, @RequestParam long movieId) {
        return movieService.setMainImageToMovie(movieId, file);
    }*/

    @RequestMapping(value = "/updateMovie")
    public String updateMovie(
            @RequestParam Long id,
            @RequestParam String movieName,
            @RequestParam String description,
            @RequestParam String dateRange,
            @RequestParam(required = false, name = "type") List<Long> movieTypeIds,
            @RequestParam(required = false, name = "mainImageFile") MultipartFile mainMovieImage,
            @RequestParam(required = false, name = "deletedImage") List<Long> deletedImages,
            @RequestParam(required = false) List<MultipartFile> movieImages,
            @RequestParam String trailerLink,
            @RequestParam String seoURL,
            @RequestParam String seoTitle,
            @RequestParam String seoKeyWords,
            @RequestParam String seoDescription) {

        movieService.updateMovie(id, movieName, description, dateRange, movieTypeIds, mainMovieImage, deletedImages, movieImages, trailerLink,
                new Seo(seoURL, seoTitle, seoKeyWords, seoDescription));

        return "redirect:/admin/movies";
    }

    @GetMapping("/deleteMovie")
    @ResponseBody
    public void deleteMovie(@RequestParam Long movieId) {
        movieService.deleteById(movieId);
    }

    @RequestMapping("/saveImage")
    @ResponseBody
    public String saveImage(@RequestParam MultipartFile file) {
        return movieService.saveImageAndGetName(file);
    }

    private String getUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
