package com.cinema.cinema.service.classes;

import com.cinema.cinema.database.dao.interfaces.CityDAO;
import com.cinema.cinema.database.dao.interfaces.LanguageDAO;
import com.cinema.cinema.database.dao.interfaces.UserDAO;
import com.cinema.cinema.database.entity.City;
import com.cinema.cinema.database.entity.Language;
import com.cinema.cinema.database.entity.User;
import com.cinema.cinema.service.interfaces.UserService;
import com.cinema.cinema.util.DateTimeParser;
import com.cinema.cinema.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl extends MainServiceImpl<User> implements UserService {
    private final Validator validator;
    private final UserDAO userDAO;
    private final LanguageDAO languageDAO;
    private final CityDAO cityDAO;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserServiceImpl(Validator validator,PasswordEncoder passwordEncoder, UserDAO userDAO, LanguageDAO languageDAO,CityDAO cityDAO) {
        super(userDAO);
        this.validator = validator;
        this.passwordEncoder = passwordEncoder;
        this.userDAO = userDAO;
        this.languageDAO = languageDAO;
        this.cityDAO = cityDAO;
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public List<Language> getAllLanguages() {
        return languageDAO.findAll();
    }

    @Override
    public List<City> getAllCities() {
        return cityDAO.findAll();
    }

    @Override
    public void updateUser(long userId, String userFirstName, String userLastName, String userNickName, String userEmail, String userAddress,
                           String userPhone, String userCardNumber, int userGender, String userDOB, int userType,long userCityId,long userLanguageId) {
        Optional<User> optionalUser = userDAO.findById(userId);
        optionalUser.ifPresent(user -> {
            user.setFirstName(userFirstName);
            user.setLastName(userLastName);
            user.setNickName(userNickName);
            user.setEmail(userEmail);
            user.setAddress(userAddress);
            user.setPhone(userPhone);
            user.setCardNumber(userCardNumber);
            user.setGender(User.UserGender.values()[userGender]);
            try {
                user.setDateOfBirthday(DateTimeParser.convertStringToDate(userDOB));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            user.setUserType(User.UserType.values()[userType]);
            cityDAO.findById(userCityId).ifPresent(user::setCity);
            languageDAO.findById(userLanguageId).ifPresent(user::setLanguage);
            update(user);
        });
    }

    @Override
    public Optional<User> findByNickName(String userName) {
        return userDAO.findUserByNickName(userName);
    }

    @Override
    public List<String>  tryRegisterNewUser(String userName, String email, String password, String confirmPassword, String firstName, String lastName, String address, String phone, String cardNumber, int gender, String userDOB, int userType, long cityId, long languageId) {

        List<String> errors = validator.validateUser(userName,email,password,confirmPassword,firstName,lastName,address,phone,cardNumber,gender,userDOB,userType,cityId,languageId);
        if (errors.isEmpty()){
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setNickName(userName);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password));
            user.setAddress(address);
            user.setPhone(phone);
            user.setCardNumber(cardNumber);
            user.setGender(User.UserGender.values()[gender]);
            user.setDateOfRegistration(Date.valueOf(LocalDate.now()));
            if (userDOB != null){
                try {
                    user.setDateOfBirthday(DateTimeParser.convertStringToDate(userDOB));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            user.setUserType(User.UserType.values()[userType]);
            cityDAO.findById(cityId).ifPresent(user::setCity);
            languageDAO.findById(languageId).ifPresent(user::setLanguage);
            save(user);
        }
        return errors;
    }
}
