package com.example.monyorms.DTOs.category;

import com.example.monyorms.DTOs.dish.DishResponseDto;
import lombok.Data;

import java.util.List;

@Data
public class CategoryResponseDto {
    private Long id;
    private String name;
    private List<DishResponseDto> dishes;
}
