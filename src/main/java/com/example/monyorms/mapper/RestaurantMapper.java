package com.example.monyorms.mapper;

import com.example.monyorms.DTOs.restaurant.RestaurantRequestDto;
import com.example.monyorms.DTOs.restaurant.RestaurantResponseDto;
import com.example.monyorms.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {TableMapper.class, MenyuMapper.class})
public interface RestaurantMapper {
    @Mapping(source = "diningTables", target = "diningTables")
    @Mapping(source = "menus", target = "menus")
    @Mapping(source = "data", target = "data")
    RestaurantResponseDto toResponseDto(Restaurant restaurant);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "diningTables", ignore = true)
    @Mapping(target = "menus", ignore = true)
    Restaurant toEntity(RestaurantRequestDto dto);
}