package com.springapi.repositories;

import com.springapi.entities.Combo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComboRepository extends JpaRepository<Combo, Integer> {

    @Query("SELECT c FROM Combo c WHERE c.restaurant.restaurant_id = :restaurantId")
    List<Combo> findByRestaurantId(int restaurantId);

    @Query("SELECT c FROM Combo c WHERE c.restaurant.restaurant_id = :restaurantId AND c.serveType.serve_type_id = :serveTypeId")
    List<Combo> findByRestaurantAndServeType(int restaurantId, int serveTypeId);
}
