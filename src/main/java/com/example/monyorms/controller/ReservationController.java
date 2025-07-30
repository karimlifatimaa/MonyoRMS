package com.example.monyorms.controller;

import com.example.monyorms.DTOs.reservation.ReservationRequestDto;
import com.example.monyorms.DTOs.reservation.ReservationResponseDto;
import com.example.monyorms.DTOs.reservation.ReservationUpdateDto;
import com.example.monyorms.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/restaurants/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<ReservationResponseDto> createReservation(@Valid @RequestBody ReservationRequestDto request) {
        ReservationResponseDto response = reservationService.createReservation(request);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }


    @PutMapping("/status")
    public ResponseEntity<ReservationResponseDto> updateReservationStatus(@Valid @RequestBody ReservationUpdateDto updateDto){
        ReservationResponseDto reservationResponseDto = reservationService.updateReservationStatus(updateDto);
        return new ResponseEntity<>(reservationResponseDto, HttpStatus.OK);
    }
}
