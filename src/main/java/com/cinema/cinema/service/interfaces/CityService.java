package com.cinema.cinema.service.interfaces;

import com.cinema.cinema.database.entity.City;

import java.util.List;

public interface CityService extends MainService<City>{
    List<City> findAll();

    void updateCity(long cityId, String cityName);

    void createAndSaveNewCity(String cityName);
}
