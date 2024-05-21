package com.springapi.repositories;

import com.springapi.entities.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {

    @Query("SELECT d FROM District d WHERE d.city.city_id = :cityId")
    List<District> findByCityId(@Param("cityId") int cityId);

}
