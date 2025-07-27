package com.example.monyorms.DTOs.dish;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class DishResponseDto {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;
}
