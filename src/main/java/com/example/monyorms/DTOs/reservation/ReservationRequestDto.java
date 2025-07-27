package com.example.monyorms.DTOs.reservation;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ReservationRequestDto {
    private String customerName;
    private String phoneNumber;
    private LocalDateTime reservationStart;
    private LocalDateTime reservationEnd;
    private Long tableId;
}
