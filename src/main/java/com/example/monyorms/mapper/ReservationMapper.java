package com.example.monyorms.mapper;

import com.example.monyorms.DTOs.reservation.ReservationRequestDto;
import com.example.monyorms.DTOs.reservation.ReservationResponseDto;
import com.example.monyorms.entity.Reservation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    Reservation toEntity(ReservationRequestDto dto);
    ReservationResponseDto toResponseDto(Reservation reservation);
}
