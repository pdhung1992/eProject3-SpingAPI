package com.springapi.repositories;

import com.springapi.entities.Food;
import com.springapi.entities.ServeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {

    @Query("SELECT f FROM Food f WHERE f.restaurant.restaurant_id = :restaurantId")
    List<Food> findByRestaurantId(@Param("restaurantId") int restaurantId);

    @Query("SELECT f FROM Food f WHERE f.restaurant.restaurant_id = :restaurantId AND f.foodType.food_type_id = :foodTypeId AND f.serveType.serve_type_id = :serveTypeId")
    List<Food> findByFoodTypeIdAndServeTypeId(int restaurantId, int foodTypeId, int serveTypeId);

    @Query("SELECT f FROM Food f WHERE f.restaurant.restaurant_id = :restaurantId AND f.foodType.food_type_id = :foodTypeId")
    List<Food> findByRestaurantAndType(int restaurantId, int foodTypeId);

    @Query("select distinct f.serveType from Food f where f.restaurant.restaurant_id = :restaurantId")
    List<ServeType> findServeTypesOfRestaurant(int restaurantId);
}
