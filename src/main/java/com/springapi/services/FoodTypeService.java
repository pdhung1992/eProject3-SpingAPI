package com.springapi.services;

import com.springapi.payload.response.FoodTypeResponse;
import com.springapi.repositories.FoodTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodTypeService {
    @Autowired
    FoodTypeRepository foodTypeRepository;

    public List<FoodTypeResponse> getAllFoodTypes() {
        return foodTypeRepository.findAll().stream()
                .map(foodType -> new FoodTypeResponse(foodType.getFood_type_id(), foodType.getFood_type_name(), foodType.getDescription(), foodType.getSort_order()))
                .collect(java.util.stream.Collectors.toList());
    }
}
