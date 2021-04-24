
# CREATE SCHEMA cinema;
CREATE TABLE IF NOT EXISTS Seo(
    seoId BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    url VARCHAR(100),
    title VARCHAR(100),
    keywords VARCHAR(100),
    description TEXT
);
CREATE TABLE IF NOT EXISTS Cinema(
    cinemaId BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    rules TEXT,
    mainImage VARCHAR(100),
    logoImage VARCHAR(100),
    upperBannerImage VARCHAR(100),
    seo_seoId BIGINT,
    FOREIGN KEY (seo_seoId)
        REFERENCES Seo(seoId)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);
CREATE TABLE IF NOT EXISTS CinemaImage(
    cinemaImageId BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    image VARCHAR(100) NULL,
    cinemaId BIGINT NULL,
    FOREIGN KEY (cinemaId)
        REFERENCES Cinema(cinemaId)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);
/*CREATE TABLE IF NOT EXISTS ChildRoom(
    childRoomId BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(100),
    description TEXT,
    mainImage VARCHAR(100),
    seo_seoId BIGINT,
    FOREIGN KEY (seo_seoId)
        REFERENCES Seo(seoId)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);
CREATE TABLE IF NOT EXISTS ChildRoomImage(
    childRoomImageId BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    image VARCHAR(100) NOT NULL,
    childRoomId BIGINT NOT NULL,
    FOREIGN KEY (childRoomId)
        REFERENCES ChildRoom(childRoomId)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);*/
CREATE TABLE IF NOT EXISTS Hall(
    hallId BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    countOfTickets INT NOT NULL,
    schemaImage VARCHAR(100),
    bannerImage VARCHAR(100),
    cinemaId BIGINT NOT NULL,
    seo_seoId BIGINT,
     FOREIGN KEY (cinemaId)
        REFERENCES Cinema(cinemaId)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
     FOREIGN KEY (seo_seoId)
        REFERENCES Seo(seoId)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);
CREATE TABLE IF NOT EXISTS HallImage(
    hallImageId BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    image VARCHAR(100) NOT NULL,
    hallId BIGINT NOT NULL,
    FOREIGN KEY (hallId)
        REFERENCES Hall (hallId)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);
/*CREATE TABLE IF NOT EXISTS Bar(
    barId BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(100),
    description TEXT,
    mainImage VARCHAR(100),
    seo_seoId BIGINT,
    FOREIGN KEY (seo_seoId)
        REFERENCES Seo (seoId)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);
CREATE TABLE IF NOT EXISTS BarImage(
    barImageId BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    image VARCHAR(100) NOT NULL,
    barId BIGINT NOT NULL,
    FOREIGN KEY (barId)
        REFERENCES Bar (barId)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);*/
/*CREATE TABLE IF NOT EXISTS VipHallPage(
    vipHallPageId BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    description TEXT NULL,
    mainImage VARCHAR(100),
    schemaImage VARCHAR(100),
    seo_seoId BIGINT,
    FOREIGN KEY (seo_seoId)
        REFERENCES Seo(seoId)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);
CREATE TABLE IF NOT EXISTS VipHallImage(
    vipHallImageId BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    image VARCHAR(100)  NOT NULL,
    vipHallPageId BIGINT NOT NULL,
    FOREIGN KEY (vipHallPageId)
        REFERENCES VipHallPage(vipHallPageId)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);*/
CREATE TABLE IF NOT EXISTS MainPage(
    mainPageId BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(100),
    firstPhone VARCHAR(100),
    secondPhone VARCHAR(100),
    SEOText TEXT,
    creationDate DATE NOT NULL,
    active BOOLEAN default false,
    seo_seoId BIGINT,
    FOREIGN KEY (seo_seoId)
        REFERENCES Seo(seoId)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);
CREATE TABLE IF NOT EXISTS Page(
  pageId BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  name VARCHAR(100),
  description TEXT,
  creationDate DATE NOT NULL,
  mainImage VARCHAR(100),
  active BOOLEAN default false,
  seo_seoId BIGINT,
  FOREIGN KEY (seo_seoId)
        REFERENCES Seo(seoId)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);
CREATE TABLE IF NOT EXISTS PageImage(
                                             pageImageId BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
                                             image VARCHAR(100) NOT NULL,
                                             pageId BIGINT NOT NULL,
                                             FOREIGN KEY (pageId)
                                                 REFERENCES Page(pageId)
                                                 ON DELETE NO ACTION
                                                 ON UPDATE NO ACTION
);
CREATE TABLE IF NOT EXISTS ContactsPage(
    contactsPageId BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(100),
    creationDate DATE NOT NULL,
    active BOOLEAN default false,
    seo_seoId BIGINT
);
CREATE TABLE IF NOT EXISTS Contact(
                                      contactId BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
                                      cinemaName VARCHAR(100) NOT NULL,
                                      address VARCHAR(100) NOT NULL,
                                      mapCoordinates VARCHAR(100) NOT NULL ,
                                      logoImage VARCHAR(100),
                                      contactsPageId BIGINT,
                                      FOREIGN KEY (contactsPageId)
                                          REFERENCES ContactsPage(contactsPageId)
                                          ON DELETE NO ACTION
                                          ON UPDATE NO ACTION

);
CREATE TABLE IF NOT EXISTS News(
    newsId BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(100),
    creationDate DATE NOT NULL,
    publishDate DATE NOT NULL,
    description TEXT,
    mainImage VARCHAR(100),
    videoLink VARCHAR(100),
    active BOOLEAN default false,
    seo_seoId BIGINT,
    FOREIGN KEY (seo_seoId)
        REFERENCES Seo(seoId)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);
CREATE TABLE IF NOT EXISTS NewsImage(
    newsImageId BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    image VARCHAR(100) NOT NULL,
    newsId BIGINT NOT NULL,
    FOREIGN KEY (newsId)
        REFERENCES News(newsId)
        ON DELETE CASCADE
        ON UPDATE CASCADE

);
CREATE TABLE IF NOT EXISTS Promotion(
    promotionId BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(100),
    creationDate DATE NOT NULL,
    publishDate DATETIME NOT NULL,
    description TEXT,
    mainImage VARCHAR(100),
    videoLink VARCHAR(100),
    active BOOLEAN default false,
    seo_seoId BIGINT,
    FOREIGN KEY (seo_seoId)
        REFERENCES Seo(seoId)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);
CREATE TABLE IF NOT EXISTS PromotionImage(
    promotionImageId BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    image VARCHAR(100) NOT NULL,
    promotionId BIGINT NOT NULL,
    FOREIGN KEY (promotionId)
        REFERENCES Promotion(promotionId)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);
CREATE TABLE IF NOT EXISTS Advertisement(
    advertisementId BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(100),
    description TEXT,
    mainImage VARCHAR(100),
    seo_seoId BIGINT,
    FOREIGN KEY (seo_seoId)
        REFERENCES Seo(seoId)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);
CREATE TABLE IF NOT EXISTS AdvertisementImage(
    advertisementImageId BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    image VARCHAR(100) NOT NULL,
    advertisementId BIGINT NOT NULL,
    FOREIGN KEY (advertisementId)
        REFERENCES Advertisement(advertisementId)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);
/*ALTER TABLE Movie MODIFY COLUMN dateOfRelease DATE;
ALTER TABLE Movie ADD COLUMN dateOfFinish DATE;*/
# ALTER TABLE Movie ADD COLUMN dateOfFinish DATE AFTER dateOfRelease;
CREATE TABLE IF NOT EXISTS Movie(
    movieId BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    mainImage VARCHAR(100),
    trailerLink VARCHAR(100),
    dateOfRelease DATE,
    dateOfFinish DATE,
    seo_seoId BIGINT,
    FOREIGN KEY (seo_seoId)
        REFERENCES Seo(seoId)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);
CREATE TABLE IF NOT EXISTS MovieImage(
    movieImageId BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    image VARCHAR(100) NOT NULL ,
    movieId BIGINT NOT NULL,
    FOREIGN KEY (movieId)
        REFERENCES Movie(movieId)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);
CREATE TABLE IF NOT EXISTS MovieType(
    movieTypeId BIGINT PRIMARY KEY  AUTO_INCREMENT NOT NULL,
    name TEXT NOT NULL
);
CREATE TABLE IF NOT EXISTS MovieTypeMovie(
    movieId BIGINT NOT NULL ,
    movieTypeId BIGINT NOT NULL,
    FOREIGN KEY (movieId)
        REFERENCES Movie(movieId),
    FOREIGN KEY (movieTypeId)
        REFERENCES MovieType(movieTypeId)
);
CREATE TABLE IF NOT EXISTS Session(
    sessionId BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    date DATETIME NOT NULL,
    hallId BIGINT NOT NULL,
    movieId BIGINT NOT NULL,
    FOREIGN KEY (hallId)
        REFERENCES Hall(hallId)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    FOREIGN KEY (movieId)
        REFERENCES Movie(movieId)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);
CREATE TABLE IF NOT EXISTS City(
    cityId BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(100) NOT NULL
);
CREATE TABLE IF NOT EXISTS Language(
                                       languageId BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
                                       name VARCHAR(100) NOT NULL
);
CREATE TABLE IF NOT EXISTS User(
    userId BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    firstName VARCHAR(100),
    lastName VARCHAR(100),
    nickName VARCHAR(100) NOT NULL ,
    password VARCHAR(100) NOT NULL ,
    email VARCHAR(100),
    address VARCHAR(100),
    phone VARCHAR(100),
    cardNumber VARCHAR(100),
    gender INT,
    dateOfRegistration DATE,
    dateOfBirthday DATETIME,
    userType INT,
    cityId BIGINT,
    languageId BIGINT,
    FOREIGN KEY (cityId)
        REFERENCES City(cityId)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    FOREIGN KEY (languageId)
        REFERENCES Language(languageId)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS Ticket(
    ticketId BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    price INT NOT NULL,
    isPurchased bit(1) NOT NULL,
    isBooked bit(1) NOT NULL,
    buyerId BIGINT NULL,
    sessionId BIGINT NOT NULL,
    FOREIGN KEY (buyerId)
        REFERENCES User(userId)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    FOREIGN KEY (sessionId)
        REFERENCES Session(sessionId)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);
INSERT INTO MovieType (name) VALUES ('2D'),('3D'),('4DX'),('ENG');
INSERT INTO Language (name) VALUES ('Russian'),('English'),('Ukrainian');
INSERT INTO City (name) VALUES ('Odessa'),('Kiev'),('Dnipro');