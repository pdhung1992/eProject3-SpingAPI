package com.springapi.services;

import com.springapi.entities.ServeType;
import com.springapi.payload.response.ServeTypeResponse;
import com.springapi.repositories.FoodRepository;
import com.springapi.repositories.ServeTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServeTypeService {
    @Autowired
    ServeTypeRepository serveTypeRepository;
    @Autowired
    FoodRepository foodRepository;


    public List<ServeTypeResponse> getAllServeTypes() {
        return serveTypeRepository.findAll().stream()
                .map(serveType -> new ServeTypeResponse(serveType.getServe_type_id(), serveType.getServe_type_name(), serveType.getValue()))
                .collect(java.util.stream.Collectors.toList());
    }

    public List<ServeTypeResponse> getServeTypesOfRestaurant(int restaurantId){
        List<ServeType> serveTypes = foodRepository.findServeTypesOfRestaurant(restaurantId);
        return serveTypes.stream()
                .map(serveType -> new ServeTypeResponse(serveType.getServe_type_id(), serveType.getServe_type_name(), serveType.getValue()))
                .collect(java.util.stream.Collectors.toList());
    }

    public ServeType getServeTypeById(int id) {
        return serveTypeRepository.findById(id).orElse(null);
    }
}
