package com.example.monyorms.mapper;

import com.example.monyorms.DTOs.category.CategoryRequestDto;
import com.example.monyorms.DTOs.category.CategoryResponseDto;
import com.example.monyorms.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponseDto toResponseDto(Category category);
    Category toEntity(CategoryRequestDto dto);
}
