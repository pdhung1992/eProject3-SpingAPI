package com.springapi.repositories;

import com.springapi.entities.Restaurant;
import com.springapi.entities.ServeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Query("SELECT r FROM Restaurant r WHERE r.admin.admin_id = :adminId")
    Restaurant findByAdminId(@Param("adminId") int adminId);

    @Query("SELECT r FROM Restaurant r WHERE r.district.city.city_id = :cityId")
    List<Restaurant> findByCityId(@Param("cityId") int cityId);

    @Query("SELECT r FROM Restaurant r WHERE r.district.district_id = :districtId")
    List<Restaurant> findByDistrictId(@Param("districtId") int districtId);

    @Query("SELECT r FROM Restaurant r WHERE r.category.category_id = :categoryId")
    List<Restaurant> findByCategoryId(@Param("categoryId") int categoryId);

}
