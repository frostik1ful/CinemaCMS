package com.cinema.cinema.database.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    private String firstName;
    private String lastName;
    private String nickName;
    private String password;
    private String email;
    private String address;
    private String phone;
    private String cardNumber;
    private Date dateOfRegistration;
    private Date dateOfBirthday;
    //@Enumerated(EnumType.STRING)
    private UserType userType;
    //@Enumerated(EnumType.STRING)
    private UserGender gender;
    @OneToOne
    @JoinColumn(name = "cityId")
    private City city;
    @OneToOne
    @JoinColumn(name = "languageId")
    private Language language;

    public User() {
    }

    public User(String nickName, String password) {
        this.nickName = nickName;
        this.password = password;
    }

    public User(String firstName, String lastName, String nickName, String password, String email, String address, String phone, String cardNumber,Date dateOfRegistration, Date dateOfBirthday, UserType userType, UserGender gender, City city, Language language) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.cardNumber = cardNumber;
        this.dateOfRegistration = dateOfRegistration;
        this.dateOfBirthday = dateOfBirthday;
        this.userType = userType;
        this.gender = gender;
        this.city = city;
        this.language = language;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", dateOfBirthday=" + dateOfBirthday +
                ", userType=" + userType +
                ", gender=" + gender +
                ", city=" + city +
                ", language=" + language +
                '}';
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Date dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public Date getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(Date dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public UserGender getGender() {
        return gender;
    }

    public void setGender(UserGender gender) {
        this.gender = gender;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public enum UserType{
        USER,ADMIN
    }
    public enum UserGender{
        MALE,FEMALE
    }

}

