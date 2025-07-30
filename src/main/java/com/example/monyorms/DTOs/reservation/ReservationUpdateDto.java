package com.example.monyorms.DTOs.reservation;

import com.example.monyorms.entity.enums.ReservationStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservationUpdateDto {

    @NotNull(message = "Rezervasiya ID boş ola bilməz.")
    private Long reservationId; // Yenilənəcək rezervasiyanın ID-si

    @NotNull(message = "Status boş ola bilməz.")
    private ReservationStatus newStatus; // Rezervasiyanın yeni statusu (PENDING, CONFIRMED, CANCELLED)
}