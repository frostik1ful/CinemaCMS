/*SELECT c.name, s.title FROM cinema c left join seo s on c.seo_ID = s.seo_ID;
SELECT COUNT(*), s.title FROM cinema c JOIN seo s ON c.seo_ID = s.seo_ID GROUP BY s.seo_ID;
SELECT c.name, m.name FROM cinema c JOIN hall h on c.cinema_ID = h.cinema_ID join session s on h.hall_ID = s.hall_ID join movie m on s.movie_ID = m.movie_ID;
SELECT c.name, h.name FROM cinema c join hall h on c.cinema_ID = h.cinema_ID;
SELECT MAX(ticket.price) FROM ticket;
SELECT AVG(ticket.price) FROM ticket;
SELECT m.name, s.title FROM movie m LEFT JOIN seo s on m.seo_ID = s.seo_ID;
SELECT h.name FROM hall h WHERE EXISTS(SELECT h2.hall_ID FROM hall h2 WHERE h2.hall_ID = h1.);*/


SELECT * FROM ticket WHERE price > (SELECT AVG(ticket.price) FROM ticket);

SELECT c.name, (SELECT count(*) FROM ticket t join session s on t.sessionId = s.sessionId join
    hall h on s.hallId = h.hallId WHERE h.cinemaId = c.cinemaId) FROM cinema c;

/*SELECT u.first_name, m.name, c.name FROM user u join ticket t on u.user_ID = t.buyer_ID join session s on t.session_ID = s.session_ID join movie m on s.movie_ID = m.movie_ID join hall h on s.hall_ID = h.hall_ID join cinema c on h.cinema_ID = c.cinema_ID;
SELECT m.name, t.ticket_ID FROM ticket t join session s on t.session_ID = s.session_ID join movie m on s.movie_ID = m.movie_ID;
SELECT COUNT(t.ticket_ID),m.name FROM ticket t join session s on t.session_ID = s.session_ID join movie m on s.movie_ID = m.movie_ID Group By m.name;
SELECT COUNT(ticket.ticket_ID) AS count_of_tickets, c.name FROM ticket join session s on ticket.session_ID = s.session_ID join hall h on s.hall_ID = h.hall_ID join cinema c on h.cinema_ID = c.cinema_ID GROUP BY c.name;
DESC cinema;
DESC seo;
DESC hall;
DESC movie;
DESC user;
DESC city;
DESC ticket;
ALTER TABLE movie ADD name VARCHAR(100) AFTER movie_ID;
ALTER TABLE movie MODIFY name VARCHAR(100) NOT NULL;
ALTER TABLE hall ADD name VARCHAR(100) NOT NULL AFTER hall_ID;
UPDATE hall SET name = 'hall1_name' WHERE hall_ID = 1;
INSERT INTO seo (url,title,keywords,description)
VALUES ('first.seo.url','seoTitle1','keywords1','this is first seo'),
       ('second.seo.url','seoTitle2','keywords2','this is second seo');
SELECT * FROM seo;
SELECT * FROM cinema;
SELECT * FROM movie;
SELECT * FROM hall;
SELECT * FROM city;
SELECT * FROM user;
SELECT * FROM ticket;
SELECT * FROM session;
INSERT INTO city (name) VALUES ('Kiev');
INSERT INTO cinema (name, description, rules, mainImage, logoImage, upperBannerImage, seo_seoId)
VALUES ('rodina','rodina cinema','rules4','rodina.jpg','rodina_logo.jpg','upper_banner3.jpg',2);
INSERT INTO hall (name,description, schemaImage, bannerImage, cinemaId, seo_seoId)
VALUES ('hall2_name','hall2','hall2_schema.jpg','hall2_banner',3,2);
INSERT INTO movie (name,description, mainImage, trailerLink, movieType, seo_seoId)
VALUES ('movie6','movie5 description','movie5_main.jpg','www.movie5_trailer.com',2,2);
INSERT INTO session (date, hallId, movieId)
VALUES ('2010-12-21 21:30:00',3,1);
INSERT INTO user (firstName, lastName, nickName, password, email, address, phone, cardNumber, language, gender, dateOfBirthday, userType, cityId)
VALUES ('Ivan','Ivanov','IVAN','pass1234','ivan@gmail.com','breusa 25','12345678','876543210','1','1','1995-10-11 00:00:00',1,1);
INSERT INTO ticket (price, isPurchased, isBooked, buyerId, sessionId)
VALUES (70,1,0,1,2);*/