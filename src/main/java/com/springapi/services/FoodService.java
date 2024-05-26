package com.springapi.services;

import com.springapi.entities.Food;
import com.springapi.entities.ServeType;
import com.springapi.payload.response.FoodResponse;
import com.springapi.payload.response.ServeTypeResponse;
import com.springapi.repositories.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodService {
    @Autowired
    private FoodRepository foodRepository;

    public List<FoodResponse> getFoodsByRestaurant(int restaurantId){
        List<Food> foods = foodRepository.findByRestaurantId(restaurantId);

        return foods.stream()
                .map(food -> new FoodResponse(
                        food.getFood_id(),
                        food.getFood_name(),
                        food.getFoodType().getFood_type_id(),
                        food.getFoodType().getFood_type_name(),
                        food.getDescription(),
                        food.getFood_price(),
                        food.getServeType().getServe_type_id(),
                        food.getServeType().getServe_type_name(),
                        food.getThumbnail(),
                        food.getTag().getTag_id(),
                        food.getTag().getTag_name()
                ))
                .collect(Collectors.toList());
    }

    public List<FoodResponse> getFoodsByRestaurantAndTypeAndServe(int restaurantId, int foodTypeId, int serveTypeId){
        List<Food> foods = foodRepository.findByFoodTypeIdAndServeTypeId(restaurantId, foodTypeId, serveTypeId);

        return foods.stream()
                .map(food -> new FoodResponse(
                        food.getFood_id(),
                        food.getFood_name(),
                        food.getFoodType().getFood_type_id(),
                        food.getFoodType().getFood_type_name(),
                        food.getDescription(),
                        food.getFood_price(),
                        food.getServeType().getServe_type_name(),
                        food.getThumbnail(),
                        food.getTag().getTag_name()
                ))
                .collect(Collectors.toList());
    }

    public List<FoodResponse> getFoodsByRestaurantAndType(int restaurantId, int foodTypeId){
        List<Food> foods = foodRepository.findByRestaurantAndType(restaurantId, foodTypeId);

        return foods.stream()
                .map(food -> new FoodResponse(
                        food.getFood_id(),
                        food.getFood_name(),
                        food.getFoodType().getFood_type_id(),
                        food.getFoodType().getFood_type_name(),
                        food.getDescription(),
                        food.getFood_price(),
                        food.getServeType().getServe_type_name(),
                        food.getThumbnail(),
                        food.getTag().getTag_name()
                ))
                .collect(Collectors.toList());
    }

    public FoodResponse getFoodDetails(int foodId) {
        Food food = foodRepository.findById(foodId).orElse(null);
        return new FoodResponse(
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
        );
    }


    public Food getFoodById(int foodId){
        return foodRepository.findById(foodId).orElse(null);
    }

    public void createFood(Food food){
        foodRepository.save(food);
    }

    public void updateFood(Food food){
        foodRepository.save(food);
    }

    public void deleteFood(int foodId){
        foodRepository.deleteById(foodId);
    }
}
