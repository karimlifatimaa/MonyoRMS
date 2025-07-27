package com.example.monyorms.DTOs.menyu;

import com.example.monyorms.DTOs.category.CategoryResponseDto;
import lombok.Data;

import java.util.List;

@Data
public class MenyuResponseDto {
    private Long id;
    private String name;
    private List<CategoryResponseDto> categories;
}