package com.springapi.services;

import com.springapi.entities.ComboDetail;
import com.springapi.entities.Food;
import com.springapi.payload.response.FoodResponse;
import com.springapi.repositories.ComboDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComboDetailService {
    @Autowired
    private ComboDetailRepository comboDetailRepository;

    public List<ComboDetail> getComboDetailsByCombo(int comboId){
        return comboDetailRepository.findByComboId(comboId);
    }

    public List<FoodResponse> getFoodsByCombo(int comboId) {
        List<Food> foods = comboDetailRepository.findFoodsByComboId(comboId);
        return foods.stream().map(food -> new FoodResponse(
                food.getFood_id(),
                food.getFood_name(),
                food.getFoodType().getFood_type_id(),
                food.getFoodType().getFood_type_name(),
                food.getDescription(),
                food.getFood_price(),
                food.getServeType().getServe_type_name(),
                food.getThumbnail(),
                food.getTag().getTag_id(),
                food.getTag().getTag_name()
        )).collect(Collectors.toList());
    }

    public void createComboDetail(ComboDetail comboDetail){
        comboDetailRepository.save(comboDetail);
    }

    public void deleteComboDetail(int comboDetailId){
        comboDetailRepository.deleteById(comboDetailId);
    }
}
