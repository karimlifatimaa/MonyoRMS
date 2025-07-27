package com.example.monyorms.repository;

import com.example.monyorms.entity.DiningTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TableRepository extends JpaRepository<DiningTable, Long> {
    // Restaurant ID ilə masaları tapır
    List<DiningTable> findAllByRestaurantId(Long restaurantId);

    // Restaurant adı ilə masaları tapır (custom JPQL sorğusu)
    @Query("SELECT t FROM DiningTable t WHERE t.restaurant.name = :restaurantName")
    List<DiningTable> findAllByRestaurantName(@Param("restaurantName") String restaurantName);
}
