package com.springapi.controllers;


import com.springapi.entities.City;
import com.springapi.entities.District;
import com.springapi.payload.request.DistrictRequest;
import com.springapi.payload.response.DistrictResponse;
import com.springapi.services.CityService;
import com.springapi.services.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/districts")
public class DistrictController {
    @Autowired
    private DistrictService districtService;

    @Autowired
    private CityService cityService;

    @GetMapping
    public ResponseEntity<List<DistrictResponse>> getAllDistricts(){
        return ResponseEntity.ok(districtService.getAllDistricts());
    }

    @GetMapping("/bycity/{city_id}")
    public ResponseEntity<List<DistrictResponse>> getDistrictsByCityId(@PathVariable int city_id){
        return ResponseEntity.ok(districtService.getDistrictsByCityId(city_id));
    }

    @GetMapping("/details/{district_id}")
    public ResponseEntity<?> getDistrictDetails(@PathVariable int district_id){
        District district = districtService.findDistrictById(district_id);

        if (district != null){
            return ResponseEntity.status(HttpStatus.OK).body(new DistrictResponse(district.getDistrict_id(), district.getDistrict_name(), district.getCity().getCity_id(), district.getCity().getCity_name()));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("District not found");
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('Root Admin')")
    public ResponseEntity<?> createDistrict(@RequestBody DistrictRequest createDistrictRequest){
        try {
            City city = cityService.findCityById(createDistrictRequest.getCityId());

            if (city == null){
                return ResponseEntity.badRequest().body("City not found");
            }

            District newDistrict = new District(
                    createDistrictRequest.getName(),
                    city
            );

            districtService.createDistrict(newDistrict);

            return ResponseEntity.status(HttpStatus.OK).body(new DistrictResponse(newDistrict.getDistrict_id(), newDistrict.getDistrict_name(), newDistrict.getCity().getCity_id(), newDistrict.getCity().getCity_name()));
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error creating district");
        }
    }

    @PostMapping("/update/{district_id}")
    @PreAuthorize("hasAuthority('Root Admin')")
    public ResponseEntity<?> updateDistrict(@PathVariable int district_id, @RequestBody DistrictRequest updateDistrictRequest){
        try {
            District district = districtService.findDistrictById(district_id);

            if (district == null){
                return ResponseEntity.badRequest().body("District not found");
            }

            City city = cityService.findCityById(updateDistrictRequest.getCityId());

            if (city == null){
                return ResponseEntity.badRequest().body("City not found");
            }

            district.setDistrict_name(updateDistrictRequest.getName());
            district.setCity(city);

            districtService.updateDistrict(district);

            return ResponseEntity.status(HttpStatus.OK).body(new DistrictResponse(district.getDistrict_id(), district.getDistrict_name(), district.getCity().getCity_id(), district.getCity().getCity_name()));
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error updating district");
        }
    }

    @DeleteMapping("/delete/{district_id}")
    @PreAuthorize("hasAuthority('Root Admin')")
    public ResponseEntity<?> deleteDistrict(@PathVariable int district_id){
        try {
            District district = districtService.findDistrictById(district_id);

            if (district == null){
                return ResponseEntity.badRequest().body("District not found");
            }

            districtService.deleteDistrictById(district_id);

            return ResponseEntity.status(HttpStatus.OK).body("District deleted successfully");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error deleting district");
        }
    }
}
