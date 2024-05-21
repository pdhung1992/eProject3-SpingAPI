package com.springapi.services;

import com.springapi.entities.District;
import com.springapi.payload.response.DistrictResponse;
import com.springapi.repositories.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictService {
    @Autowired
    DistrictRepository districtRepository;

    public List<DistrictResponse> getAllDistricts(){
        return districtRepository.findAll().stream()
                .map(district -> new DistrictResponse(district.getDistrict_id(), district.getDistrict_name(), district.getCity().getCity_id(), district.getCity().getCity_name()))
                .collect(java.util.stream.Collectors.toList());
    }

    public List<DistrictResponse> getDistrictsByCityId(int city_id){
        return districtRepository.findByCityId(city_id).stream()
                .map(district -> new DistrictResponse(district.getDistrict_id(), district.getDistrict_name(), district.getCity().getCity_id(), district.getCity().getCity_name()))
                .collect(java.util.stream.Collectors.toList());
    }

    public District findDistrictById(int district_id){
        return districtRepository.findById(district_id).orElse(null);
    }

    public void createDistrict(District district){
        districtRepository.save(district);
    }

    public void updateDistrict(District district){
        districtRepository.save(district);
    }

    public void deleteDistrictById(int district_id){
        districtRepository.deleteById(district_id);
    }
}
