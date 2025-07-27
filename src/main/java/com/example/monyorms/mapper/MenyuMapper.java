package com.example.monyorms.mapper;

import com.example.monyorms.DTOs.menyu.MenyuRequestDto;
import com.example.monyorms.DTOs.menyu.MenyuResponseDto;
import com.example.monyorms.entity.Menyu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface MenyuMapper {
    MenyuResponseDto toResponseDto(Menyu menyu);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "restaurant", ignore = true) // restaurant service metodunda set olunur
    @Mapping(target = "categories", ignore = true)
    Menyu toEntity(MenyuRequestDto dto);
}
