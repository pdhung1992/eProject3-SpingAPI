package com.springapi.services;

import com.springapi.entities.Combo;
import com.springapi.entities.ComboDetail;
import com.springapi.payload.response.ComboResponse;
import com.springapi.payload.response.FoodResponse;
import com.springapi.repositories.ComboDetailRepository;
import com.springapi.repositories.ComboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComboService {
    @Autowired
    private ComboRepository comboRepository;
    @Autowired
    private ComboDetailService comboDetailService;

    public List<ComboResponse> getCombosByRestaurant(Integer restaurantId) {
        List<Combo> combos = comboRepository.findByRestaurantId(restaurantId);
        return combos.stream().map(combo -> new ComboResponse(
            combo.getCombo_id(),
            combo.getCombo_name(),
            combo.getDescription(),
            combo.getDiscount_rate(),
            combo.getServeType().getServe_type_id(),
            combo.getServeType().getServe_type_name(),
            combo.getThumbnail(),
            combo.getTag().getTag_id(),
            combo.getTag().getTag_name()
        )).collect(Collectors.toList());
    }

    public List<ComboResponse> getCombosByRestaurantAndServeType(Integer restaurantId, Integer serveTypeId) {
        List<Combo> combos = comboRepository.findByRestaurantAndServeType(restaurantId, serveTypeId);
        return combos.stream().map(combo -> new ComboResponse(
            combo.getCombo_id(),
            combo.getCombo_name(),
            combo.getDescription(),
            combo.getDiscount_rate(),
            combo.getServeType().getServe_type_name(),
            combo.getThumbnail(),
            combo.getTag().getTag_name()
        )).collect(Collectors.toList());
    }

    public Combo getComboById(int comboId) {
        return comboRepository.findById(comboId).orElse(null);
    }

    public ComboResponse getComboDetail(int comboId) {
        Combo combo = comboRepository.findById(comboId).orElse(null);
        List<FoodResponse> foods = comboDetailService.getFoodsByCombo(comboId);
        double fullPrice = foods.stream().mapToDouble(FoodResponse::getPrice).sum();
        double actualPrice = fullPrice - (fullPrice * combo.getDiscount_rate() / 100);
        return new ComboResponse(
                combo.getCombo_id(),
                combo.getCombo_name(),
                combo.getDescription(),
                combo.getDiscount_rate(),
                combo.getServeType().getServe_type_name(),
                combo.getThumbnail(),
                combo.getTag().getTag_name(),
                foods.toArray(new FoodResponse[0]),
                actualPrice,
                fullPrice
        );
    }



    public void createCombo(Combo combo) {
        comboRepository.save(combo);
    }


    public void deleteCombo(int comboId) {
        comboRepository.deleteById(comboId);
    }
}
