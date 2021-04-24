package com.cinema.cinema.service.interfaces;

import com.cinema.cinema.database.entity.City;
import com.cinema.cinema.database.entity.Language;
import com.cinema.cinema.database.entity.User;
import com.cinema.cinema.util.Validator;

import java.util.List;
import java.util.Optional;


public interface UserService extends MainService<User>{
    List<User> findAll();
    List<Language> getAllLanguages();
    List<City> getAllCities();
    void updateUser(long userId, String userFirstName, String userLastName, String userNickName, String userEmail, String userAddress, String userPhone, String userCardNumber, int userGender, String userDOB, int userType,long userCityId,long userLanguageId);

    Optional<User> findByNickName(String userName);

    List<String> tryRegisterNewUser(String userName, String email, String password, String confirmPassword, String firstName, String lastName, String address, String phone, String cardNumber, int gender, String userDOB, int userType, long cityId, long languageId);

}
