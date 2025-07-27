package com.example.monyorms.repository;

import com.example.monyorms.entity.Menyu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface MenyuRepository extends JpaRepository<Menyu, Long> {
    List<Menyu> findAllByRestaurantId(Long restaurantId);

    // Restoranın adına görə menyuları tapır (JOIN istifadə olunur)
    @Query("SELECT m FROM Menyu m WHERE m.restaurant.name = :restaurantName")
    List<Menyu> findAllByRestaurantName(@Param("restaurantName") String restaurantName);
}
