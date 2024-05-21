package com.springapi.services;

import com.springapi.entities.City;
import com.springapi.payload.response.CityResponse;
import com.springapi.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    @Autowired
    CityRepository cityRepository;

    public List<CityResponse> getAllCities(){
        return cityRepository.findAll().stream()
                .map(city -> new CityResponse(city.getCity_id(), city.getCity_name(), city.getThumbnail()))
                .collect(java.util.stream.Collectors.toList());
    }

    public void createCity(City city){
        cityRepository.save(city);
    }

    public City findCityById(int city_id){
        return cityRepository.findById(city_id).orElse(null);
    }

    public void updateCity(City city){
        cityRepository.save(city);
    }

    public void deleteCityById(int city_id){
        cityRepository.deleteById(city_id);
    }

}
