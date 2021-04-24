package com.cinema.cinema.controller;

import com.cinema.cinema.database.entity.Movie;
import com.cinema.cinema.database.entity.Session;
import com.cinema.cinema.service.interfaces.CinemaService;
import com.cinema.cinema.service.interfaces.MovieService;
import com.cinema.cinema.service.interfaces.SessionService;
import com.cinema.cinema.service.interfaces.UserService;
import com.cinema.cinema.util.YoutubeParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    private final MovieService movieService;
    private final CinemaService cinemaService;
    private final SessionService sessionService;
    private final UserService userService;
    @Autowired
    public UserController(MovieService movieService, CinemaService cinemaService, SessionService sessionService,UserService userService) {
        this.movieService = movieService;
        this.cinemaService = cinemaService;
        this.sessionService = sessionService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String admin(Model model) {
        //model.addAttribute("userName", getUserName());
        return "user/mainPage";
    }


    @GetMapping("/poster")
    public String poster(Model model){
        model.addAttribute("movies",movieService.findAllWithTypes());
        return "user/poster";
    }
    @GetMapping("/moviePage")
    public String moviePage(@RequestParam long movieId, Model model){
        Optional<Movie> optionalMovie = movieService.findWithImagesAndTypesById(movieId);
        if (optionalMovie.isPresent()){
            Movie movie = optionalMovie.get();
            model.addAttribute("movie",movie);
            model.addAttribute("youtubeLink", YoutubeParser.parseLink(movie.getTrailerLink()));
            model.addAttribute("cinemas",cinemaService.findAll());
            model.addAttribute("sessions",sessionService.findAllWithHallAndCinemaByMovieId(movie.getMovieId()));
            return "user/moviePage";
        }
        return "user/poster";
    }
    @GetMapping("/chooseTicket")
    public String chooseTicket(Model model,@RequestParam long sessionId){
        model.addAttribute("sessionz",sessionService.findWithTicketsById(sessionId).get());
        return "user/chooseTicket";
    }
    @GetMapping("/buyTicket")
    @ResponseBody
    public void buyTicket(@RequestParam long ticketId){
        userService.findByNickName(getUserName()).ifPresent(user ->
            sessionService.buyTicket(ticketId,user)
        );
    }



    private String getUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
