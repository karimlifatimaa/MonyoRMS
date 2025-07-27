package com.example.monyorms.mapper;

import com.example.monyorms.DTOs.table.TableRequestDto;
import com.example.monyorms.DTOs.table.TableResponseDto;
import com.example.monyorms.entity.DiningTable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TableMapper {

    @Mapping(target = "id", ignore = true) // Ignore ID for new entities
    @Mapping(target = "restaurant", ignore = true) // Set in service layer manually
    @Mapping(target = "reservations", ignore = true) // Manage manually (e.g. lazy loading)
    DiningTable toEntity(TableRequestDto dto);

    TableResponseDto toResponseDto(DiningTable diningTable);
}
