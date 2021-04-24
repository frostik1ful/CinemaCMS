package com.cinema.cinema.service.classes;

import com.cinema.cinema.database.dao.interfaces.CityDAO;
import com.cinema.cinema.database.entity.City;
import com.cinema.cinema.service.interfaces.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl extends MainServiceImpl<City> implements CityService {
    private final CityDAO dao;
    @Autowired
    public CityServiceImpl(CityDAO dao) {
        super(dao);
        this.dao = dao;
    }

    @Override
    public List<City> findAll() {
        return dao.findAll();
    }

    @Override
    public void updateCity(long cityId, String cityName) {
        findById(cityId).ifPresent(city -> {
            city.setName(cityName);
            update(city);
        });
    }

    @Override
    public void createAndSaveNewCity(String cityName) {
        save(new City(cityName));
    }
}
