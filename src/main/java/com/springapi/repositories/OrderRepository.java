package com.springapi.repositories;

import com.springapi.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT o FROM Order o WHERE o.restaurant.restaurant_id = :restaurantId")
    List<Order> getOrderByRestaurantId(int restaurantId);

    @Query("SELECT o FROM Order o WHERE o.user.user_id = :userId")
    List<Order> getOrderByUserId(int userId);
}
