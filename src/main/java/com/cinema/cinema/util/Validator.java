package com.cinema.cinema.util;

import com.cinema.cinema.database.dao.interfaces.CityDAO;
import com.cinema.cinema.database.dao.interfaces.LanguageDAO;
import com.cinema.cinema.database.dao.interfaces.UserDAO;
import com.cinema.cinema.database.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Validator {
    private Pattern pattern = Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}");
    private final UserDAO userDAO;
    private final CityDAO cityDAO;
    private final LanguageDAO languageDAO;
    @Autowired
    public Validator(UserDAO userDAO, CityDAO cityDAO, LanguageDAO languageDAO) {
        this.userDAO = userDAO;
        this.cityDAO = cityDAO;
        this.languageDAO = languageDAO;
    }

    public List<String> validateUser(String userName, String email, String password, String confirmPassword, String firstName, String lastName, String address, String phone, String cardNumber, int gender, String userDOB, int userType, long cityId, long languageId){
        List<String> errors = new ArrayList<>();
        int min = 6;
        int max = 100;
        if (!checkLength(userName,min,max)){
            errors.add("name must be between 6 and 100");
        }
        else{
            if (userDAO.findUserByNickName(userName).isPresent()){
                errors.add("User with this name already exists");
            }
        }

        if (!checkLength(password,min,max)){
            errors.add("password must be between 6 and 100");
        }
        else{
            if (!password.equals(confirmPassword)){
                errors.add("passwords don't match");
            }
        }
        if (!checkLength(email,min+3,max)||!checkEmail(email)){
            errors.add("email is not valid");
        }
        if (!firstName.isEmpty() && !checkLength(firstName,min,max)){
            errors.add("firstName must be between 6 and 100");
        }
        if (!lastName.isEmpty() && !checkLength(lastName,min,max)){
            errors.add("lastName must be between 6 and 100");
        }
        if (!address.isEmpty() && !checkLength(address,min,max)){
            errors.add("address must be between 6 and 100");
        }
        if (!phone.isEmpty() && phone.length() != 14){
            errors.add("phone must be 10 digits in long");
        }
        if (!cardNumber.isEmpty() && cardNumber.length() != 16){
            errors.add("card number must be 16 digits in long");
        }
        if (userDOB != null && !userDOB.isEmpty()){
            try {
                DateTimeParser.convertStringToDate(userDOB);
            } catch (ParseException e) {
                errors.add("date of birthday is not valid");
            }
        }

        if (cityId > 0 && cityDAO.findById(cityId).isEmpty()){
            errors.add("this city doesn't exist");
        }
        if (languageId > 0 && languageDAO.findById(languageId).isEmpty()){
            errors.add("this language doesn't exist");
        }

        return errors;
    }
    private boolean checkLength(String string, int min, int max){
        return string.length() >= min && string.length() <= max;
    }
    private boolean checkEmail(String email){
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


}
