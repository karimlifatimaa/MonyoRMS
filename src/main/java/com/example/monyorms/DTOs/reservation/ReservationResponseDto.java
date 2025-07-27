package com.example.monyorms.DTOs.reservation;

import com.example.monyorms.entity.enums.ReservationStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationResponseDto {
    private Long id;
    private String customerName;
    private String phoneNumber;
    private LocalDateTime reservationStart;
    private LocalDateTime reservationEnd;
    private ReservationStatus status;
    private String tableCode;
}
