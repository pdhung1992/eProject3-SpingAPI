package com.springapi.controllers;

import com.springapi.entities.City;
import com.springapi.payload.request.CityRequest;
import com.springapi.payload.response.CityResponse;
import com.springapi.payload.response.DistrictResponse;
import com.springapi.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping
    public ResponseEntity<List<CityResponse>> getAllCities(){
        return ResponseEntity.status(HttpStatus.OK).body(cityService.getAllCities());
    }

    @GetMapping("/{city_id}")
    public ResponseEntity<?> getCityDetails(@PathVariable int city_id){
        City city = cityService.findCityById(city_id);
        if (city != null){
            return ResponseEntity.status(HttpStatus.OK).body(new CityResponse(city.getCity_id(), city.getCity_name(), city.getThumbnail()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("City not found");
    }


    @PostMapping("/create")
    @PreAuthorize("hasAuthority('Root Admin')")
    public ResponseEntity<?> createCity(@ModelAttribute CityRequest cityRequest){
        try {
            MultipartFile thumbnail = cityRequest.getThumbnail();

            if (thumbnail != null && !thumbnail.isEmpty()){
                File uploadDir = new File("public/img");
                if (!uploadDir.exists()){
                    uploadDir.mkdirs();
                }

                String originalFileName = thumbnail.getOriginalFilename();
                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                String uniqFilename = System.currentTimeMillis() + fileExtension;

                String thumbnailPath = uploadDir.getAbsolutePath() + "/" + uniqFilename;

                Files.copy(thumbnail.getInputStream(), Paths.get(thumbnailPath), StandardCopyOption.REPLACE_EXISTING);

                City newCity = new City(
                        cityRequest.getName(),
                        uniqFilename
                );
                cityService.createCity(newCity);
                return ResponseEntity.status(HttpStatus.OK).body(new CityResponse(newCity.getCity_id(), newCity.getCity_name(), newCity.getThumbnail()));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("City creation failed. 1");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("City creation failed. 2");
        }
    }

    @PostMapping("/update/{city_id}")
    @PreAuthorize("hasAuthority('Root Admin')")
    public ResponseEntity<?> updateCity(@PathVariable int city_id, @ModelAttribute CityRequest cityRequest){
        try {
            City city = cityService.findCityById(city_id);
            if (city != null){
                MultipartFile thumbnail = cityRequest.getThumbnail();

                if (thumbnail != null && !thumbnail.isEmpty()){
                    File uploadDir = new File("public/img");
                    if (!uploadDir.exists()){
                        uploadDir.mkdirs();
                    }

                    String oldThumbnail = city.getThumbnail();
                    if (oldThumbnail != null){
                        File oldThumbnailFile = new File(uploadDir.getAbsolutePath() + "/" + oldThumbnail);
                        if (oldThumbnailFile.exists()){
                            oldThumbnailFile.delete();
                        }
                    }

                    String originalFileName = thumbnail.getOriginalFilename();
                    String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                    String uniqFilename = System.currentTimeMillis() + fileExtension;

                    String thumbnailPath = uploadDir.getAbsolutePath() + "/" + uniqFilename;

                    Files.copy(thumbnail.getInputStream(), Paths.get(thumbnailPath), StandardCopyOption.REPLACE_EXISTING);

                    city.setThumbnail(uniqFilename);
                }
                city.setCity_name(cityRequest.getName());
                cityService.updateCity(city);
                return ResponseEntity.status(HttpStatus.OK).body(new CityResponse(city.getCity_id(), city.getCity_name(), city.getThumbnail()));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("City not found");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("City update failed");
        }
    }

    @DeleteMapping("/delete/{city_id}")
    @PreAuthorize("hasAuthority('Root Admin')")
    public ResponseEntity<?> deleteCity(@PathVariable int city_id){
        City city = cityService.findCityById(city_id);
        if (city != null){
            if (city.getThumbnail() != null){
                File thumbnailFile = new File("public/img/" + city.getThumbnail());
                if (thumbnailFile.exists()){
                    thumbnailFile.delete();
                }
            }

            cityService.deleteCityById(city_id);
            return ResponseEntity.status(HttpStatus.OK).body("City deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("City not found");
    }
}
