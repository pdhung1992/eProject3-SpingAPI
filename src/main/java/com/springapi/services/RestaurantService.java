package com.springapi.services;

import com.springapi.entities.Restaurant;
import com.springapi.payload.response.RestaurantResponse;
import com.springapi.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    public void createRestaurant(Restaurant restaurant){
        restaurantRepository.save(restaurant);
    }

    public void deleteRestaurant(int restaurantId){
        restaurantRepository.deleteById(restaurantId);
    }

    public Restaurant getRestaurantByAdmin(int adminId){
        return restaurantRepository.findByAdminId(adminId);
    }

    public Restaurant getRestaurantById(int restaurantId){
        return restaurantRepository.findById(restaurantId).orElse(null);
    }

    public List<RestaurantResponse> getRestaurantByCity(int cityId){
        List<Restaurant> restaurants = restaurantRepository.findByCityId(cityId);

        return restaurants.stream()
                .map(restaurant -> new RestaurantResponse(
                        restaurant.getRestaurant_id(),
                        restaurant.getRestaurant_name(),
                        restaurant.getDelivery_hours(),
                        restaurant.getThumbnail(),
                        restaurant.getDistrict().getDistrict_name(),
                        restaurant.getCategory().getCategory_name()
                ))
                .collect(Collectors.toList());
    }

    public List<RestaurantResponse> getRestaurantsByDistrict(int districtId){
        List<Restaurant> restaurants = restaurantRepository.findByDistrictId(districtId);

        return restaurants.stream()
                .map(restaurant -> new RestaurantResponse(
                        restaurant.getRestaurant_id(),
                        restaurant.getRestaurant_name(),
                        restaurant.getDelivery_hours(),
                        restaurant.getThumbnail(),
                        restaurant.getDistrict().getDistrict_name(),
                        restaurant.getCategory().getCategory_name()
                ))
                .collect(Collectors.toList());
    }

    public List<RestaurantResponse> getRestaurantsByCategory(int categoryId){
        List<Restaurant> restaurants = restaurantRepository.findByCategoryId(categoryId);

        return restaurants.stream()
                .map(restaurant -> new RestaurantResponse(
                        restaurant.getRestaurant_id(),
                        restaurant.getRestaurant_name(),
                        restaurant.getDelivery_hours(),
                        restaurant.getThumbnail(),
                        restaurant.getDistrict().getDistrict_name(),
                        restaurant.getCategory().getCategory_name()
                ))
                .collect(Collectors.toList());
    }

    public void updateRestaurant(Restaurant restaurant){
        restaurantRepository.save(restaurant);
    }
}
