package com.cinema.cinema.controller;

import com.cinema.cinema.service.interfaces.UserService;
import com.cinema.cinema.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/")
public class AuthorizeController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final Validator validator;


    @Autowired
    public AuthorizeController(UserService userService, PasswordEncoder passwordEncoder, Validator validator) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.validator = validator;
    }

    @GetMapping("/")
    public RedirectView welcome() {
        return new RedirectView("login");
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("registration")
    public String registration(Model model) {
        model.addAttribute("cities", userService.getAllCities());
        model.addAttribute("languages", userService.getAllLanguages());
        return "registration";
    }

    @RequestMapping(value="register",method= RequestMethod.POST)
    public String register(ModelMap model,
                                 @RequestParam String userName,
                                 @RequestParam String email,
                                 @RequestParam String password,
                                 @RequestParam String confirmPassword,
                                 @RequestParam(required = false) String firstName,
                                 @RequestParam(required = false) String lastName,
                                 @RequestParam(required = false) String address,
                                 @RequestParam(required = false) String phone,
                                 @RequestParam(required = false) String cardNumber,
                                 @RequestParam(required = false) int gender,
                                 @RequestParam(required = false) String userDOB,
                                 @RequestParam(required = false) int userType,
                                 @RequestParam(required = false, defaultValue = "0") long cityId,
                                 @RequestParam(required = false, defaultValue = "0") long languageId) {
        List<String> errors = userService.tryRegisterNewUser(userName,email,password,confirmPassword,firstName,lastName,address,phone,cardNumber,
                gender,userDOB,userType,cityId,languageId);


        if (!errors.isEmpty()){
            model.addAttribute("errors", errors);
            return "registration";
        }
        else {
            model.addAttribute("name",userName);
            model.addAttribute("password",password);
            return "login";
        }
//
    }
}
