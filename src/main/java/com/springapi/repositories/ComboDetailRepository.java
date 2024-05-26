package com.springapi.repositories;

import com.springapi.entities.ComboDetail;
import com.springapi.entities.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComboDetailRepository extends JpaRepository<ComboDetail, Integer> {

    @Query("SELECT cd FROM ComboDetail cd WHERE cd.combo.combo_id = :comboId")
    List<ComboDetail> findByComboId(int comboId);

    @Query("SELECT f FROM Food f WHERE f.food_id IN (SELECT cd.food.food_id FROM ComboDetail cd WHERE cd.combo.combo_id = :comboId)")
    List<Food> findFoodsByComboId(int comboId);
}
